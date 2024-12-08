package com.me.duckdb;

import okhttp3.*;
import org.duckdb.DuckDBAppender;
import org.duckdb.DuckDBConnection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


// 异步爬虫
public class OkHttp2Duckdb {

    private final static int semaphoreNum = 10;
    private final static int maxRequestsPerHostNum = semaphoreNum * 2;

    public static void main(String[] args) throws InterruptedException, SQLException {
        // 自定义线程池
        Dispatcher dispatcher = new Dispatcher(new ThreadPoolExecutor(
                semaphoreNum, // 核心线程数
                200, // 最大线程数
                60, TimeUnit.SECONDS, // 线程空闲时间
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        ));
        dispatcher.setMaxRequests(maxRequestsPerHostNum);
        dispatcher.setMaxRequestsPerHost(maxRequestsPerHostNum);
        OkHttpClient client = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        DuckDBConnection conn = (DuckDBConnection) DriverManager.getConnection("jdbc:duckdb:C:\\workspace\\zs\\duckdb\\duckdb_demo.db");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE tbl (data json)");
        DuckDBAppender appender = conn.createAppender(DuckDBConnection.DEFAULT_SCHEMA, "tbl");

        // 用于存储异步任务的 Future
        List<CompletableFuture<String>> futures = new ArrayList<>();
        Semaphore semaphore = new Semaphore(semaphoreNum);

        // 开始时间
        long startTime = System.currentTimeMillis();
        int batchSize = 50;
        int totalRequests = 100; // 假设我们要发送100个请求
        // 发送 1 万个异步请求
        for (int i = 0; i < totalRequests; i++) {
            Request request = new Request.Builder()
                    .url("https://www.fastmoss.com/api/author/index/country/?pagesize=100")
                    .header("accept", "application/json")
                    .header("accept-language", "zh-CN,zh;q=0.9")
                    .header("cache-control", "no-cache")
                    .header("content-type", "application/json")
                    .header("cookie", "NEXT_LOCALE=zh; vis_fid=673497470110d1133021731499847.0047; region=TH; fd_id=vBDt5ldcmrIxJ0WeVfjypqNAG3HuP21a; fp_visid=21def1a1c0bca0300e6332c453c70b3c; _src=")
                    .header("pragma", "no-cache")
                    .header("priority", "u=1, i")
                    .header("referer", "https://www.fastmoss.com/zh/dashboard")
                    .header("sec-ch-ua", "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"")
                    .header("sec-ch-ua-mobile", "?0")
                    .header("sec-ch-ua-platform", "\"Windows\"")
                    .header("sec-fetch-dest", "empty")
                    .header("sec-fetch-mode", "cors")
                    .header("sec-fetch-site", "same-origin")
                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36")
                    .build();

            // 创建 CompletableFuture 用于存储单个请求的结果
            CompletableFuture<String> future = new CompletableFuture<>();


            semaphore.acquire();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // 将失败信息传递到 future
                    future.complete("Request " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        if (response.isSuccessful()) {
                            future.complete("Request " + response.body().string());
                            appender.beginRow();
                            appender.append(response.body().string());
                            appender.endRow();
                        } else {
                            future.complete("Request " + response.code());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        response.close(); // 释放资源
                        semaphore.release();
                    }
                }
            });

            futures.add(future);
            if ((i + 1) % batchSize == 0) {
                Thread.sleep(1000);
            }
        }

        appender.flush();
        stmt.close();

        futures.forEach(future -> {
            future.whenComplete((result, throwable) -> {
                if (throwable != null) {
                    System.out.println("Error: " + throwable.getMessage());
                } else {
                    System.out.println("Result: " + result);
                }
            });
        });


        long endTime = System.currentTimeMillis();
        System.out.println("All requests completed in " + (endTime - startTime) + " ms");

        client.dispatcher().executorService().shutdown();
    }
}
