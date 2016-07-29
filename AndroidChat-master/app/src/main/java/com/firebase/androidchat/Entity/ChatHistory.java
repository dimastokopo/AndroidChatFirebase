package com.firebase.androidchat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 22/07/2016.
 */
public class ChatHistory {
    private Timestamp date;
    private String from;
    private String last_message;
    private String name;
    private String type;
    private String is_typing;
   private String others_id;
  private String last_key_message;
  @JsonIgnoreProperties(ignoreUnknown = true)
  private String   tickstate;



    public String getTickstate() {
        return tickstate;
    }

    public void setTickstate(String tickstate) {
        this.tickstate = tickstate;
    }



    public ChatHistory(){

    }

    public String getLast_key_message() {
        return last_key_message;
    }

    public void setLast_key_message(String last_key_message) {
        this.last_key_message = last_key_message;
    }



    public ChatHistory(String name, String from, Timestamp date, String type, String last_message) {
        this.from = from;
        this.name = name;
        this.date=date;
        this.type=type;
        this.last_message=last_message;
        this.tickstate="";
    }
    public String getOthers_id() {
        return others_id;
    }

    public void setOthers_id(String others_id) {
        this.others_id = others_id;
    }

    public String getIs_typing() {
        return is_typing;
    }

    public void setIs_typing(String is_typing) {
        this.is_typing = is_typing;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


   // private Timestamp date;
}
