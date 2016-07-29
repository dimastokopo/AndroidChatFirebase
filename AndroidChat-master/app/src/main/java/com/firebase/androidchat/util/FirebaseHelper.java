package com.firebase.androidchat.util;

import android.app.Activity;
import android.graphics.Color;

import com.firebase.androidchat.Adapter.ChatGroupMemberAdapter;
import com.firebase.androidchat.Adapter.ChatHeaderAdapter;
import com.firebase.androidchat.Adapter.ChatHistoryListAdapter;
import com.firebase.androidchat.Adapter.ChatListAdapter;
import com.firebase.androidchat.Adapter.ChatUsersContactAdapter;
import com.firebase.androidchat.Entity.Chat;
import com.firebase.androidchat.Entity.ChatGroupMember;
import com.firebase.androidchat.Entity.ChatGroupProperty;

import com.firebase.androidchat.Entity.ChatHistory;
import com.firebase.androidchat.Entity.ChatProfileContact;
import com.firebase.androidchat.Entity.ChatUsersContact;
import com.firebase.androidchat.Entity.ChatUsersProfile;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 25/07/2016.
 */
public class FirebaseHelper {
  //  private static final String FIREBASE_URL_Users ="https://scorching-fire-8526.firebaseio.com/Users/";
   // private static final String FIREBASE_URL_Groups ="https://scorching-fire-8526.firebaseio.com/Groups/";
  private static final String FIREBASE_URL_Users ="https://campaignbeta-44d01.firebaseio.com/Users/";

  private static final String FIREBASE_URL_Root ="https://campaignbeta-44d01.firebaseio.com/";
    private static final String FIREBASE_URL_Groups ="https://campaignbeta-44d01.firebaseio.com/Groups/";
    ChatUsersProfile tempUserProfile=new ChatUsersProfile();

    //==================Firebase Database Table================================================
    public Firebase getFirebaseHistory(String mProfID){
        String FIREBASE_URL_HISTORY=FIREBASE_URL_Users+mProfID;
        Firebase mFirebaseHistory = new Firebase(FIREBASE_URL_HISTORY).child("History Chat");
        return mFirebaseHistory;
    }
    public Firebase getFirebaseHistoryForInsertSingle(String mProfID,String mProfIDOthers){
        String FIREBASE_URL_HISTORY=FIREBASE_URL_Users+mProfID+"/History Chat";
        Firebase mFirebaseHistory = new Firebase(FIREBASE_URL_HISTORY).child(mProfIDOthers);
        return mFirebaseHistory;
    }
    public Firebase getFirebaseProfile(String mProfID){
        String FIREBASE_URL_HISTORY=FIREBASE_URL_Users;
        Firebase mFirebaseHistory = new Firebase(FIREBASE_URL_HISTORY).child(mProfID);
        return mFirebaseHistory;
    }
    public Firebase getFirebaseChatSingle(String mProfID,String mProfIDOthers){
        String FirebaseSingleChatUser1=FIREBASE_URL_Users+mProfID+"/SingleChat/";
        Firebase mFirebaseChatPerson1 = new Firebase(FirebaseSingleChatUser1).child(mProfIDOthers);

        return mFirebaseChatPerson1;
    }




