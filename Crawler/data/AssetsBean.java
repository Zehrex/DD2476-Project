1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/response/AssetsBean.java
package com.hjq.demo.http.response;

import java.util.List;

/**
 * 获取用户支付方式及资产余额
 * by 刘坤
 * https://docs.apipost.cn/view/01449150faed2ee8#2051560
 */
public class AssetsBean {

    /**
     * Code : 0
     * Msg : success
     * Data : {"payMethods":[{"id":12,"account":"eavesmy","type":0,"qr":"ef901c7d53374e318c91329a1f742d42.png"}],"balance":{"balance":"5000","available":"5000","freezeForSell":"0","freezeForRefound":"0"},"contract":{"balance":"5000","available":"5000","entrust":"0","freeze":"0","hold":"0"},"total":"10000.000000"}
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
         * payMethods : [{"id":12,"account":"eavesmy","type":0,"qr":"ef901c7d53374e318c91329a1f742d42.png"}]
         * balance : {"balance":"5000","available":"5000","freezeForSell":"0","freezeForRefound":"0"}
         * contract : {"balance":"5000","available":"5000","entrust":"0","freeze":"0","hold":"0"}
         * total : 10000.000000
         */

        private BalanceBean balance;
        private ContractBean contract;
        private String total;
        private List<PayMethodsBean> payMethods;

        public BalanceBean getBalance() {
            return balance;
        }

        public void setBalance(BalanceBean balance) {
            this.balance = balance;
        }

        public ContractBean getContract() {
            return contract;
        }

        public void setContract(ContractBean contract) {
            this.contract = contract;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<PayMethodsBean> getPayMethods() {
            return payMethods;
        }

        public void setPayMethods(List<PayMethodsBean> payMethods) {
            this.payMethods = payMethods;
        }

        public static class BalanceBean {
            /**
             * balance : 5000
             * available : 5000
             * freezeForSell : 0
             * freezeForRefound : 0
             */

            private String balance;
            private String available;
            private String freezeForSell;
            private String freezeForRefound;

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getAvailable() {
                return available;
            }

            public void setAvailable(String available) {
                this.available = available;
            }

            public String getFreezeForSell() {
                return freezeForSell;
            }

            public void setFreezeForSell(String freezeForSell) {
                this.freezeForSell = freezeForSell;
            }

            public String getFreezeForRefound() {
                return freezeForRefound;
            }

            public void setFreezeForRefound(String freezeForRefound) {
                this.freezeForRefound = freezeForRefound;
            }
        }

        public static class ContractBean {
            /**
             * balance : 5000
             * available : 5000
             * entrust : 0
             * freeze : 0
             * hold : 0
             */

            private String balance;
            private String available;
            private String entrust;
            private String freeze;
            private String hold;

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getAvailable() {
                return available;
            }

            public void setAvailable(String available) {
                this.available = available;
            }

            public String getEntrust() {
                return entrust;
            }

            public void setEntrust(String entrust) {
                this.entrust = entrust;
            }

            public String getFreeze() {
                return freeze;
            }

            public void setFreeze(String freeze) {
                this.freeze = freeze;
            }

            public String getHold() {
                return hold;
            }

            public void setHold(String hold) {
                this.hold = hold;
            }
        }

        public static class PayMethodsBean {
            /**
             * id : 12
             * account : eavesmy
             * type : 0
             * qr : ef901c7d53374e318c91329a1f742d42.png
             */

            private int id;
            private String account;
            private int type;
            private String qr;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getQr() {
                return qr;
            }

            public void setQr(String qr) {
                this.qr = qr;
            }
        }
    }
}
