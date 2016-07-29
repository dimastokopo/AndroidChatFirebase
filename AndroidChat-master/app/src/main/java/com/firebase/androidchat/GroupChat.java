package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.database.DataSetObserver;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.androidchat.Adapter.ChatListAdapter;
import com.firebase.androidchat.Entity.Chat;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.sql.Timestamp;

public class GroupChat extends Activity {
  //  private static final String FIREBASE_URL_Users ="https://scorching-fire-8526.firebaseio.com/Users/";
    private static final String FIREBASE_URL_Groups ="https://scorching-fire-8526.firebaseio.com/Groups/";

    //"https://android-chat.firebaseio-demo.com";
     //"https://android-chat.firebaseio-demo.com";

    private String mUsername,mUsernameOthers,mGroupName;
    private Firebase mFirebaseRef,mFirebaseRef2;
    private Firebase mFirebaseChatPerson1,mFirebaseChatPerson2,mFirebaseHistoryChatPerson1,mFirebaseHistoryChatPerson2;
    private ValueEventListener mConnectedListener;
    private ChatListAdapter mChatListAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        listView=(ListView)findViewById(R.id.list);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mGroupName = intent.getStringExtra("groupName");

        String FirebaseGroupChat=FIREBASE_URL_Groups+mGroupName;


        mFirebaseRef = new Firebase(FirebaseGroupChat).child("Messages");



        EditText inputText = (EditText) findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });


        refreshList();
        mConnectedListener = mFirebaseChatPerson1.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    //        Toast.makeText(MainActivity.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    //      Toast.makeText(MainActivity.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });
    }



    @Override
    public void onStop() {
        super.onStop();
        mFirebaseChatPerson1.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mChatListAdapter.cleanup();
    }
    private void sendMessage() {
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            // Create our 'model', a Chat object
            Chat chat = new Chat(input, mUsername,new Timestamp(System.currentTimeMillis()));
            // Create a new, auto-generated child of that chat location, and save our chat data there

            mFirebaseRef.push().setValue(chat);

            inputText.setText("");
        }


/*
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    BlogPost post = postSnapshot.getValue(BlogPost.class);
                    System.out.println(post.getAuthor() + " - " + post.getTitle());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        */
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(GroupChat.this, ListActiveChat.class);


        //EditText editText = (EditText) findViewById(R.id.edit_message);
        intent.putExtra("username", mUsername);
        startActivity(intent);

    }


    private void refreshList(){
        //   final ListView listView = getListView();
        mChatListAdapter = new ChatListAdapter(mFirebaseRef.limit(50), this, R.layout.chat_message, mUsername);
        listView.setAdapter(mChatListAdapter);
        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mChatListAdapter.getCount() - 1);
            }
        });

    }


}
