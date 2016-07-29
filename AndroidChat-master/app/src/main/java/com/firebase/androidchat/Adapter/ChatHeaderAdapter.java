package com.firebase.androidchat.Adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.androidchat.Entity.ChatHistory;
import com.firebase.androidchat.R;

import com.firebase.client.Query;


/**
 * Created by Administrator on 29/07/2016.
 */
public class ChatHeaderAdapter extends FirebaseListAdapter<ChatHistory> {


    private Activity act;
    private String prof_ID_Login;
    // private String prof_ID_Others;
    public ChatHeaderAdapter(Query ref, Activity activity, int layout , String mProf_ID) {
        super(ref, ChatHistory.class, layout, activity);
        this.act=activity;
        this.prof_ID_Login=mProf_ID;

    }

    @Override
    protected void populateView(View view, ChatHistory chat) {

        TextView tx=   (TextView)view.findViewById(R.id.textData);


        if( chat.getIs_typing()!=null) {
                if (chat.getIs_typing().equals("1")) {
                    tx.setText("lawan bicara sedang mengetik...");
                } else {
                    tx.setText("");


            }
        }

        //+ ""+  getTimestamp(chat.getDate()) );
        // If the message was sent by this user, color it differently

    }

}