    public Firebase getFirebaseUserContact(String mProfID){
        String FIREBASE_URL_HISTORY=FIREBASE_URL_Users+mProfID;
        Firebase mFirebaseHistory = new Firebase(FIREBASE_URL_HISTORY).child("Contacts");
        return mFirebaseHistory;
    }
    public Firebase getFirebaseUserAddContact(String mProfID,String mProfIDAdd){
        String FIREBASE_URL_HISTORY=FIREBASE_URL_Users+mProfID+"/Contacts";
        Firebase mFirebaseHistory = new Firebase(FIREBASE_URL_HISTORY).child(mProfIDAdd);
        return mFirebaseHistory;
    }
    public Firebase getFirebaseChatGroupRoot(){
        String FirebaseGroupChat=FIREBASE_URL_Groups;

        Firebase mFirebaseChatPerson1 = new Firebase(FirebaseGroupChat);
        //.child("Groups");

        return mFirebaseChatPerson1;
    }
   public Firebase getFirebaseChatGroupMessage(String mGroupKey ){
        String FirebaseGroupChat=FIREBASE_URL_Groups+mGroupKey;

        Firebase mFirebaseChatPerson1 = new Firebase(FirebaseGroupChat).child("Messages");

        return mFirebaseChatPerson1;
    }
    public Firebase getFirebaseChatGroupMembers(String mGroupKey ){
        String FirebaseGroupChat=FIREBASE_URL_Groups+mGroupKey;

        Firebase mFirebaseChatPerson1 = new Firebase(FirebaseGroupChat).child("Members");

        return mFirebaseChatPerson1;
    }
    public Firebase getFirebaseChatGroupProperty(String mGroupKey ){
        String FirebaseGroupChat=FIREBASE_URL_Groups;
        // String FirebaseSingleChatUser1=FIREBASE_URL_Users+mProfID+"/SingleChat/";
        Firebase mFirebaseChatPerson1 = new Firebase(FirebaseGroupChat).child(mGroupKey);
        return mFirebaseChatPerson1;
    }
public Firebase getFirebaseSingleChatWithKey(String KeyMessage,String mProfID,String mProfIDOthers){
    String FirebaseSingleChatUser1=FIREBASE_URL_Users+mProfID+"/SingleChat/"+mProfIDOthers;
    Firebase mFirebaseChatPerson1 = new Firebase(FirebaseSingleChatUser1).child(KeyMessage);

    return mFirebaseChatPerson1;

}
    //==================Firebase Database Table================================================



    //==================Firebase Get Data================================================

    public ChatHistoryListAdapter getFirstHistoryAdapter(int limit, int IDLayout,  Activity act,String mProf_ID){
        Firebase mFirebaseHistory=getFirebaseHistory(mProf_ID);
        ChatHistoryListAdapter ch = new ChatHistoryListAdapter(mFirebaseHistory.limit(limit), act, IDLayout,mProf_ID);
        return ch;
    }

    public ChatListAdapter getFirstChatAdapter(int limit, int IDLayout, Activity act,String mProf_ID,String mProf_id_others) {
        Firebase mFirebaseChatPerson1=getFirebaseChatSingle(mProf_ID,mProf_id_others);

        ChatListAdapter mChatListAdapter = new ChatListAdapter(mFirebaseChatPerson1.limit(limit), act, IDLayout, mProf_ID);

        return mChatListAdapter;
    }
    public ChatListAdapter getChatAdapter(String key_start,int limit, int IDLayout, Activity act,String mProf_ID,String mProf_id_others) {
        Firebase mFirebaseChatPerson1=getFirebaseChatSingle(mProf_ID,mProf_id_others);
        ChatListAdapter mChatListAdapter = new ChatListAdapter(mFirebaseChatPerson1.orderByKey().startAt(key_start).limit(limit), act, IDLayout, mProf_ID);
        return mChatListAdapter;
    }

    public ChatHistoryListAdapter getHistoryAdapter(String key_start,int limit, int IDLayout,  Activity act,String mProf_ID){
        Firebase mFirebaseHistory=getFirebaseHistory(mProf_ID);
        ChatHistoryListAdapter ch = new ChatHistoryListAdapter(mFirebaseHistory.orderByKey().startAt(key_start).limit(limit), act, IDLayout,mProf_ID);
        return ch;
    }

    public ChatHeaderAdapter getHeaderSingleCHatAdapter(  int IDLayout, Activity act, String mProf_ID,String mProf_id_others){
        Firebase mFirebaseHistory=getFirebaseHistory(mProf_ID);
        Query ref=mFirebaseHistory.orderByChild("others_id").startAt(mProf_id_others).limit(1);
        //Firebase fh=getFirebaseHistoryForInsertSingle(mProf_ID,mProf_id_others);
        ChatHeaderAdapter ch = new ChatHeaderAdapter(ref, act, IDLayout,mProf_ID);
        return ch;
    }



