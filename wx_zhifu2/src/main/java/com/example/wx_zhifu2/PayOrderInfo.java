package com.example.wx_zhifu2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xts on 2018/8/10.
 */

public class PayOrderInfo {

    /**
     * code : 0
     * desc :
     * result : {"id":7294,"wx":{"appid":"wx2281f6dda3069029","noncestr":"ArJVY0H7AykicnavyquBNfLozleSN1xv","package":"Sign=WXPay","partnerid":"1462681502","prepayid":"wx1015031428939852c9847eaa4219766520","timestamp":"1533884594","sign":"2A08FA4689CE3DBF8FEBC20A8552F601"}}
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
         * id : 7294
         * wx : {"appid":"wx2281f6dda3069029","noncestr":"ArJVY0H7AykicnavyquBNfLozleSN1xv","package":"Sign=WXPay","partnerid":"1462681502","prepayid":"wx1015031428939852c9847eaa4219766520","timestamp":"1533884594","sign":"2A08FA4689CE3DBF8FEBC20A8552F601"}
         */

        private int id;
        private WxBean wx;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public WxBean getWx() {
            return wx;
        }

        public void setWx(WxBean wx) {
            this.wx = wx;
        }

        public static class WxBean {
            /**
             * appid : wx2281f6dda3069029
             * noncestr : ArJVY0H7AykicnavyquBNfLozleSN1xv
             * package : Sign=WXPay
             * partnerid : 1462681502
             * prepayid : wx1015031428939852c9847eaa4219766520
             * timestamp : 1533884594
             * sign : 2A08FA4689CE3DBF8FEBC20A8552F601
             */

            private String appid;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private String timestamp;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
