package com.example.asus.zhifubao_zhifu1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alipay.sdk.app.PayTask;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<String> {
    private String mUrl = "http://api.banmi.com/api/app/v3/payments/alipay";
    private String mToken = "mQzcEwW2B5eSdjiqSb8pUZ3ESDiXT4ArER2Ldt6sGmLhWNLv4R8vO7umIuM6tdwaAhVeQjSqwMRCbMYqUTySbl1UM6sAb4y0sdMT9MxIwgcGyz5yAmsw93Ub7i5p9wJZVscFw";
    private static final String TAG = "Alipay";
    private Button btn;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                pay();
                break;
        }
    }

    private void pay() {
        //1.向公司服务器提交数据(支付方式,商品信息,数量,金额)
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, mUrl, this, this){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                ArrayMap<String, String> map = new ArrayMap<>();
                /*@Path("payType") String payType, @Field("amount") String amount, @Field("token") String token,
                @Field("version") String version, @Field("os") String os*/
                map.put("amount","1.0");
                map.put("token","mQzcEwW2B5eSdjiqSb8pUZ3ESDiXT4ArER2Ldt6sGmLhWNLv4R8vO7umIuM6tdwaAhVeQjSqwMRCbMYqUTySbl1UM6sAb4y0sdMT9MxIwgcGyz5yAmsw93Ub7i5p9wJZVscFw");
                map.put("version","3.1.3");
                map.put("os","android");

                return map;
            }
        };
        mQueue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d(TAG,"解析失败");
    }

    @Override
    public void onResponse(String s) {
        Log.d(TAG,s);
        //2.解析数据结果
        Gson gson = new Gson();
        PayOrderInfo payOrderInfo = gson.fromJson(s, PayOrderInfo.class);

        //3.调用支付宝sdk
        callSDK(payOrderInfo);
    }
    private void callSDK(final PayOrderInfo payOrderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String, String> result = alipay.payV2(payOrderInfo.getResult().getSign(), true);

                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    private MyHandler mHandler = new MyHandler();
    class MyHandler extends Handler {
        //4.支付结果处理
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。

            }
            Log.d(TAG,payResult.toString());
        }
    }
}
