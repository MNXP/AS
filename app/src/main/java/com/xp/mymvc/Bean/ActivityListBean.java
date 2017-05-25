package com.xp.mymvc.Bean;

import java.util.List;

/***
 * 活动列表数据
 */
public class ActivityListBean {

    public static class ActivityBean {

        private Integer id;	/*3*/
        private String descrition;	/*test*/
        private String title;	/*测试*/
        private String jumpUrl;	/*m.cheyr.cn*/
        private String beginTime;	/*2016-05-11 00:00:00*/
        private String endTime;	/*2016-05-11 00:00:00*/
        private String url;	/*http://pre.cheyr.cn/files/activity/1462956386459.jpg*/
        private Integer sortNum;	/*1*/
        private String statusFlag; //活动状态标识 1.即将开始   2.进行中  3.已结束

        public void setId(Integer value) {
            this.id = value;
        }

        public Integer getId() {
            return this.id;
        }

        public void setDescrition(String value) {
            this.descrition = value;
        }

        public String getDescrition() {
            return this.descrition;
        }

        public void setTitle(String value) {
            this.title = value;
        }

        public String getTitle() {
            return this.title;
        }

        public void setJumpUrl(String value) {
            this.jumpUrl = value;
        }

        public String getJumpUrl() {
            return this.jumpUrl;
        }

        public void setBeginTime(String value) {
            this.beginTime = value;
        }

        public String getBeginTime() {
            return this.beginTime;
        }

        public void setEndTime(String value) {
            this.endTime = value;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setStatusFlag(String value) {
            this.statusFlag = value;
        }

        public String getStatusFlag() {
            return this.statusFlag;
        }
        public void setUrl(String value) {
            this.url = value;
        }

        public String getUrl() {
            return this.url;
        }

        public void setSortNum(Integer value) {
            this.sortNum = value;
        }

        public Integer getSortNum() {
            return this.sortNum;
        }

    }

    private List<ActivityBean> list;	/*List<TList>*/

    public void setList(List<ActivityBean> value) {
        this.list = value;
    }

    public List<ActivityBean> getList() {
        return this.list;
    }

    private String totalNum;	/*2*/
    private String resmsg;	/*success*/
    private String rescode;	/*00000*/
    private String resmsg_cn;	/**/
    private Integer listSize;	/*2*/

    public void setTotalNum(String value) {
        this.totalNum = value;
    }

    public String getTotalNum() {
        return this.totalNum;
    }

    public void setResmsg(String value) {
        this.resmsg = value;
    }

    public String getResmsg() {
        return this.resmsg;
    }

    public void setRescode(String value) {
        this.rescode = value;
    }

    public String getRescode() {
        return this.rescode;
    }

    public void setResmsg_cn(String value) {
        this.resmsg_cn = value;
    }

    public String getResmsg_cn() {
        return this.resmsg_cn;
    }

    public void setListSize(Integer value) {
        this.listSize = value;
    }

    public Integer getListSize() {
        return this.listSize;
    }

}
