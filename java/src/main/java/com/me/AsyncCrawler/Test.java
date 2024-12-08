package com.me.AsyncCrawler;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Test {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        OkHttpClient client = new OkHttpClient();

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
        CompletableFuture<String> future = new CompletableFuture<>();
        HashSet<String> set = new HashSet<>();
        String ss;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                future.complete(response.body().string());
                set.add(response.body().string());
//                System.out.println(response.body().string());
            }
        });

//        String s = future.get();
//        System.out.println(s);

        set.forEach(System.out::println);

        client.dispatcher().executorService().shutdown();

//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            System.out.println(response.body().string());
//        }
    }
}
