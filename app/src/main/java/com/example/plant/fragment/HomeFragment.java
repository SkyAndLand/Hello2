package com.example.plant.fragment;

import android.content.Intent;

import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.plant.Adapter.HomeAdapter;
import com.example.plant.ContralActivity;
import com.example.plant.MainActivity;
import com.example.plant.Object.PlantData;
import com.example.plant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView plantRecyclerView;//存放植物item的可滑动容器
    private HomeAdapter plantAdapter;//获取设配器
    private MainActivity mainActivity; //获取首页Activity
    private String msg;//读取的字符串
    private String time;//读取的时间
    private String url;
    private ArrayList<PlantData> plantData;
    private PlantData data;



    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home,null);
        //获取Acitivty（Main）
        mainActivity = (MainActivity) getActivity();
        //加载列表的item
        plantRecyclerView = view.findViewById(R.id.id_recyclerview);//找到滑动列表
        plantRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//控制Recyclerview的布局是垂直还是水平
        getplantData();
        plantAdapter = new HomeAdapter(plantData);
        plantRecyclerView.setAdapter(plantAdapter);
        //item点击事件
        plantAdapter.setMyOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getActivity(), ContralActivity.class);
                intent.putExtra("plantdataname",data.getB());
                intent.putExtra("msg",msg);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
    //将从巴法平台的数据转换放到plantData
    public   void getplantData(){
        final CountDownLatch latch = new CountDownLatch(2);

        //读取巴法平台的数据
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://apis.bemfa.com/va/getmsg?uid=c901424982d6476c83630ee94dde0040&topic=wyhtemp&type=3&num=1")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d(TAG, "onFailure:1 ");
                latch.countDown();

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {


                    String jsonData = response.body().string();

                    // 解析JSON数据
                    JSONObject jsonObject = new JSONObject(jsonData);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject latestData = jsonArray.getJSONObject(jsonArray.length()-1);

                    // 获取最新的一条数据
                    msg = latestData.getString("msg");
                    time=latestData.getString("time");
                    Log.d(TAG, "onResponse: success1");

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        });

        Request request1 = new Request.Builder()
                .url("https://images.bemfa.com/cloud/v1/get/?uid=c901424982d6476c83630ee94dde0040&topic=picture01&num=1")
                .build();

        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d(TAG, "onFailure:2 ");
                latch.countDown();

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {

                    String jsonData = response.body().string();

                    // 解析JSON数据
                    JSONObject jsonObject = new JSONObject(jsonData);
                    //data
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject latestData = jsonArray.getJSONObject(jsonArray.length()-1);


                    // 获取最新的一条数据
                    url = latestData.getString("url");

                    Log.d(TAG, "onResponse: "+"success2");


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        });
        try {
            latch.await();
            //以#分割
            if (msg.startsWith("#")) {
                msg = msg.substring(1);
            }
            String[] arr = msg.split("#");
            //放入plantData集合中
            data = new PlantData(url,"设备1",arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],time);
            plantData = new ArrayList<>();
            plantData.add(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }





}
