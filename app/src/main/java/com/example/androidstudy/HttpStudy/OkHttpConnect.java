package com.example.androidstudy.HttpStudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpConnect {
    public static String post(String url, File file, final JCUploadCallback callback) throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();

        OkHttpClient client = ProgressManager.getInstance().with(new OkHttpClient.Builder()).build();
        ProgressManager.getInstance().addRequestListener(url, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                Float per = Float.valueOf(100*progressInfo.getCurrentbytes() / progressInfo.getContentLength());
                callback.onFileUpdate(per);
            }

            @Override
            public void onError(long id, Exception e) {
                callback.onFileError(id, e);
            }
        });
        FileInputStream fis = new FileInputStream(file);
        fis.available();
        MediaType mediaType = MediaType.parse("image/png/jpg");
        RequestBody body = RequestBody.create(mediaType, file);
        body.contentLength();
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("moreInfo", "aa")
                .addHeader("token", "123")
                .addHeader("extraParams", "")
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            callback.onFileResult(responseBody.string());
            return responseBody.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
