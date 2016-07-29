package com.firebase.androidchat.Entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ChatGroupMember {

    //private Timestamp date;
    private String prof_id;
    private String prof_name;
   private String prof_avatar;

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    private Timestamp joinDate;
private ChatGroupMember(){

}
    public ChatGroupMember(String prof_id,
                           String prof_name,
                           String prof_avatar,
                           Timestamp joinDate){
        this.prof_id=prof_id;
        this.prof_name=prof_name;
        this.prof_avatar=prof_avatar;
        this.joinDate=joinDate;


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