    public ChatUsersProfile getUserProfileEntity(String mProf_id){
        Firebase firebaseProf=getFirebaseProfile(mProf_id);
        //final ChatUsersProfile cup;
        //=new ProfileDAO().getProfile(firebaseProf);
      //  Firebase ref = new Firebase("https://dinosaur-facts.firebaseio.com/dinosaurs");
        //Query queryRef = firebaseProf.orderByChild("prof_id").equalTo(25);
        firebaseProf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // do some stuff once
                //ChatUsersProfile facts = snapshot.getValue(ChatUsersProfile.class);
                ChatUsersProfile facts=new ChatUsersProfile(snapshot.child("prof_id").getValue().toString(),
                        snapshot.child("prof_avatar").getValue().toString(),
                        snapshot.child("prof_name").getValue().toString());
                tempUserProfile=facts;
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        //cup.getProf_avatar()


    });
        return tempUserProfile;
    }

    public ChatGroupProperty getGroupProperty(String mGroupID){
        Firebase firebaseGroup=getFirebaseChatGroupProperty(mGroupID);
        ChatGroupProperty cgp=new GroupChatDAO().getProperty(firebaseGroup);
        return cgp;
    }
    public ChatGroupMemberAdapter getGroupMember(int limit, int IDLayout, Activity act,String mGroupID) {
        Firebase firebaseGroup=getFirebaseChatGroupMembers(mGroupID);
        ChatGroupMemberAdapter cgp=new ChatGroupMemberAdapter(firebaseGroup.limit(limit),act,IDLayout);
        return cgp;
    }
    public ChatUsersContactAdapter getUsersContact(int limit, int IDLayout, Activity act, String mProfID) {
        Firebase firebaseGroup=getFirebaseUserContact(mProfID);
        ChatUsersContactAdapter cgp=new ChatUsersContactAdapter(firebaseGroup.limit(limit),act,IDLayout);
        return cgp;
    }


    public   Map<String,Object> UsersMapFromEntity(ChatUsersProfile chat){
        Map<String,Object> xx= new HashMap< String,Object>();
        xx.put(  "prof_id",chat.getProf_id());
        xx.put(  "prof_avatar",chat.getProf_avatar());
        xx.put(  "prof_name",chat.getProf_name());
        xx.put(  "status",chat.getStatus());
        xx.put(  "status_desc",chat.getStatus_desc());


        return xx;
    }
public   Map<String,Object> singleChatMapFromEntity(Chat chat){
                Map<String,Object> xx= new HashMap< String,Object>();
                xx.put(  "message",chat.getMessage());
                xx.put(  "author",chat.getAuthor());
               xx.put(  "date",chat.getDate().getTime());
             xx.put(  "prof_id",chat.getProf_id());
                xx.put(  "from",chat.getFrom());
             xx.put(  "message_type",chat.getMessage_type());
             xx.put(  "image_url",chat.getImage_url());
             xx.put(  "title_url",chat.getTitle_url());
             xx.put(  "descrip_url",chat.getDescrip_url());
             xx.put(  "url",chat.getUrl());
              xx.put(  "old",chat.getOld());
               xx.put(  "self",chat.getSelf());
                       xx.put(  "sent",chat.getSent());
                               xx.put(  "tickstate",chat.getTickstate());
                xx.put("otherKeyMessage",chat.getOtherKeyMessage());
                xx.put("selfKeyMessage",chat.getSelfKeyMessage());
                xx.put("selfKeyMessage",chat.getSelfKeyMessage());


    return xx;
}

    /**
     * Fungsi untuk mengubah entity menjadi adapter
     * @param chatHistory  diisi entity history chat yang akan diubah menjadi map
     * @return
     */
    public   Map<String,Object> HistoryChatMapFromEntity(ChatHistory chatHistory){
        Map<String,Object> historyChatperson1= new HashMap< String,Object>();
        historyChatperson1.put("from",chatHistory.getFrom());
        historyChatperson1.put("last_message",chatHistory.getLast_message());
        historyChatperson1.put("name",chatHistory.getName());
        historyChatperson1.put("type",chatHistory.getType());
        historyChatperson1.put("date",chatHistory.getDate().getTime());
        historyChatperson1.put("tickstate",chatHistory.getTickstate());
        historyChatperson1.put("last_key_message",chatHistory.getLast_key_message());

        historyChatperson1.put(  "is_typing", chatHistory.getIs_typing() );
        historyChatperson1.put("others_id",chatHistory.getOthers_id());


        return historyChatperson1;
    }

    public   Map<String,Object> HistoryChatMapFromSnapshotSetisTyping(DataSnapshot chatHistory,String is_typing){
        Map<String,Object> historyChatperson1= new HashMap< String,Object>();

        historyChatperson1.put("from",chatHistory.child("from").getValue());
        historyChatperson1.put("last_message",chatHistory.child("last_message").getValue());
        historyChatperson1.put("name",chatHistory.child("name").getValue());
        historyChatperson1.put("type",chatHistory.child("type").getValue());
        historyChatperson1.put("date",chatHistory.child("date").getValue());
        historyChatperson1.put("tickstate",chatHistory.child("tickstate").getValue());
        historyChatperson1.put("last_key_message",chatHistory.child("last_key_message").getValue());

        historyChatperson1.put(  "is_typing", is_typing );
        historyChatperson1.put("others_id",chatHistory.child("others_id").getValue());


        return historyChatperson1;
    }


    //==================Firebase Get Data================================================


    //==================Firebase Action=================================================

