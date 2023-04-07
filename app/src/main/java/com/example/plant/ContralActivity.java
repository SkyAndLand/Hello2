package com.example.plant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;



public class ContralActivity extends AppCompatActivity {
    private static final String TAG = "ContralActivity";
    public NetworkTask networkTask;
    private String msg;
    private String[] msgarr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contral);


        Intent intent = getIntent();
        String plantdataname = intent.getStringExtra("plantdataname");//设备的名称
        //设备的参数
        msg = intent.getStringExtra("msg");
        //将设备参数以#分割放到msgarr数组
        if (msg.startsWith("#")) {
            msg = msg.substring(1);
        }
        msgarr = msg.split("#");
        System.out.println(msgarr[0]);

        //
        Log.d(TAG, "onCreate:hu "+plantdataname+ msg);

        //1、禁止浇水
        ImageButton imageButton1 = findViewById(R.id.banwater);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] msgarr1=msgarr;
                msgarr1[2]="off";
                String msg1 = "#" + String.join("#", msgarr1) + "#";
                Log.d(TAG, "onClick: "+msg1);
                // 在这里编写点击事件的处理逻辑
                new NetworkTask("off").execute();


            }
        });
        //2、浇水
        ImageButton imageButton2 = findViewById(R.id.water);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] msgarr2=msgarr;
                msgarr2[2]="on";
                String msg2 = "#" + String.join("#", msgarr2) + "#";
                Log.d(TAG, "onClick: "+msg2);
                new NetworkTask("on").execute();

            }
        });





    }


}
