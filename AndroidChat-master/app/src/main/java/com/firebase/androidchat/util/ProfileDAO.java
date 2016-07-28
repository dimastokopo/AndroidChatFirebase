package com.firebase.androidchat.util;

import com.firebase.androidchat.Entity.ChatUsersProfile;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Administrator on 25/07/2016.
 */
public class ProfileDAO {
    private ChatUsersProfile cao;

        public ChatUsersProfile getProfile(Firebase ref){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ChatUsersProfile post = postSnapshot.getValue(ChatUsersProfile.class);
                    cao=post;
                  //  System.out.println(post.getAuthor() + " - " + post.getTitle());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
            return cao;
    }
}