public void updateTickState(Chat chat,final String mProfID,final String mProfIDOthers){
    String KeyMessage=chat.getSelfKeyMessage();
    Firebase fbCHat1=getFirebaseSingleChatWithKey(KeyMessage, mProfID, mProfIDOthers);
    chat.setTickstate("2");
    Map<String,Object> singleChat1 = singleChatMapFromEntity(chat);
    updateHistoryChat(mProfID,mProfIDOthers,chat.getMessage(),"single",chat.getDate().getTime(),"2",
            chat.getSelfKeyMessage(),chat.getOtherKeyMessage(),"0");
    fbCHat1.updateChildren(singleChat1);

    fbCHat1.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            Chat post = snapshot.getValue(Chat.class);
            String keyMessageOthers=post.getOtherKeyMessage();
            Firebase fbCHat2=getFirebaseSingleChatWithKey(keyMessageOthers,  mProfIDOthers,mProfID);
            post.setTickstate("2");

            Map<String,Object> singleChat2 = singleChatMapFromEntity(post);
            fbCHat2.updateChildren(singleChat2);
        }


        @Override
        public void onCancelled(FirebaseError firebaseError) {
            System.out.println("The read failed: " + firebaseError.getMessage());
        }
    });



}
    public void addContact(final String prof_id_session, final String prof_id_page){
              ChatProfileContact facts=new ChatProfileContact(prof_id_session);
                Firebase addContactOthers=getFirebaseUserAddContact(prof_id_page,prof_id_session);
                addContactOthers.setValue(facts);
                 ChatProfileContact factsx=new ChatProfileContact(prof_id_page );
                Firebase addContactsSession=getFirebaseUserAddContact(prof_id_session,prof_id_page);
                addContactsSession.setValue(factsx);

    }

