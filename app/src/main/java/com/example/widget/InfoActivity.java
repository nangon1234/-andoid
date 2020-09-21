package com.example.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    //定义控件变量
    private TextView tv_username;       //定义获取用户名的控件件
    private EditText et_real_name;     //定义获取含有姓名的控件
    private RadioGroup group_sex;        //定义获取性别单选按钮组
    private RadioButton rbtn_male;       //定义获取性别男的单选按钮
    private RadioButton rbtn_female;      //定义获取性别女的单选按钮
    private CheckBox cb_java;            //定义获取含有java内容的单选按钮
    private CheckBox cb_android;         //定义获取含有android内容的单选按钮
    private CheckBox cb_sql;            //定义获取含有sql内容的单选按钮
    private Button btn_confirm;          //定义获取确认按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //1.初始化界面控件
        //1.1初始化控件对象
        et_real_name = findViewById(R.id.et_real_name);
        group_sex = findViewById(R.id.group_sex);
        rbtn_male = findViewById(R.id.rbtn_male);
        rbtn_female = findViewById(R.id.rbtn_female);
        cb_java = findViewById(R.id.cb_java);
        cb_android = findViewById(R.id.cb_android);
        cb_sql = findViewById(R.id.cb_sql);
        btn_confirm = findViewById(R.id.btn_confirm);
        //1.2获取登录界面传递的数据
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("username");
            tv_username.setText(name);
        }
        //2.设置点击事件，键盘事件的监听器
        //2.1设置点击事件
        btn_confirm.setOnClickListener(this);
        //2.2键盘事件处理
        et_real_name.setOnKeyListener((v, keyCode, event) -> {
            //按回车键隐藏软键盘
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                //关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                return true;
            }
            return false;
        });
    }

    // 4.处理单击事件
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_confirm) {
            getInfo();
        }
    }

    private void getInfo() {
        //4.1获取控件的值
        String username = tv_username.getText().toString().trim();
        String real_name = et_real_name.getText().toString().trim();
        String sex = "男";
        String favorite = "";
        //4.2获取选择RadioButton的id;
        int id = group_sex.getCheckedRadioButtonId();
        if (id == R.id.rbtn_male) {
            sex = "男";
        } else {
            sex = "女";
        }
       /* if(rbtn_male.isChecked()){
            sex = rbtn_male.getText().toString();
        }else if(rbtn_female.isChecked()){
            sex = rbtn_female.getText().toString();
        }*/
        if (cb_java.isChecked()) {
            favorite += cb_java.getText().toString() + ",";
        }
        if (cb_android.isChecked()) {
            favorite += cb_android.getText().toString() + ",";
        }
        if (cb_sql.isChecked()) {
            favorite += cb_sql.getText().toString() + ",";
        }
        //4.3显示或传递内容
        String info = "用户名:" + username + "\n姓名:"
                + real_name + "\n性别:"
                + sex + "\n喜欢的课程:"
                + favorite.trim().substring(0, favorite.trim().length() - 1);
        Toast.makeText(InfoActivity.this, info, Toast.LENGTH_LONG).show();
        //跳转到主界面
        Intent intent = new Intent(InfoActivity.this, MainActivity.class);
        startActivity(intent);
    }

}