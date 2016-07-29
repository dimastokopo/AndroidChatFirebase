package com.firebase.androidchat.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.androidchat.Entity.ChatHistory;
import com.firebase.androidchat.Entity.ChatUsersProfile;
import com.firebase.androidchat.R;
import com.firebase.androidchat.util.FirebaseHelper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 22/07/2016.
 */
public class ChatHistoryListAdapter extends FirebaseListAdapter<ChatHistory> {


private Activity act;
    private String prof_ID_Login;
   // private String prof_ID_Others;
    public ChatHistoryListAdapter(Query ref, Activity activity, int layout ,String mProf_ID) {
        super(ref, ChatHistory.class, layout, activity);
        this.act=activity;
        this.prof_ID_Login=mProf_ID;

    }

    @Override
    protected void populateView(View view, ChatHistory chat) {
        if(!chat.getLast_message().equals("")&&!chat.getTickstate().equals("")){
            // Map a Chat object to an entry in our listview
            final ChatHistory chatx=chat;
            final View views=view;
            String from = chat.getFrom();
            final  TextView fromText = (TextView) view.findViewById(R.id.name);
            // fromText.setText(from + ": ");
            final    TextView lastmessage=(TextView)view.findViewById(R.id.lastmessage);

            lastmessage.setText(chat.getLast_message());
            final TextView type=(TextView)view.findViewById(R.id.type);
            type.setText(" Type chat : "+chat.getType() +" waktu "+  (chat.getDate()));

            final FirebaseHelper fbh=new FirebaseHelper();
            Firebase firebaseProf=fbh.getFirebaseProfile(chat.getName());

            firebaseProf.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    // do some stuff once
                    //  ChatUsersProfile facts = snapshot.getValue(ChatUsersProfile.class);
                    if (snapshot.exists()){
                        ChatUsersProfile facts=new ChatUsersProfile(snapshot.child("prof_id").getValue().toString(),
                                snapshot.child("prof_avatar").getValue().toString(),
                                snapshot.child("prof_name").getValue().toString());

                        fromText.setText(facts.getProf_name()+"  ");
                        // TextView authorText = (TextView) view.findViewById(R.id.author);
                        final ImageView imgAvatar=(ImageView)views.findViewById(R.id.imageViewUserAvatar);
                        Picasso.with(act.getApplicationContext())
                                .load(facts.getProf_avatar())
                                .into(imgAvatar);
                        if(!chatx.getFrom().equals(prof_ID_Login)) {
                            Firebase refUnread = fbh.getFirebaseChatSingle(chatx.getOthers_id(), prof_ID_Login);
                            Query queryRef = refUnread.orderByChild("tickstate").equalTo("1");
                            queryRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    // System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                                    Long x = snapshot.getChildrenCount();

                                    Long obj2 = new Long(0);
                                    int retval = x.compareTo(obj2);

                                    if (retval > 0) {
                                        fromText.setBackgroundColor(Color.GREEN);
                                    }

                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    System.out.println("The read failed: " + firebaseError.getMessage());
                                }
                            });
                        }

                    }

                }



                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
                //cup.getProf_avatar()


            });
            if( chatx.getIs_typing()!=null) {
                if (chatx.getIs_typing().equals("1")) {
                    type.setText("  sedang mengetik waktu " + (chat.getDate()));

                } else {
                    type.setText(" Type chat : " + chat.getType() + " waktu " + (chat.getDate()));

                }
            }
        }

        //+ ""+  getTimestamp(chat.getDate()) );
        // If the message was sent by this user, color it differently

    }


    public String getTimestamp(long date){

        Date datex = new Date(date);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(datex);

    }
}