public  void CreateGroup(String GroupName,String GroupAvatar,String Prof_ID_Creator,String Prof_Name_Creator){
    Firebase fbg=getFirebaseChatGroupRoot();
    Firebase groupAdd=fbg.push();
    ChatGroupProperty cgp=new ChatGroupProperty(groupAdd.getKey(),GroupName,GroupAvatar,
            Prof_ID_Creator,Prof_Name_Creator,new Timestamp(System.currentTimeMillis()));
    groupAdd.setValue(cgp);

}
    public void addUser(ChatUsersProfile users){
        Firebase fb=getFirebaseProfile(users.getProf_id());

        Map<String,Object> datauser = UsersMapFromEntity(users);
//        new HashMap< String,Object>();
//        datauser.put("prof_id",users.getProf_id());
//        datauser.put("prof_avatar",users.getProf_avatar());
//        datauser.put("prof_name",users.getProf_name());
       fb.setValue(datauser);

    }

    public void updateUser(ChatUsersProfile users){
        Firebase fb=getFirebaseProfile(users.getProf_id());

        Map<String,Object> datauser = UsersMapFromEntity(users);
//        new HashMap< String,Object>();
//        datauser.put("prof_id",users.getProf_id());
//        datauser.put("prof_avatar",users.getProf_avatar());
//        datauser.put("prof_name",users.getProf_name());



        fb.updateChildren(datauser);

    }


    public void removeContact(String prof_id_session, String prof_id_page){
        removeConversation(prof_id_session,prof_id_page);

        Firebase addContactOthers=getFirebaseUserAddContact(prof_id_page,prof_id_session);
        addContactOthers.setValue(null);

        Firebase addContactsSession=getFirebaseUserAddContact(prof_id_session,prof_id_page);
        addContactsSession.setValue(null);

    }
    public void removeConversation(  final String Prof_ID,   final String Prof_ID_Others ){


        Firebase mFirebaseChatPerson1,mFirebaseChatPerson2,mFirebaseHistoryChatPerson1,mFirebaseHistoryChatPerson2;
        mFirebaseChatPerson1 = getFirebaseChatSingle(Prof_ID,Prof_ID_Others);
        mFirebaseChatPerson2 = getFirebaseChatSingle(Prof_ID_Others,Prof_ID);

        mFirebaseHistoryChatPerson1 = getFirebaseHistoryForInsertSingle(Prof_ID,Prof_ID_Others);
        mFirebaseHistoryChatPerson2 = getFirebaseHistoryForInsertSingle(Prof_ID_Others,Prof_ID);



        mFirebaseChatPerson1.setValue(null);
        mFirebaseChatPerson2.setValue(null);

        mFirebaseHistoryChatPerson1.setValue(null);
        mFirebaseHistoryChatPerson2.setValue(null);




    }

