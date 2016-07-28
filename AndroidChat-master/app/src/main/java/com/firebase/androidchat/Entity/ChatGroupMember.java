package com.firebase.androidchat.Entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ChatGroupMember {

    //private Timestamp date;
    private String prof_id;
    private String prof_name;
   private String prof_avatar;
private ChatGroupMember(){

}

    public String getProf_avatar() {
        return prof_avatar;
    }

    public void setProf_avatar(String prof_avatar) {
        this.prof_avatar = prof_avatar;
    }

    public String getProf_id() {
        return prof_id;
    }

    public void setProf_id(String prof_id) {
        this.prof_id = prof_id;
    }

    public String getProf_name() {
        return prof_name;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }
}
