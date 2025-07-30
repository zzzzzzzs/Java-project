package com.me.WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class AsyncWebCrawler {
    // 已访问的URL集合，使用ConcurrentHashMap保证线程安全
    private final Set<String> visitedUrls = ConcurrentHashMap.newKeySet();
    // 待访问的URL队列，使用线程安全的LinkedBlockingQueue
    private final BlockingQueue<String> urlsToVisit = new LinkedBlockingQueue<>();
    // 线程池
    private final ExecutorService executorService;
    // 限制爬取的深度
    private final int maxDepth;
    // 限制爬取的页面数量
    private final int maxPages;
    // 已爬取的页面数量
    private final AtomicInteger pagesVisited = new AtomicInteger(0);
    // 域名限制
    private final String domainLimit;
    // 用户代理
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
    // 请求超时时间（毫秒）
    private int timeout = 5000;
    // 爬取延迟（毫秒）
    private int crawlDelay = 1000;
    // 最大并发数
    private final int maxConcurrentRequests;

    public AsyncWebCrawler(String startUrl, int maxDepth, int maxPages, String domainLimit, int maxConcurrentRequests) {
        this.urlsToVisit.add(startUrl);
        this.maxDepth = maxDepth;
        this.maxPages = maxPages;
        this.domainLimit = domainLimit;
        this.maxConcurrentRequests = maxConcurrentRequests;
        this.executorService = Executors.newFixedThreadPool(maxConcurrentRequests);
    }

    /**
     * 开始异步爬取
     */
    public void startCrawling() {
        System.out.println("开始异步爬取...");

        // 创建CompletableFuture数组来跟踪所有任务
        CompletableFuture<?>[] futures = new CompletableFuture[maxConcurrentRequests];

        for (int i = 0; i < maxConcurrentRequests; i++) {
            futures[i] = CompletableFuture.runAsync(this::crawlTask, executorService);
        }

        // 等待所有任务完成
        CompletableFuture.allOf(futures).join();

        System.out.println("爬取完成。共爬取 " + pagesVisited.get() + " 个页面。");
        executorService.shutdown();
    }

    /**
     * 单个爬取任务
     */
    private void crawlTask() {
        while (pagesVisited.get() < maxPages && !Thread.currentThread().isInterrupted()) {
            String currentUrl;
            try {
                currentUrl = urlsToVisit.poll(1, TimeUnit.SECONDS);
                if (currentUrl == null) {
                    if (pagesVisited.get() >= maxPages) {
                        break;
                    }
                    continue;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            // 如果已经访问过，跳过
            if (visitedUrls.contains(currentUrl)) {
                continue;
            }

            // 标记为已访问
            if (!visitedUrls.add(currentUrl)) {
                continue;
            }

            try {
                System.out.println(Thread.currentThread().getName() + " 正在爬取: " + currentUrl);

                // 发送HTTP请求并获取文档
                Document doc = Jsoup.connect(currentUrl)
                        .userAgent(userAgent)
                        .timeout(timeout)
                        .get();

                // 处理获取到的文档
                processPage(doc, currentUrl);

                // 增加已爬取页面计数
                pagesVisited.incrementAndGet();

                // 提取页面中的链接并加入队列
                if (getUrlDepth(currentUrl) < maxDepth) {
                    extractLinks(doc);
                }

                // 礼貌性延迟
                Thread.sleep(crawlDelay);
            } catch (IOException e) {
                System.err.println("爬取 " + currentUrl + " 时出错: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IllegalArgumentException e) {
                System.err.println("无效的URL: " + currentUrl);
            }
        }
    }

    /**
     * 处理页面内容
     */
    private void processPage(Document doc, String url) {
        // 提取页面标题
        String title = doc.title();
        System.out.println(Thread.currentThread().getName() + " 标题: " + title);

        // 提取正文文本
        String text = doc.body().text();
        System.out.println(Thread.currentThread().getName() + " 正文长度: " + text.length() + " 字符");

        // 这里可以添加更多的处理逻辑
    }

    /**
     * 从页面中提取链接并加入队列
     */
    private void extractLinks(Document doc) {
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String href = link.attr("abs:href");

            // 过滤无效URL
            if (href == null || href.isEmpty() || href.startsWith("#") || href.startsWith("javascript:")) {
                continue;
            }

            // 域名限制
            if (domainLimit != null && !href.contains(domainLimit)) {
                continue;
            }

            // 只处理HTTP/HTTPS
            if (!href.startsWith("http://") && !href.startsWith("https://")) {
                continue;
            }

            // 过滤静态文件
            if (isStaticFile(href)) {
                continue;
            }

            // 添加到待访问队列
            if (!visitedUrls.contains(href)) {
                urlsToVisit.offer(href);
            }
        }
    }

    /**
     * 判断URL是否是静态文件
     */
    private boolean isStaticFile(String url) {
        Pattern staticFilePattern = Pattern.compile(".*\\.(css|js|bmp|gif|jpe?g|png|ico|pdf|zip|rar|tar|gz|mp3|mp4|avi|mov|wmv)$", Pattern.CASE_INSENSITIVE);
        return staticFilePattern.matcher(url).matches();
    }

    /**
     * 获取URL的深度
     */
    private int getUrlDepth(String url) {
        return url.split("/").length - 3;
    }

    /**
     * 设置用户代理
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 设置超时时间
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 设置爬取延迟
     */
    public void setCrawlDelay(int crawlDelay) {
        this.crawlDelay = crawlDelay;
    }

    public static void main(String[] args) {
        // 示例用法
        String startUrl = "https://doris.apache.org/";
        int maxDepth = 2;
        int maxPages = 50;
        String domainLimit = "apache.org";
        int maxConcurrentRequests = 50; // 并发请求数

        AsyncWebCrawler crawler = new AsyncWebCrawler(
                startUrl, maxDepth, maxPages, domainLimit, maxConcurrentRequests);

        crawler.setUserAgent("MyAsyncWebCrawler/1.0");
        crawler.setTimeout(10000);
        crawler.setCrawlDelay(500); // 设置500毫秒延迟

        crawler.startCrawling();
    }
}