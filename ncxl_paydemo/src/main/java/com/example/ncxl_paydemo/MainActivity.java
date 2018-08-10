package com.example.ncxl_paydemo;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private Handler handler=new MyHandler(MainActivity.this);
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        load();
    }

    private void load() {
        handler.sendMessageDelayed(Message.obtain(),10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //此方法如果注释掉会报内存泄漏removeCallbacksAndMessages（）；
        handler.removeCallbacksAndMessages(null);

        App application = (App)getApplication();
        RefWatcher refWatcher = application.getRefWatcher();
        refWatcher.watch(this);
    }

  /*  //解决Handler的OOM,如果开启了子线程都需在onDestory关闭
    static class MyHandler extends Handler{
        //软引用，自动回收
        private WeakReference<MainActivity>mWeakReference;

        public MyHandler(MainActivity mainActivity) {
            mWeakReference=new WeakReference<>(mainActivity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mWeakReference.get() != null){
                mWeakReference.get().tv.setText("我喜欢美女");
            }
        }
    }*/
    //会泄漏
     class MyHandler extends Handler{

        MainActivity act ;
        public MyHandler(MainActivity mainActivity) {
            act = mainActivity;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (act != null){
                act.tv.setText("我喜欢敲代码");
            }
        }
    }
    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }
}
