package com.openhack.serverless;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {
    public static Response sendGet(String path) throws Exception {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response;
    }
}
