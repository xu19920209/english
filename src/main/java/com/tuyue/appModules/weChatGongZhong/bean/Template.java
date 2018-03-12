package com.tuyue.appModules.weChatGongZhong.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名：AEnglishApp
 * 包名：com.tuyue.aenglishapp.entity
 * 创建者：mmcc
 * 创建时间：2017/12/26 15:22
 * 描述：TODO
 */


public class Template {

    /**
     * touser : oRARAwtWrivQCX4SJKA95tQNmEqg
     * template_id : wYvuGrmgrdQ3GIe2PeNfLk6x1EP981xdyxj1MMxbfDY
     * url : http://weixin.qq.com/download
     * data : {"first":{"value":"恭喜你购买成功！","color":"#173177"},"class":{"value":"巧克力","color":"#173177"},"time":{"value":"39.8元","color":"#173177"},"add":{"value":"2014年9月22日","color":"#173177"},"remark":{"value":"欢迎再次购买！","color":"#173177"}}
     */

    private String touser;
    private String template_id;
    private String url;
    private DataBean data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * first : {"value":"恭喜你购买成功！","color":"#173177"}
         * class : {"value":"巧克力","color":"#173177"}
         * time : {"value":"39.8元","color":"#173177"}
         * add : {"value":"2014年9月22日","color":"#173177"}
         * remark : {"value":"欢迎再次购买！","color":"#173177"}
         */

        private FirstBean first;
        @SerializedName("class")
        private ClassBean classX;
        private TimeBean time;
        private AddBean add;
        private RemarkBean remark;

        public FirstBean getFirst() {
            return first;
        }

        public void setFirst(FirstBean first) {
            this.first = first;
        }

        public ClassBean getClassX() {
            return classX;
        }

        public void setClassX(ClassBean classX) {
            this.classX = classX;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public AddBean getAdd() {
            return add;
        }

        public void setAdd(AddBean add) {
            this.add = add;
        }

        public RemarkBean getRemark() {
            return remark;
        }

        public void setRemark(RemarkBean remark) {
            this.remark = remark;
        }

        public static class FirstBean {
            /**
             * value : 恭喜你购买成功！
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class ClassBean {
            /**
             * value : 巧克力
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class TimeBean {
            /**
             * value : 39.8元
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class AddBean {
            /**
             * value : 2014年9月22日
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class RemarkBean {
            /**
             * value : 欢迎再次购买！
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }
}
