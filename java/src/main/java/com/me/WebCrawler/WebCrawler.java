package com.me.WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

public class WebCrawler {
    // 已访问的URL集合，避免重复访问
    private Set<String> visitedUrls;
    // 待访问的URL队列
    private Queue<String> urlsToVisit;
    // 限制爬取的深度
    private int maxDepth;
    // 限制爬取的页面数量
    private int maxPages;
    // 已爬取的页面数量
    private int pagesVisited;
    // 域名限制，只爬取指定域名的页面
    private String domainLimit;
    // 用户代理
    private String userAgent;
    // 请求超时时间（毫秒）
    private int timeout;

    public WebCrawler(String startUrl, int maxDepth, int maxPages, String domainLimit) {
        this.visitedUrls = new HashSet<>();
        this.urlsToVisit = new LinkedList<>();
        this.urlsToVisit.add(startUrl);
        this.maxDepth = maxDepth;
        this.maxPages = maxPages;
        this.pagesVisited = 0;
        this.domainLimit = domainLimit;
        this.userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
        this.timeout = 5000;
    }

    /**
     * 开始爬取
     */
    public void crawl() {
        while (!urlsToVisit.isEmpty() && pagesVisited < maxPages) {
            String currentUrl = urlsToVisit.poll();

            // 如果已经访问过，跳过
            if (visitedUrls.contains(currentUrl)) {
                continue;
            }

            try {
                System.out.println("正在爬取: " + currentUrl);

                // 发送HTTP请求并获取文档
                Document doc = Jsoup.connect(currentUrl)
                        .userAgent(userAgent)
                        .timeout(timeout)
                        .get();

                // 处理获取到的文档
                processPage(doc, currentUrl);

                // 标记为已访问
                visitedUrls.add(currentUrl);
                pagesVisited++;

                // 提取页面中的链接并加入队列
                if (getUrlDepth(currentUrl) < maxDepth) {
                    extractLinks(doc);
                }

            } catch (IOException e) {
                System.err.println("爬取 " + currentUrl + " 时出错: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("无效的URL: " + currentUrl);
            }

            // 礼貌性延迟，避免对服务器造成过大压力
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("爬取完成。共爬取 " + pagesVisited + " 个页面。");
    }

    /**
     * 处理页面内容
     */
    private void processPage(Document doc, String url) {
        // 提取页面标题
        String title = doc.title();
        System.out.println("标题: " + title);

        // 提取正文文本
        String text = doc.body().text();
        System.out.println("正文长度: " + text.length() + " 字符");

        // 这里可以添加更多的处理逻辑，如保存到数据库或文件等
        // saveToDatabase(url, title, text);
    }

    /**
     * 从页面中提取链接并加入队列
     */
    private void extractLinks(Document doc) {
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String href = link.attr("abs:href"); // 获取绝对URL

            // 过滤掉无效的URL
            if (href == null || href.isEmpty() || href.startsWith("#") || href.startsWith("javascript:")) {
                continue;
            }

            // 如果设置了域名限制，只爬取指定域名的页面
            if (domainLimit != null && !href.contains(domainLimit)) {
                continue;
            }

            // 过滤掉非HTTP协议的URL
            if (!href.startsWith("http://") && !href.startsWith("https://")) {
                continue;
            }

            // 过滤掉常见的静态文件
            if (isStaticFile(href)) {
                continue;
            }

            // 添加到待访问队列
            if (!visitedUrls.contains(href)) {
                urlsToVisit.add(href);
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
        return url.split("/").length - 3; // 粗略计算深度
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

    public static void main(String[] args) {
        // 示例用法
        String startUrl = "https://doris.apache.org/"; // 起始URL
        int maxDepth = 2; // 最大爬取深度
        int maxPages = 10; // 最大爬取页面数
        String domainLimit = "apache.org"; // 域名限制

        WebCrawler crawler = new WebCrawler(startUrl, maxDepth, maxPages, domainLimit);
        crawler.setUserAgent("MyWebCrawler/1.0");
        crawler.setTimeout(10000);
        crawler.crawl();
    }
}