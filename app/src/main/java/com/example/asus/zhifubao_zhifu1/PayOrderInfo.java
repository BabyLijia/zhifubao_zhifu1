package com.example.asus.zhifubao_zhifu1;

import java.io.Serializable;

public class PayOrderInfo implements Serializable {

    /**
     * code : 0
     * desc :
     * result : {"sign":"app_id=2017071807800448&method=alipay.trade.app.pay&version=1.0&format=JSON&charset=utf8&sign_type=RSA2&timestamp=2018-08-10%2009%3A08%3A55&notify_url=https%3A%2F%2Fapi.banmi.com%2Fapi%2Fapp%2Fv3%2Fpayments%2Falibaba&biz_content=%7B%22subject%22%3A%22%E4%BC%B4%E7%B1%B3%E6%97%85%E8%A1%8C%22%2C%22out_trade_no%22%3A%22APP1533863335648ALIPAY591205%22%2C%22total_amount%22%3A%221.00%22%2C%22timeout_express%22%3A%2215m%22%7D&sign=c6JPYYO6BbIkd%2F0ncYNjLqQTSU55UG2pI9P5Nz2hh17whSANnyd4yZPrGhyBqd9ZyyvA14LtMLpCCfgssG4MiwP3ux5Acqb40w9fNorulAo2v4SSnBqGNGWdeIqIMlGNEilZ%2Buc7d2n3G%2FG1qQ%2Bta%2F3bjJa15i%2F3XencZ1hPx%2Bk2KZJZjy4dbHW4fDoJIIB89A04kRkg4dz4P%2BzN20%2FDLhSIDKN6bHz7fCGp9doi07wk2kKymYgvohTW6FNy9sLaR7nj62RHXUezEaVJzAFnkw711FxFCcXreeryEN%2Bq6bgzu9sxI06XAa2j1UZIdcXcCEeKbI%2F94T4qhmJjZ4zkgQ%3D%3D","id":7166}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sign : app_id=2017071807800448&method=alipay.trade.app.pay&version=1.0&format=JSON&charset=utf8&sign_type=RSA2&timestamp=2018-08-10%2009%3A08%3A55&notify_url=https%3A%2F%2Fapi.banmi.com%2Fapi%2Fapp%2Fv3%2Fpayments%2Falibaba&biz_content=%7B%22subject%22%3A%22%E4%BC%B4%E7%B1%B3%E6%97%85%E8%A1%8C%22%2C%22out_trade_no%22%3A%22APP1533863335648ALIPAY591205%22%2C%22total_amount%22%3A%221.00%22%2C%22timeout_express%22%3A%2215m%22%7D&sign=c6JPYYO6BbIkd%2F0ncYNjLqQTSU55UG2pI9P5Nz2hh17whSANnyd4yZPrGhyBqd9ZyyvA14LtMLpCCfgssG4MiwP3ux5Acqb40w9fNorulAo2v4SSnBqGNGWdeIqIMlGNEilZ%2Buc7d2n3G%2FG1qQ%2Bta%2F3bjJa15i%2F3XencZ1hPx%2Bk2KZJZjy4dbHW4fDoJIIB89A04kRkg4dz4P%2BzN20%2FDLhSIDKN6bHz7fCGp9doi07wk2kKymYgvohTW6FNy9sLaR7nj62RHXUezEaVJzAFnkw711FxFCcXreeryEN%2Bq6bgzu9sxI06XAa2j1UZIdcXcCEeKbI%2F94T4qhmJjZ4zkgQ%3D%3D
         * id : 7166
         */

        private String sign;
        private int id;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
