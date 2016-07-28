package com.firebase.androidchat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ChatUsersProfile {

    private String prof_id;
    private String prof_name;
    private String prof_avatar;


//private ChatUsersProfile(){

//}
    public ChatUsersProfile(){
        this.prof_id = "";
        this.prof_avatar = "";
        this.prof_name="";
    }
    public ChatUsersProfile(String prof_id, String prof_avatar,String prof_name) {
        this.prof_id = prof_id;
        this.prof_avatar = prof_avatar;
        this.prof_name=prof_name;
    }

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnore(true)

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

    public String getProf_avatar() {
        return prof_avatar;
    }

    public void setProf_avatar(String prof_avatar) {
        this.prof_avatar = prof_avatar;
    }
   // private String prof_id;

}
