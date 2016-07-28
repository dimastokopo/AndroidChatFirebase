package com.firebase.androidchat.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.androidchat.Entity.ChatProfileContact;
import com.firebase.androidchat.Entity.ChatUsersContact;
import com.firebase.androidchat.Entity.ChatUsersProfile;
import com.firebase.androidchat.R;
import com.firebase.androidchat.util.FirebaseHelper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ChatUsersContactAdapter extends FirebaseListAdapter<ChatProfileContact>{

    private Context context;
    private Activity act;
    public ChatUsersContactAdapter(Query ref, Activity activity, int layout ) {
        super(ref, ChatProfileContact.class, layout, activity);
        this.context=activity.getApplicationContext();
        this.act=activity;

    }

    @Override
    protected void populateView(View viewx, ChatProfileContact chatx) {
        // Map a Chat object to an entry in our listview
        final View view=viewx;
        final ChatProfileContact chat=chatx;
       TextView txtStatusMessage=(TextView)view.findViewById(R.id.textViewStatusMessage);
        txtStatusMessage.setText(chat.getProf_id());
        Firebase firebaseProf=new FirebaseHelper().getFirebaseProfile(chat.getProf_id());

        firebaseProf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ChatUsersProfile facts=new ChatUsersProfile(snapshot.child("prof_id").getValue().toString(),
                        snapshot.child("prof_avatar").getValue().toString(),
                        snapshot.child("prof_name").getValue().toString());
                 ImageView imgAvatar=(ImageView)view.findViewById(R.id.imageViewUserAvatar);

                TextView txtUsername=(TextView)view.findViewById(R.id.textViewUserName);
                txtUsername.setText(facts.getProf_name());
                Picasso.with(context)
                        .load(facts.getProf_avatar())
                        .into(imgAvatar);


            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
            //cup.getProf_avatar()


        });



    }

}