public void updateSingleMessageChat(Chat message,String prof_id,String prof_id_others){
    Firebase mFirebaseChatPerson1,mFirebaseChatPerson2 ;

    mFirebaseChatPerson1 = getFirebaseChatSingle(prof_id,prof_id_others);
    mFirebaseChatPerson2 = getFirebaseChatSingle(prof_id,prof_id);

    Firebase xchat=  mFirebaseChatPerson1.child(message.getSelfKeyMessage());


    Chat message2=message;

    Firebase xchat2=  mFirebaseChatPerson1.child(message.getOtherKeyMessage());


    String key1=xchat.getKey();
    String key2=xchat2.getKey();


    message.setSelfKeyMessage(key1);
    message2.setSelfKeyMessage(key2);



    message.setOtherKeyMessage(key2);
    message2.setOtherKeyMessage(key1);

    Map<String,Object> mapchat=singleChatMapFromEntity(message);
    Map<String,Object> mapchat2=singleChatMapFromEntity(message2);


    xchat.updateChildren(mapchat);
    xchat2.updateChildren(mapchat2);

}
    public void removeSingleChat(final String keyMessageProfID, final String Prof_ID, String OtherKeyMessage, final String Prof_ID_Others ){


        Firebase mFirebaseChatPerson1,mFirebaseChatPerson2,mFirebaseHistoryChatPerson1,mFirebaseHistoryChatPerson2;
        mFirebaseChatPerson1 = getFirebaseChatSingle(Prof_ID,Prof_ID_Others);
        mFirebaseChatPerson2 = getFirebaseChatSingle(Prof_ID_Others,Prof_ID);


              final Firebase xchat=  mFirebaseChatPerson1.child(keyMessageProfID);
            xchat.setValue(null);





            Firebase xchat2= mFirebaseChatPerson2.child(OtherKeyMessage);

            xchat2.setValue(null);


        Query queryRef = mFirebaseChatPerson1.orderByChild("date").limitToLast(2);
        removeHistoryChat(Prof_ID,Prof_ID_Others);
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue()==null){
                    removeHistoryChat(Prof_ID,Prof_ID_Others);
                }else{
                    System.out.println("##History Count :" + snapshot.getChildrenCount() + "");
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        Chat post = postSnapshot.getValue(Chat.class);
                        updateHistoryChat(Prof_ID,Prof_ID_Others,post.getMessage(),post.getMessage_type(),post.getDate().getTime(),
                                post.getTickstate(),post.getSelfKeyMessage(),post.getOtherKeyMessage(),"0");

                        //    System.out.println(post.getAuthor() + " - " + post.getTitle());
                    }

}
                }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });




    }

    public void sendSingleMessage(String message,String mProfID,String mProfIDOthers, String message_type,
                                  String image_url,
                                  String title_url,
                                  String descrip_url,
                                  String url,
                                  String old,
                                  String self,
                                  String sent,
                                  String tickstate) {
        Firebase mFirebaseChatPerson1,mFirebaseChatPerson2,mFirebaseHistoryChatPerson1,mFirebaseHistoryChatPerson2;
        mFirebaseChatPerson1 = getFirebaseChatSingle(mProfID,mProfIDOthers);
        mFirebaseChatPerson2 = getFirebaseChatSingle(mProfIDOthers,mProfID);
      //  mFirebaseHistoryChatPerson1 = getFirebaseHistoryForInsertSingle(mProfID,mProfIDOthers);
     //   mFirebaseHistoryChatPerson2 = getFirebaseHistoryForInsertSingle(mProfIDOthers,mProfID);

        if (!message.equals("")) {
            // Create our 'model', a Chat object
           // Chat chat = new Chat(message, mProfID,new Timestamp(System.currentTimeMillis()));
            Timestamp date=new Timestamp(System.currentTimeMillis());
            Chat chat = new Chat(message, mProfID,date
                    ,mProfIDOthers,
                      message_type,
                      image_url,
                      title_url,
                      descrip_url,
                      url,
                      old,
                      self,
                      sent,
                      tickstate,"",""
                    );


            Chat chat2 = new Chat(message, mProfID,date
                    ,mProfIDOthers,
                    message_type,
                    image_url,
                    title_url,
                    descrip_url,
                    url,
                    old,
                    self,
                    sent,
                    tickstate,"",""
            );

            // Create a new, auto-generated child of that chat location, and save our chat data there
            Firebase xchat=  mFirebaseChatPerson1.push();
            Firebase xchat2= mFirebaseChatPerson2.push();
            String key1=xchat.getKey();
            String key2=xchat2.getKey();


            chat.setSelfKeyMessage(key1);
            chat2.setSelfKeyMessage(key2);



            chat.setOtherKeyMessage(key2);
            chat2.setOtherKeyMessage(key1);


            xchat.setValue(chat);
            xchat2.setValue(chat2);

            updateHistoryChat(mProfID,mProfIDOthers,message,"single",date.getTime(),"1",key1,key2,"0");

            chat.setTickstate("1");
            chat2.setTickstate("1");
            xchat.setValue(chat);
            xchat2.setValue(chat2);


        }
    }

    /**
     *
     * @param mProf_id_typing prof id yang sedang mengetik
     * @param mProf_ID_Others prof id orang lain yang menerima message
     * @param is_typing value "0" = tidak sedang mengetik, "1" sedang mengetik
     */
    public void set_is_typing(final String mProf_id_typing, String mProf_ID_Others, final String is_typing){
        //get history chat another persons

        final Firebase   mFirebaseHistoryChatOthers = getFirebaseHistoryForInsertSingle(mProf_ID_Others,mProf_id_typing);
//        Query queryRef = mFirebaseChatPerson1.orderByChild("date").limitToLast(2);
 //       removeHistoryChat(Prof_ID,Prof_ID_Others);

        mFirebaseHistoryChatOthers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                 //   System.out.println("##History Count :" + snapshot.getChildrenCount() + "");
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        if(postSnapshot.exists()){
                          //  ChatHistory post = postSnapshot.getValue(ChatHistory.class);
                            //post.setIs_typing(is_typing);
                            Map<String,Object> maph=HistoryChatMapFromSnapshotSetisTyping(snapshot,is_typing);



                            mFirebaseHistoryChatOthers.updateChildren(maph);
                        }

                        //    System.out.println(post.getAuthor() + " - " + post.getTitle());


                    }
                }else{
                    ChatHistory post = new ChatHistory(mProf_id_typing,mProf_id_typing,new Timestamp(System.currentTimeMillis()),"single","");
                    post.setIs_typing(is_typing);
                    Map<String,Object> maph=HistoryChatMapFromEntity(post);
                    mFirebaseHistoryChatOthers.updateChildren(maph);
                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }
