package com.example.plant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plant.dao.UserDao;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void reg(View view){

        startActivity(new Intent(this,RegisterActivity.class));

    }


    public void login(View view){
        EditText EditTextName = (EditText)findViewById(R.id.name);
        EditText EditTextPassword = (EditText)findViewById(R.id.password);

        new Thread(){
            @Override
            public void run() {

                UserDao userDao = new UserDao();

                boolean aa = false;
                try {
                    aa = userDao.login(EditTextName.getText().toString(),EditTextPassword.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int msg = 0;
                if(aa){
                    msg = 1;
                }
                hand1.sendEmptyMessage(msg);
            }
        }.start();


    }
    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();
                //监听按钮，如果点击，就跳转
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
            }
        }
    };
}