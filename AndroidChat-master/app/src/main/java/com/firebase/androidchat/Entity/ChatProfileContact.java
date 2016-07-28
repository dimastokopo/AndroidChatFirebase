package com.firebase.androidchat.Entity;

/**
 * Created by Administrator on 26/07/2016.
 */
public class ChatProfileContact {
    public String getProf_id() {
        return prof_id;
    }

    public void setProf_id(String prof_id) {
        this.prof_id = prof_id;
    }

    private String prof_id;

    public ChatProfileContact(){
        this.prof_id = "";

    }
    public ChatProfileContact(String profid){
        this.prof_id = profid;

    }
}
