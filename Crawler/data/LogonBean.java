1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/response/LogonBean.java
package com.hjq.demo.http.response;

/**
 *
 * 刘坤
 * 登录bean
 * 接口地址
 *
 * https://docs.apipost.cn/view/01449150faed2ee8#2049207
 */
public class LogonBean {

    /**
     * Code : 0
     * Msg : success
     * Data : {"id":17,"phone":"","email":"eavesmy@gmail.com","updateDate":"0001-01-01T00:00:00Z","createDate":"0001-01-01T00:00:00Z","loginDate":"0001-01-01T00:00:00Z","wallet":{"omniAddr":"1Q4kdLeSMKtzqQtYEoLo2RCaRzykp1xgEk","ercAddr":"0x8460B2b27a50F6514760dd4d0Fd5b512F45d0f0f"},"token":"01e56bc864e039a9e4c1bcfeecead6707c044c5f076475d0c87e6901ff389470a52c58d608fdf1aad9dbf6d05bc4030e1528cf26","veried":false}
     */

    private int Code;
    private String Msg;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 17
         * phone :
         * email : eavesmy@gmail.com
         * updateDate : 0001-01-01T00:00:00Z
         * createDate : 0001-01-01T00:00:00Z
         * loginDate : 0001-01-01T00:00:00Z
         * wallet : {"omniAddr":"1Q4kdLeSMKtzqQtYEoLo2RCaRzykp1xgEk","ercAddr":"0x8460B2b27a50F6514760dd4d0Fd5b512F45d0f0f"}
         * token : 01e56bc864e039a9e4c1bcfeecead6707c044c5f076475d0c87e6901ff389470a52c58d608fdf1aad9dbf6d05bc4030e1528cf26
         * veried : false
         */

        private int id;
        private String phone;
        private String email;
        private String updateDate;
        private String createDate;
        private String loginDate;
        private WalletBean wallet;
        private String token;
        private boolean veried;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public WalletBean getWallet() {
            return wallet;
        }

        public void setWallet(WalletBean wallet) {
            this.wallet = wallet;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public boolean isVeried() {
            return veried;
        }

        public void setVeried(boolean veried) {
            this.veried = veried;
        }

        public static class WalletBean {
            /**
             * omniAddr : 1Q4kdLeSMKtzqQtYEoLo2RCaRzykp1xgEk
             * ercAddr : 0x8460B2b27a50F6514760dd4d0Fd5b512F45d0f0f
             */

            private String omniAddr;
            private String ercAddr;

            public String getOmniAddr() {
                return omniAddr;
            }

            public void setOmniAddr(String omniAddr) {
                this.omniAddr = omniAddr;
            }

            public String getErcAddr() {
                return ercAddr;
            }

            public void setErcAddr(String ercAddr) {
                this.ercAddr = ercAddr;
            }
        }
    }
}
