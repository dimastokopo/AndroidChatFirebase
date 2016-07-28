package com.firebase.androidchat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.androidchat.Adapter.ChatListAdapter;
import com.firebase.androidchat.Entity.Chat;
import com.firebase.androidchat.Entity.ChatHistory;
import com.firebase.androidchat.Entity.ChatUsersProfile;
import com.firebase.androidchat.util.FirebaseHelper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends Activity {

    private  Firebase mFirebaseChatPerson1 ;
    private String mProf_id,mProf_id_Others;
    private ValueEventListener mConnectedListener;
    private ChatListAdapter mChatListAdapter;
    private FirebaseHelper fbh=new FirebaseHelper();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        Intent intent = getIntent();
        String username = intent.getStringExtra("prof_id");
        mProf_id_Others = intent.getStringExtra("prof_id_Others");

        mProf_id=username;
        Firebase firebaseProf=fbh.getFirebaseProfile(mProf_id);
        firebaseProf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                 ChatUsersProfile facts=new ChatUsersProfile(snapshot.child("prof_id").getValue().toString(),
                        snapshot.child("prof_avatar").getValue().toString(),snapshot.child("prof_name").getValue().toString());
                setTitle("Chatting as " + facts.getProf_name());

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }



        });





        // Setup our input methods. Enter key on the keyboard or pushing the send button
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
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

        mFirebaseChatPerson1=fbh.getFirebaseChatSingle(mProf_id,mProf_id_Others);
      //  mFirebaseChatPerson2=fbh.getFirebaseChatSingle(mProf_id_Others,mProf_id);

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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Chat chatx = (Chat) mChatListAdapter.getItem(position);
                if (chatx.getAuthor().equals(mProf_id)) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    FirebaseHelper fbh = new FirebaseHelper();
                                    fbh.removeSingleChat(chatx.getSelfKeyMessage(), mProf_id,chatx.getOtherKeyMessage(), mProf_id_Others);


                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                }
            }
        });
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
       // final ListView listView = getListView();
        // Tell our list adapter that we only want 50 messages at a time
        mChatListAdapter = new ChatListAdapter(mFirebaseChatPerson1.limit(50), this, R.layout.chat_message, mUsername);
        listView.setAdapter(mChatListAdapter);
        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mChatListAdapter.getCount() - 1);
            }
        });

        // Finally, a little indication of connection status

    }
*/
    @Override
    public void onStop() {
        super.onStop();

        mFirebaseChatPerson1.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mChatListAdapter.cleanup();
    }
private void refreshList(){
 //   final ListView listView = getListView();
    //mChatListAdapter = fbh.getFirstChatAdapter(50,R.layout.chat_message,
    mChatListAdapter = fbh.getChatAdapter("-KNkw2QDcuhv28NN-s7t",5,R.layout.chat_message,
    this,mProf_id,mProf_id_Others);
            //new ChatListAdapter(mFirebaseChatPerson1.limit(50), this, R.layout.chat_message, mUsername);
    listView.setAdapter(mChatListAdapter);
    mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            listView.setSelection(mChatListAdapter.getCount() - 1);
        }
    });

}


    private void sendMessage() {
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            fbh.sendSingleMessage(input,mProf_id,mProf_id_Others,"10","","","","",
                    "","","","0");
            // Create our 'model', a Chat object

            inputText.setText("");
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, ListActiveChat.class);


        //EditText editText = (EditText) findViewById(R.id.edit_message);
        intent.putExtra("prof_id", mProf_id);
        startActivity(intent);
    }
}
