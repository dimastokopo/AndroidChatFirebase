package com.firebase.androidchat.Adapter;

import android.app.Activity;
import android.view.View;

import com.firebase.androidchat.Entity.ChatGroupMember;
import com.firebase.client.Query;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ChatGroupMemberAdapter extends FirebaseListAdapter<ChatGroupMember>{



    public ChatGroupMemberAdapter(Query ref, Activity activity, int layout ) {
        super(ref, ChatGroupMember.class, layout, activity);

    }

    @Override
    protected void populateView(View view, ChatGroupMember chat) {
        // Map a Chat object to an entry in our listview
      /*  String from = chat.getFrom();
        TextView fromText = (TextView) view.findViewById(R.id.name);
        fromText.setText(from + ": ");
        TextView lastmessage=(TextView)view.findViewById(R.id.lastmessage);
        lastmessage.setText(chat.getLast_message());

        TextView type=(TextView)view.findViewById(R.id.type);
        type.setText(chat.getType());
        // If the message was sent by this user, color it differently
*/
    }

}