public void updateHistoryChat(String mProfID,String mProfIDOthers,String message, String type, long datetime, String tickstate,String keyMessageProfID1,
                              String keyMessageProfIDOthers,String is_typing  ){


    Firebase mFirebaseHistoryChatPerson1 = getFirebaseHistoryForInsertSingle(mProfID,mProfIDOthers);




    Firebase   mFirebaseHistoryChatPerson2 = getFirebaseHistoryForInsertSingle(mProfIDOthers,mProfID);





    Map<String,Object> historyChatperson1 = new HashMap< String,Object>();
    historyChatperson1.put("from",mProfID);
    historyChatperson1.put("last_message",message);
    historyChatperson1.put("name",mProfIDOthers);
    historyChatperson1.put("type","single");
    historyChatperson1.put("date",datetime);
    historyChatperson1.put("tickstate",tickstate);
    historyChatperson1.put("last_key_message",keyMessageProfID1);

    historyChatperson1.put(  "is_typing", is_typing );
    historyChatperson1.put("others_id",mProfIDOthers);



  //  historyChatperson1.put("unread_message",unread);


    Map<String,Object> historyChatperson2 = new HashMap< String,Object>();
    historyChatperson2.put("from",mProfID);
    historyChatperson2.put("last_message",message);
    historyChatperson2.put("name",mProfID );
    historyChatperson2.put("type","single");
    historyChatperson2.put("date",datetime);
    historyChatperson2.put("tickstate",tickstate);
    historyChatperson2.put("last_key_message",keyMessageProfIDOthers);
    historyChatperson2.put("others_id",mProfID  );
    historyChatperson2.put(  "is_typing", is_typing );
  //  historyChatperson1.put("unread_message",unread);
    mFirebaseHistoryChatPerson1.updateChildren(historyChatperson1);
    mFirebaseHistoryChatPerson2.updateChildren(historyChatperson2);



}
    public void updateHistoryChatGroups(String mProfIDHistoryOwner,String mProfIDAuthorMessage,String mGroupID,String message, String type,
                                        long datetime, String tickstate,String keyMessageProfID1,
                                  String keyMessageProfIDOthers,String is_typing  ){


        Firebase mFirebaseHistoryChatPerson1 = getFirebaseHistoryForInsertSingle(mProfIDHistoryOwner,mGroupID);




     //   Firebase   mFirebaseHistoryChatPerson2 = getFirebaseHistoryForInsertSingle(mProfIDOthers,mProfID);





        Map<String,Object> historyChatperson1 = new HashMap< String,Object>();
        historyChatperson1.put("from",mProfIDAuthorMessage);
        historyChatperson1.put("last_message",message);
        historyChatperson1.put("name",mGroupID);
        historyChatperson1.put("type","single");
        historyChatperson1.put("date",datetime);
        historyChatperson1.put("tickstate",tickstate);
        historyChatperson1.put("last_key_message",keyMessageProfID1);

        historyChatperson1.put(  "is_typing", is_typing );
        historyChatperson1.put("others_id",mGroupID);



        //  historyChatperson1.put("unread_message",unread);


//        Map<String,Object> historyChatperson2 = new HashMap< String,Object>();
//        historyChatperson2.put("from",mProfID);
//        historyChatperson2.put("last_message",message);
//        historyChatperson2.put("name",mProfID );
//        historyChatperson2.put("type","single");
//        historyChatperson2.put("date",datetime);
//        historyChatperson2.put("tickstate",tickstate);
//        historyChatperson2.put("last_key_message",keyMessageProfIDOthers);
//        historyChatperson2.put("others_id",mProfID  );
//        historyChatperson2.put(  "is_typing", is_typing );
        //  historyChatperson1.put("unread_message",unread);
        mFirebaseHistoryChatPerson1.updateChildren(historyChatperson1);
//        mFirebaseHistoryChatPerson2.updateChildren(historyChatperson2);



    }
    public void removeHistoryChat(String mProfID,String mProfIDOthers){
        Firebase mFirebaseHistoryChatPerson1 = getFirebaseHistoryForInsertSingle(mProfID,mProfIDOthers);
        Firebase   mFirebaseHistoryChatPerson2 = getFirebaseHistoryForInsertSingle(mProfIDOthers,mProfID);
        mFirebaseHistoryChatPerson1.removeValue();
        mFirebaseHistoryChatPerson2.removeValue();

    }

    public void setForTextTypingStatus(String mProf_id,String mProf_id_others,final Activity act){
        Firebase fh=getFirebaseHistoryForInsertSingle(mProf_id,mProf_id_others);

        fh.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    //   System.out.println("##History Count :" + snapshot.getChildrenCount() + "");
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        if(postSnapshot.exists()){
                         if(  postSnapshot.child("is_typing").getValue()!=null){
                             if( postSnapshot.child("is_typing").getValue().equals("")
                                     ||postSnapshot.child("is_typing").getValue().equals("0")){
                                 act.setTitle(act.getTitle());
                             }else{

                                 act.setTitle(act.getTitle()+" lainnya sedang mengetik ");

                             }
                         }


                            //  ChatHistory post = postSnapshot.getValue(ChatHistory.class);
                            //post.setIs_typing(is_typing);
                           // Map<String,Object> maph=HistoryChatMapFromSnapshotSetisTyping(snapshot,is_typing);



                          //  mFirebaseHistoryChatOthers.updateChildren(maph);
                        }

                        //    System.out.println(post.getAuthor() + " - " + post.getTitle());


                    }
                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


    public void joinGroup(String GroupKey,String Prof_id,Timestamp JoinDate, String prof_name,
                          String prof_avatar,
                          Timestamp joinDate){
        //[##JOIN##]
        String defaultMessage="[##JOIN##] joined group";

        Firebase fbg=getFirebaseChatGroupRoot();
        Firebase groupAdd=fbg.child(GroupKey + "/Members/" + Prof_id);
        ChatGroupMember cgp=new ChatGroupMember( Prof_id,prof_name,prof_avatar,joinDate);
        groupAdd.setValue(cgp);


    }

    public void sendGroupMessage(String message,String mProfIDSender,String mGroupID, String message_type,
                                  String image_url,
                                  String title_url,
                                  String descrip_url,
                                  String url,
                                  String old,
                                  String self,
                                  String sent,
                                  String tickstate) {
        Firebase mFirebaseChatGroup,mFirebaseChatMembers,mFirebaseHistoryChatPerson1,mFirebaseHistoryChatPerson2;
        mFirebaseChatGroup = getFirebaseChatGroupMessage( mGroupID);
     //   mFirebaseChatPerson2 = getFirebaseChatSingle(mGroupID,mProfID);
        //  mFirebaseHistoryChatPerson1 = getFirebaseHistoryForInsertSingle(mProfID,mProfIDOthers);
        //   mFirebaseHistoryChatPerson2 = getFirebaseHistoryForInsertSingle(mProfIDOthers,mProfID);

        if (!message.equals("")) {
            // Create our 'model', a Chat object
            // Chat chat = new Chat(message, mProfID,new Timestamp(System.currentTimeMillis()));
            Timestamp date=new Timestamp(System.currentTimeMillis());
            Chat chat = new Chat(message, mProfIDSender,date
                    ,mGroupID,
                    message_type,
                    image_url,
                    title_url,
                    descrip_url,
                    url,
                    old,
                    self,
                    sent,
                    tickstate,"",""
            );




            // Create a new, auto-generated child of that chat location, and save our chat data there
            Firebase xchat=  mFirebaseChatGroup.push();
          //  Firebase xchat2= mFirebaseChatPerson2.push();
            String key1=xchat.getKey();
      //      String key2=xchat2.getKey();


            chat.setSelfKeyMessage(key1);
          //  chat2.setSelfKeyMessage(key2);



           // chat.setOtherKeyMessage(key2);
         //   chat2.setOtherKeyMessage(key1);


            xchat.setValue(chat);
           // xchat2.setValue(chat2);

            //updateHistoryChatGroups(mProfIDSender,mGroupID,message,"Groups",date.getTime(),"1",key1,"","0");

            chat.setTickstate("1");
           // chat2.setTickstate("1");
            xchat.setValue(chat);
          //  xchat2.setValue(chat2);


        }
    }

    //==================Firebase Action=================================================

}
