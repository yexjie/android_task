package com.example.AndroidTask.EnterFram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.AndroidTask.MainFram.EnterMainFram;
import com.example.AndroidTask.Database.SPSave;
import com.example.cq_1014_task.R;

public class Login extends AppCompatActivity {
    private Button mBtnZc;//点开注册界面
    private Button mBtnDenglu;
    private EditText et_account; //账号输入框
    private EditText et_password; //密码输入框
    //private Map<String,String> Saved_information; //获取保存在XML中的信息
    /*private String Saved_account;
    private String Saved_password;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String account= sp.getString("account",null);
        String password= sp.getString("password",null);
        //Map<String,String> Saved_information=new HashMap<String,String>(SPSave.getUserInfo(getApplicationContext()));
        //if (Saved_information.get("account")!=null&&Saved_information.get("password")!=null) {
        if (account!=null&&password!=null) {
            Toast.makeText(getApplicationContext(),"自动登录成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, EnterMainFram.class);
            startActivity(intent);
        }
        Toast.makeText(getApplicationContext(),account,Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_login);
        mBtnZc=(Button) findViewById(R.id.btn_woyaozhuce);
        mBtnDenglu=(Button) findViewById(R.id.btn_denglu);
        et_account=(EditText) findViewById(R.id.et_1);
        et_password=(EditText) findViewById(R.id.et_2);
        setListeners();

    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        mBtnZc.setOnClickListener(onClick);
        mBtnDenglu.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.btn_woyaozhuce:
                    intent=new Intent(Login.this,RegisterActivity.class);
                    break;
                case R.id.btn_denglu:
                    String account =et_account.getText().toString().trim();
                    String password=et_password.getText().toString();
                    if (TextUtils.isEmpty(account)){
                        Toast.makeText(getApplicationContext(),"请输入账号",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)){
                        Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    boolean isSaveSuccess = SPSave.saveUserInfo(getApplicationContext(),account,password);
                    if (isSaveSuccess){
                        Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(getApplicationContext(),"保存失败",Toast.LENGTH_SHORT).show();
                    intent=new Intent(Login.this, EnterMainFram.class);
                    break;
            }
            finish();
            startActivity(intent);
        }
    }

}