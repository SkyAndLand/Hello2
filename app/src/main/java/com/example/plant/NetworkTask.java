package com.example.plant;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//实现发送数据到物联网平台
public class NetworkTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "NetworkTask";
    private  String msg;
    public  NetworkTask(String msg){
        this.msg=msg;
    }
    @Override
    protected String doInBackground(Void... voids) {
        postplantdata(msg);

        return null;
    }



    public void postplantdata(String msg){
        OkHttpClient client = new OkHttpClient();
        // 构造请求参数

        String urlParameters =msg;
        Log.d(TAG, "postplantdata: "+msg);


        // 构造请求对象
        Request request = new Request.Builder()
                .url("https://apis.bemfa.com/va/postmsg?uid=c901424982d6476c83630ee94dde0040&topic=led&type=3&msg="+urlParameters)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), ""))
                .build();

        // 发送请求并获取响应
        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.d(TAG, "postplantdata: "+responseData);

            // 处理响应数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
