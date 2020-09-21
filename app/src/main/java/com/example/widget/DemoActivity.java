package com.example.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class DemoActivity extends AppCompatActivity {

    //2.初始化控件对象
    //1.定义控件对象
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbAutoLogin;
    private Button btnLogin;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        etUsername = findViewById(R.id.et_username);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbAutoLogin = findViewById(R.id.cb_auto_login);
        btnLogin = findViewById(R.id.btn_login);
        //3.设置按钮的监听器
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        //3.1获取用户名和密码的值
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            Toast.makeText(DemoActivity.this, "用户名或密码都不能为空", Toast.LENGTH_LONG).show();
        }
        //3.2比较用户和密码是否正确，然后给出提示
        if ("android".equals(username) && "123456".equals(password)) {
            Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DemoActivity.this, InfoActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            Toast.makeText(this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
        }
    }
}