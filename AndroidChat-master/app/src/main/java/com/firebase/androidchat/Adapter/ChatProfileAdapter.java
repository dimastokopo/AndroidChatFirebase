package com.firebase.androidchat.Adapter;


import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.androidchat.Entity.ChatHistory;
import com.firebase.androidchat.Entity.ChatUsersProfile;
import com.firebase.androidchat.R;
import com.firebase.client.Query;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ChatProfileAdapter  extends FirebaseListAdapter<ChatUsersProfile> {
    public ChatProfileAdapter(Query ref, Activity activity, int layout ) {
        super(ref, ChatUsersProfile.class, layout, activity);

    }

    @Override
    protected void populateView(View view, ChatUsersProfile chat) {
        // Map a Chat object to an entry in our listview
       /* String from = chat.getFrom();
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
