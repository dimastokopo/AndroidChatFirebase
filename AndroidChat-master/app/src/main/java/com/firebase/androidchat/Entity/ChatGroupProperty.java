package com.firebase.androidchat.Entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ChatGroupProperty {

    private String group_id;
    private String group_name;
    private String group_avatar;

    private String createdby_prof_id;
    private String createdby_prof_name;
    private Timestamp created_date;
private ChatGroupProperty(){

}
    public ChatGroupProperty(    String group_id,
              String group_name,
              String group_avatar,
              String createdby_prof_id,
              String createdby_prof_name,
              Timestamp created_date) {
        this.group_id=group_id;
                this.group_name=group_name;
                this.group_avatar=group_avatar;
                this.createdby_prof_id=createdby_prof_id;
                this.createdby_prof_name=createdby_prof_name;
                this.created_date=created_date;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_avatar() {
        return group_avatar;
    }

    public void setGroup_avatar(String group_avatar) {
        this.group_avatar = group_avatar;
    }

    public String getCreatedby_prof_id() {
        return createdby_prof_id;
    }

    public void setCreatedby_prof_id(String createdby_prof_id) {
        this.createdby_prof_id = createdby_prof_id;
    }

    public String getCreatedby_prof_name() {
        return createdby_prof_name;
    }

    public void setCreatedby_prof_name(String createdby_prof_name) {
        this.createdby_prof_name = createdby_prof_name;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

}
