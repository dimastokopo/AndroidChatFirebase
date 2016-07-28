package com.firebase.androidchat.Entity;

import java.sql.Timestamp;

/**
 * @author greg
 * @since 6/21/13
 */
public class Chat {

    private String message;
    private String author;
    private Timestamp date;
    private String prof_id;
   // "last_id
  //  history
    //        id_message
   private String from;
    private String message_type;
    private String image_url;
    private String title_url;
    private String descrip_url;
    private String url;
    private String old;
    private String self;
    private String sent;
    private String tickstate;

    public String getOtherKeyMessage() {
        return otherKeyMessage;
    }

    public void setOtherKeyMessage(String otherKeyMessage) {
        this.otherKeyMessage = otherKeyMessage;
    }

    private String otherKeyMessage;

    public String getSelfKeyMessage() {
        return selfKeyMessage;
    }

    public void setSelfKeyMessage(String selfKeyMessage) {
        this.selfKeyMessage = selfKeyMessage;
    }

    private String selfKeyMessage;


    //timestamp"

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat() {
    }

    public Chat(String message, String author,Timestamp date) {
        this.message = message;
        this.author = author;
        this.date=date;
    }
    public Chat(String message, String author,Timestamp date,String from,
              String message_type,
              String image_url,
              String title_url,
              String descrip_url,
              String url,
              String old,
              String self,
              String sent,
              String tickstate,String OtherKeyMessage,String selfKeyMessage) {
        this.message = message;
        this.author = author;
        this.date=date;
        this.from=from;
                this.message_type=message_type;
                this.image_url=image_url;
                this.title_url=title_url;
                this.descrip_url=descrip_url;
                this.url=url;
                this.old=old;
                this.self=self;
                this.sent=sent;
                this.tickstate=tickstate;
        this.otherKeyMessage=otherKeyMessage;
        this.selfKeyMessage=selfKeyMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getProf_id() {
        return prof_id;
    }

    public void setProf_id(String prof_id) {
        this.prof_id = prof_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle_url() {
        return title_url;
    }

    public void setTitle_url(String title_url) {
        this.title_url = title_url;
    }

    public String getDescrip_url() {
        return descrip_url;
    }

    public void setDescrip_url(String descrip_url) {
        this.descrip_url = descrip_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getTickstate() {
        return tickstate;
    }

    public void setTickstate(String tickstate) {
        this.tickstate = tickstate;
    }
}
