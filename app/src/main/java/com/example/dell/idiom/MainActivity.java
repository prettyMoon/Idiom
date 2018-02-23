package com.example.dell.idiom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.idiom.android_db.Databse_Operate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView t1, t2, t3, t4;
    private TextView w11, w12, w13, w14, w21, w22, w23, w24, w31, w32, w33, w34, w41, w42, w43, w44, countdowntime;
    private Button button_restart, button_undo;
    private List<TextView> list_show;
    private List<TextView> list_press;
    private mycount Daojishi;
    private Databse_Operate dbo;
    private List<String> temp_String;
    private String show_answer = "";
    private boolean UnFinish = true;
    private boolean UnFinish02 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_show = new ArrayList<TextView>();
        list_press = new ArrayList<TextView>();
        dbo = new Databse_Operate(this);
        dbo.createDatabase();
        temp_String = new ArrayList<String>();
        Daojishi = new mycount(30000, 1000);
        //显示
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        list_show.add(t1);
        list_show.add(t2);
        list_show.add(t3);
        list_show.add(t4);
        //点击
        w11 = (TextView) findViewById(R.id.word11);
        w11.setOnClickListener(this);
        list_press.add(w11);
        w12 = (TextView) findViewById(R.id.word12);
        w12.setOnClickListener(this);
        list_press.add(w12);
        w13 = (TextView) findViewById(R.id.word13);
        w13.setOnClickListener(this);
        list_press.add(w13);
        w14 = (TextView) findViewById(R.id.word14);
        w14.setOnClickListener(this);
        list_press.add(w14);
        w21 = (TextView) findViewById(R.id.word21);
        w21.setOnClickListener(this);
        list_press.add(w21);
        w22 = (TextView) findViewById(R.id.word22);
        w22.setOnClickListener(this);
        list_press.add(w22);
        w23 = (TextView) findViewById(R.id.word23);
        w23.setOnClickListener(this);
        list_press.add(w23);
        w24 = (TextView) findViewById(R.id.word24);
        w24.setOnClickListener(this);
        list_press.add(w24);
        w31 = (TextView) findViewById(R.id.word31);
        w31.setOnClickListener(this);
        list_press.add(w31);
        w32 = (TextView) findViewById(R.id.word32);
        w32.setOnClickListener(this);
        list_press.add(w32);
        w33 = (TextView) findViewById(R.id.word33);
        w33.setOnClickListener(this);
        list_press.add(w33);
        w34 = (TextView) findViewById(R.id.word34);
        w34.setOnClickListener(this);
        list_press.add(w34);
        w41 = (TextView) findViewById(R.id.word41);
        w41.setOnClickListener(this);
        list_press.add(w41);
        w42 = (TextView) findViewById(R.id.word42);
        w42.setOnClickListener(this);
        list_press.add(w42);
        w43 = (TextView) findViewById(R.id.word43);
        w43.setOnClickListener(this);
        list_press.add(w43);
        w44 = (TextView) findViewById(R.id.word44);
        w44.setOnClickListener(this);
        list_press.add(w44);
        countdowntime = (TextView) findViewById(R.id.countdowntime);
        countdowntime.setText(" ");

        button_restart = (Button) findViewById(R.id.Button_restart);
        button_undo = (Button) findViewById(R.id.Button_undo);
