package com.ccj.base.bean;

import com.ccj.base.base.BaseBean;
import com.google.gson.Gson;

import java.io.Serializable;

public class User extends BaseBean implements Serializable {

    public static final long serialVersionUID = 2233933716943685981L;
    /**
     * code : 200
     * msg : 登录成功
     * result : {"ID":1,"Users_Organization":null,"Users_CellPhoneNum":"13800138000","Users_PassWord":"123456","Users_CorpName":"安监站","Users_IDCard":null,"Users_Kind":1,"Users_RegisterDate":"2016-06-14T11:20:02","Users_IsDel":false,"Users_Guid":null,"Users_PersonName":"刘某某","Users_CorpKind":null,"Users_AppID":109,"Users_AreaCode":"02","Users_TableName":null,"Users_PKCode":null,"Users_Alias":"10014AAC2F4FAD043E6BF71E30C34DEC","Users_JobName":"主管","Users_CorpKindName":"施工企业","Users_AreaName":"市南区","Users_Photo":null,"Users_OrderBy":null}
     */

    public String code;
    public String msg;
    /**
     * ID : 1
     * Users_Organization : null
     * Users_CellPhoneNum : 13800138000
     * Users_PassWord : 123456
     * Users_CorpName : 安监站
     * Users_IDCard : null
     * Users_Kind : 1
     * Users_RegisterDate : 2016-06-14T11:20:02
     * Users_IsDel : false
     * Users_Guid : null
     * Users_PersonName : 刘某某
     * Users_CorpKind : null
     * Users_AppID : 109
     * Users_AreaCode : 02
     * Users_TableName : null
     * Users_PKCode : null
     * Users_Alias : 10014AAC2F4FAD043E6BF71E30C34DEC
     * Users_JobName : 主管
     * Users_CorpKindName : 施工企业
     * Users_AreaName : 市南区
     * Users_Photo : null
     * Users_OrderBy : null
     */

    public ResultBean result;


    public static User objectFromData(String str) {

        return new Gson().fromJson(str, User.class);
    }

    public static class ResultBean {
        public int ID;
        public Object Users_Organization;
        public String Users_CellPhoneNum;
        public String Users_PassWord;
        public String Users_CorpName;
        public Object Users_IDCard;
        public int Users_Kind;
        public String Users_RegisterDate;
        public boolean Users_IsDel;
        public Object Users_Guid;
        public String Users_PersonName;
        public Object Users_CorpKind;
        public int Users_AppID;
        public String Users_AreaCode;
        public Object Users_TableName;
        public Object Users_PKCode;
        public String Users_Alias;
        public String Users_JobName;
        public String Users_CorpKindName;
        public String Users_AreaName;
        public Object Users_Photo;
        public Object Users_OrderBy;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "ID=" + ID +
                    ", Users_Organization=" + Users_Organization +
                    ", Users_CellPhoneNum='" + Users_CellPhoneNum + '\'' +
                    ", Users_PassWord='" + Users_PassWord + '\'' +
                    ", Users_CorpName='" + Users_CorpName + '\'' +
                    ", Users_IDCard=" + Users_IDCard +
                    ", Users_Kind=" + Users_Kind +
                    ", Users_RegisterDate='" + Users_RegisterDate + '\'' +
                    ", Users_IsDel=" + Users_IsDel +
                    ", Users_Guid=" + Users_Guid +
                    ", Users_PersonName='" + Users_PersonName + '\'' +
                    ", Users_CorpKind=" + Users_CorpKind +
                    ", Users_AppID=" + Users_AppID +
                    ", Users_AreaCode='" + Users_AreaCode + '\'' +
                    ", Users_TableName=" + Users_TableName +
                    ", Users_PKCode=" + Users_PKCode +
                    ", Users_Alias='" + Users_Alias + '\'' +
                    ", Users_JobName='" + Users_JobName + '\'' +
                    ", Users_CorpKindName='" + Users_CorpKindName + '\'' +
                    ", Users_AreaName='" + Users_AreaName + '\'' +
                    ", Users_Photo=" + Users_Photo +
                    ", Users_OrderBy=" + Users_OrderBy +
                    '}';
        }

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }




}



