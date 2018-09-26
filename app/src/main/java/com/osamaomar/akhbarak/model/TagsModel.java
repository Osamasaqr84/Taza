package com.osamaomar.akhbarak.model;

import java.util.List;

public class TagsModel {
    /**
     * code : 200
     * msg : success
     * Data : [{"TagId":1,"Name":"Tag 1"},{"TagId":2,"Name":"Tag 2"},{"TagId":3,"Name":"Tag 3"},{"TagId":4,"Name":"Tag 4"}]
     */

    private int code;
    private String msg;
    private List<DataBean> Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * TagId : 1
         * Name : Tag 1
         */

        private int TagId;
        private String Name;

        public int getTagId() {
            return TagId;
        }

        public void setTagId(int TagId) {
            this.TagId = TagId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