//游戏开始的提示
        AlertDialog.Builder bulider = new AlertDialog.Builder(MainActivity.this);
        bulider.setTitle("提示");
        bulider.setMessage("游戏即将开始，准备好了吗？");

        bulider.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Daojishi.start();
            }
        });
        AlertDialog alertDialog = bulider.create();
        alertDialog.show();


        setPressText();
        //重新开始
        button_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder bulider = new AlertDialog.Builder(MainActivity.this);
                bulider.setTitle("提示");
                bulider.setMessage("确定要重新开始吗？");
                bulider.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UnFinish02 = false;
                        show_answer = "";
                        temp_String.clear();
                        for (int i = 0; i < list_show.size(); i++) {
                            list_show.get(i).setText("");
                        }
                        setPressText();
                        UnFinish = false;
                        // Daojishi.onFinish();
                        Daojishi.cancel();
                        UnFinish = true;
                        Daojishi.start();

                    }
                });
                bulider.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = bulider.create();
                alertDialog.show();
            }
        });

        button_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0, i = 0;
                while (!(list_show.get(i).getText().equals("")) && i < list_show.size()) {
                    count++;
                    i++;
                }

                if (count == 0) {
                    return;
                } else if (count == 1) {
                    list_show.get(0).setText("");
                    return;
                } else {
                    list_show.get(count - 1).setText("");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        switch (id) {
            case R.id.help:
                intent = new Intent(MainActivity.this, Help.class);
                item.setIntent(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //各种监听器
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.word11:
                setShowText(w11.getText().toString());
                Judge();
                break;
            case R.id.word12:
                setShowText(w12.getText().toString());
                Judge();
                break;
            case R.id.word13:
                setShowText(w13.getText().toString());
                Judge();
                break;
            case R.id.word14:
                setShowText(w14.getText().toString());
                Judge();
                break;
            case R.id.word21:
                setShowText(w21.getText().toString());
                Judge();
                break;
            case R.id.word22:
                setShowText(w22.getText().toString());
                Judge();
                break;
            case R.id.word23:
                setShowText(w23.getText().toString());
                Judge();
                break;
            case R.id.word24:
                setShowText(w24.getText().toString());
                Judge();
                break;
            case R.id.word31:
                setShowText(w31.getText().toString());
                Judge();
                break;
            case R.id.word32:
                setShowText(w32.getText().toString());
                Judge();
                break;
            case R.id.word33:
                setShowText(w33.getText().toString());
                Judge();
                break;
            case R.id.word34:
                setShowText(w34.getText().toString());
                Judge();
                break;
            case R.id.word41:
                setShowText(w41.getText().toString());
                Judge();
                break;
            case R.id.word42:
                setShowText(w42.getText().toString());
                Judge();
                break;
            case R.id.word43:
                setShowText(w43.getText().toString());
                Judge();
                break;
            case R.id.word44:
                setShowText(w44.getText().toString());
                Judge();
                break;
        }
    }


    //设置显示文字
    public void setShowText(String text) {
        for (int i = 0; i < list_show.size(); i++) {
            if (list_show.get(i).getText().toString().equals("") || list_show.get(i).getText().equals(null)) {
                list_show.get(i).setText(text);
                return;
            }
        }
    }


    public List<String> string_to_single() {
        List<String> templist = new ArrayList<String>();
        List<String> temp_single = new ArrayList<String>();

        int chengyu_count = 0;
        List<Integer> number_double = new ArrayList<Integer>();
        templist = dbo.slectData();
        while (chengyu_count < 4) {
            int i = (int) (Math.random() * templist.size());
            number_double.add(i);
            for (int j = 0; j < number_double.size(); j++) {
                if (i != number_double.get(j)) {
                    temp_String.add(templist.get(i));
                    chengyu_count++;
                    break;
                }
            }

        }
        for (int i = 0; i < temp_String.size(); i++) {

            for (int j = 0; j < 4; j++) {
                temp_single.add(temp_String.get(i).charAt(j) + "");
            }
        }
        Collections.shuffle(temp_single);
        return temp_single;
    }

    public void setPressText() {
        List<String> str = string_to_single();
        for (int i = 0; i < str.size(); i++) {
            list_press.get(i).setText(str.get(i));
        }
    }

    //判断方法
    public void Judge() {
        int count = 0;
        show_answer = "";
        for (int i = 0; i < list_show.size(); i++) {
            if (!(list_show.get(i).getText().equals(null) || list_show.get(i).getText().equals(""))) {
                count++;
            }
        }
        //如果按下四个键，进行判断
        if (count == 4) {
            //获取显示栏中的成语
            for (int j = 0; j < list_show.size(); j++) {
                show_answer += list_show.get(j).getText();
            }
            //判断成语是否正确
            for (int i = 0; i < temp_String.size(); i++) {
                //如果回答正确

                if (UnFinish02) {
                    return;
                }
                if (show_answer.equals(temp_String.get(i))) {

                    for (int j = 0; j < list_show.size(); j++) {
                        for (int k = 0; k < list_press.size(); k++) {
                            if (list_press.get(k).getText().equals(list_show.get(j).getText())) {
                                list_press.get(k).setText("");
                                Isend();
                                break;
                            }
                        }
                    }
                    for (int j = 0; j < list_show.size(); j++) {
                        list_show.get(j).setText("");
                    }
                    return;
                }
            }
            for (int i = 0; i < list_show.size(); i++) {
                list_show.get(i).setText("");
            }
        }
    }

    public void Isend() {
        int count = 0;
        for (int i = 0; i < list_press.size(); i++) {
            if (list_press.get(i).getText().equals("") || list_press.get(i).getText().equals(null)) {
                count++;
            }
        }
        if (count == 16) {
            UnFinish = false;
            UnFinish02 = true;
            Daojishi.cancel();
            AlertDialog.Builder bulider = new AlertDialog.Builder(MainActivity.this);
            bulider.setTitle(" Congratulations!");
            bulider.setMessage("恭喜高智商男神加学霸的你，这么快就完成挑战，是否要开始新一局游戏？");
            bulider.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UnFinish02 = false;
                    show_answer = "";
                    temp_String.clear();
                    for (int i = 0; i < list_show.size(); i++) {
                        list_show.get(i).setText("");
                    }
                    setPressText();
                    UnFinish = false;
                    Daojishi.onFinish();

                    UnFinish = true;
                    Daojishi.start();
                }
            });
            bulider.setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = bulider.create();
            alertDialog.show();
        } else {
            return;
        }

    }


    class mycount extends CountDownTimer {
        public mycount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            countdowntime.setText("倒计时： " + millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            Daojishi.cancel();
            countdowntime.setText("游戏结束！");
            if (UnFinish) {

                // UnFinish02 = true;
                AlertDialog.Builder bulider = new AlertDialog.Builder(MainActivity.this);
                bulider.setTitle(" 时间到!");
                bulider.setMessage("很遗憾你最终未能完成游戏，是否再战一局？");
                bulider.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UnFinish02 = false;
                        show_answer = "";
                        temp_String.clear();
                        for (int i = 0; i < list_show.size(); i++) {
                            list_show.get(i).setText("");
                        }
                        setPressText();
                        UnFinish = false;
                        // Daojishi.onFinish();
                        Daojishi.cancel();
                        UnFinish = true;
                        Daojishi.start();
                    }
                });
                bulider.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = bulider.create();
                alertDialog.show();
            }
        }
    }
}
