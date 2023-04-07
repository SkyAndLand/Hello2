package com.example.plant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class UpdataPSWActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userId;
    private EditText et_old_psw;
    private EditText et_new_psw;
    private Button btn_update;
    private Button btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_psw);
        initView();
    }

    private void initView() {
        userId = (EditText) findViewById(R.id.userId);
        et_old_psw = (EditText) findViewById(R.id.et_old_psw);
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_clear = (Button) findViewById(R.id.btn_clear);

        btn_update.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                break;
            case R.id.btn_clear:
                userId.setText("");
                et_old_psw.setText("");
                et_new_psw.setText("");
                break;
        }
    }

}
