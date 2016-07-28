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
    private String is_typing;



    private String status;
    private String status_desc;


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
        this.is_typing="0";
        this.status="";
        this.status_desc="";
    }
    public ChatUsersProfile(String prof_id, String prof_avatar,String prof_name,String is_typing) {
        this.prof_id = prof_id;
        this.prof_avatar = prof_avatar;
        this.prof_name=prof_name;
        this.is_typing=is_typing;
        this.status="";
        this.status_desc="";
    }
    public ChatUsersProfile(String prof_id, String prof_avatar,String prof_name,String is_typing,String status,String status_desc) {
        this.prof_id = prof_id;
        this.prof_avatar = prof_avatar;
        this.prof_name=prof_name;
        this.is_typing=is_typing;
        this.status=status;
        this.status_desc=status_desc;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnore(true)

public String getIs_typing() {
    return is_typing;
}

    public void setIs_typing(String is_typing) {
        this.is_typing = is_typing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_desc() {
        return status_desc;
    }

    public void setStatus_desc(String status_desc) {
        this.status_desc = status_desc;
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

    public String getProf_avatar() {
        return prof_avatar;
    }

    public void setProf_avatar(String prof_avatar) {
        this.prof_avatar = prof_avatar;
    }
   // private String prof_id;

}
