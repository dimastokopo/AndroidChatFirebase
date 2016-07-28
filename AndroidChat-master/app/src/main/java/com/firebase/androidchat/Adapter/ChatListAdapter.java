package com.firebase.androidchat.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.androidchat.Entity.Chat;
import com.firebase.androidchat.Entity.ChatUsersProfile;
import com.firebase.androidchat.R;
import com.firebase.androidchat.util.FirebaseHelper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Console;

/**
 * @author greg
 * @since 6/21/13
 *
 * This class is an example of how to use FirebaseListAdapter. It uses the <code>Chat</code> class to encapsulate the
 * data for each individual chat message
 */
public class ChatListAdapter extends FirebaseListAdapter<Chat> {

    // The mUsername for this client. We use this to indicate which messages originated from this user
    private String mUsername;
    private Activity act;

    public ChatListAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, Chat.class, layout, activity);
        this.mUsername = mUsername;
        this.act=activity;
    }

    /**
     * Bind an instance of the <code>Chat</code> class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single <code>Chat</code> instance that represents the current data to bind.
     *
     * @param viewx A view instance corresponding to the layout we passed to the constructor.
     * @param chatx An instance representing the current state of a chat message
     */
    @Override
    protected void populateView(View viewx, Chat chatx) {
        final View view=viewx;
        final Chat chat=chatx;
final String prof_id_login=this.mUsername;
        // Map a Chat object to an entry in our listview
        final String author = chat.getAuthor();
        Firebase firebaseProf=new FirebaseHelper().getFirebaseProfile(author);

        firebaseProf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // do some stuff once
                //  ChatUsersProfile facts = snapshot.getValue(ChatUsersProfile.class);
                if (snapshot!=null){
                    try{
                        ChatUsersProfile facts=new ChatUsersProfile(snapshot.child("prof_id").getValue().toString(),
                                snapshot.child("prof_avatar").getValue().toString(),
                                snapshot.child("prof_name").getValue().toString());
                        TextView authorText = (TextView) view.findViewById(R.id.author);
                        ImageView imgAvatar=(ImageView)view.findViewById(R.id.imageViewUserAvatar);

                        authorText.setText(facts.getProf_name() + ": ");
                        TextView textdate=(TextView)view.findViewById(R.id.date);
                        textdate.setText(chat.getDate().toString());
                        // If the message was sent by this user, color it differently
                        if (facts.getProf_id() != null && facts.getProf_id().equals(mUsername)) {
                            authorText.setTextColor(Color.RED);
                        } else {
                            authorText.setTextColor(Color.BLUE);
                        }

                        Picasso.with(act.getApplicationContext())
                                .load(facts.getProf_avatar())
                                .into(imgAvatar);
                        ((TextView) view.findViewById(R.id.message)).setText(chat.getMessage());
                    if(!author.equals(prof_id_login)){
                        //update chat milik lawan bicara tickstate menjadi 2
                        FirebaseHelper fbh=new FirebaseHelper();
                       fbh.updateTickState(chat ,prof_id_login,author);


                    }
                    }catch (NullPointerException nex){
                        Log.e("error",nex.getMessage());
                    }
                }

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
            //cup.getProf_avatar()


        });




    }
}
