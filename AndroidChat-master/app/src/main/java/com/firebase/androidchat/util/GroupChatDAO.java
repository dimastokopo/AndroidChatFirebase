package com.firebase.androidchat.util;

import com.firebase.androidchat.Entity.ChatGroupMember;
import com.firebase.androidchat.Entity.ChatGroupProperty;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Administrator on 25/07/2016.
 */
public class GroupChatDAO {


    private ChatGroupProperty cgp;

    public ChatGroupProperty getProperty(Firebase ref){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ChatGroupProperty post = postSnapshot.getValue(ChatGroupProperty.class);
                    cgp=post;
                    //  System.out.println(post.getAuthor() + " - " + post.getTitle());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        return cgp;
    }
}
