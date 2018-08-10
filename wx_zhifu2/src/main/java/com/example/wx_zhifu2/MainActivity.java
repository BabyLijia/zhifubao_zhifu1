package com.example.wx_zhifu2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<String> {
    private static final String TAG = "WxPay";
    private RequestQueue mQueue;
    //post
    private String mUrl = "http://api.banmi.com/api/app/v3/payments/wxpay";
    private String mToken = "mQzcEwW2B5eSdjiqSb8pUZ3ESDiXT4ArER2Ldt6sGmLhWNLv4R8vO7umIuM6tdwaAhVeQjSqwMRCbMYqUTySbl1UM6sAb4y0sdMT9MxIwgcGyz5yAmsw93Ub7i5p9wJZVscFw";
    private Button btn;

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
        //1.提交参数到公司服务器
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, mUrl, this, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                ArrayMap<String, String> map = new ArrayMap<>();
                map.put("amount","1.0");
                map.put("token","mQzcEwW2B5eSdjiqSb8pUZ3ESDiXT4ArER2Ldt6sGmLhWNLv4R8vO7umIuM6tdwaAhVeQjSqwMRCbMYqUTySbl1UM6sAb4y0sdMT9MxIwgcGyz5yAmsw93Ub7i5p9wJZVscFw");
                map.put("version","3.1.3");
                map.put("os","android");
                return map;
            }
        };
        mQueue.add(request);

    }

    private void callSDK(PayOrderInfo payOrderInfo) {
        //注册APPID
        final IWXAPI api = WXAPIFactory.createWXAPI(this, null);
        api.registerApp(payOrderInfo.getResult().getWx().getAppid());

        //调起支付
        PayReq request = new PayReq();
        PayOrderInfo.ResultBean.WxBean wxBean = payOrderInfo.getResult().getWx();
        request.appId = wxBean.getAppid();
        request.partnerId = wxBean.getPartnerid();
        request.prepayId= wxBean.getPrepayid();
        request.packageValue = wxBean.getPackageX();
        request.nonceStr= wxBean.getNoncestr();
        request.timeStamp= wxBean.getTimestamp();
        request.sign= wxBean.getSign();
        api.sendReq(request);

        //4.处理支付结果
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(String string) {
        Log.d(TAG,string);
        //2.解析数据
        Gson gson = new Gson();
        PayOrderInfo payOrderInfo = gson.fromJson(string, PayOrderInfo.class);
        //3.调用sdk
        callSDK(payOrderInfo);
    }
}
