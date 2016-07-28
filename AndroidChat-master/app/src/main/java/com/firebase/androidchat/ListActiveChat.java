package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
//import android.widget.Toast;

import com.firebase.androidchat.Adapter.ChatHistoryListAdapter;
import com.firebase.androidchat.Entity.ChatHistory;
import com.firebase.androidchat.util.FirebaseHelper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ListActiveChat extends Activity {
  private Firebase mFirebaseHistory;
    private FirebaseHelper mfh=new FirebaseHelper();

    private ValueEventListener mConnectedListener;
    private ChatHistoryListAdapter mChatListAdapter;
     private String mProf_id;
     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_active_chat);
        listView=(ListView)findViewById(R.id.list);
        Intent intent = getIntent();
        String prof_id = intent.getStringExtra("prof_id");
        mProf_id=prof_id;

      mFirebaseHistory=mfh.getFirebaseHistory(mProf_id);

        refreshList();
        mConnectedListener = mFirebaseHistory.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
           //         Toast.makeText(ListActiveChat.this, "Connected to Firebase ", Toast.LENGTH_SHORT).show();
                } else {
            //        Toast.makeText(ListActiveChat.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
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
                final ChatHistory user =(ChatHistory) mChatListAdapter.getItem(position);
                if(user.getType().equals("single")){
                    Intent intent = new Intent(ListActiveChat.this, MainActivity.class);
                    //EditText editText = (EditText) findViewById(R.id.edit_message);
                    intent.putExtra("prof_id", mProf_id);
                    intent.putExtra("prof_id_Others", user.getName());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ListActiveChat.this, GroupChat.class);
                    //EditText editText = (EditText) findViewById(R.id.edit_message);
                    intent.putExtra("prof_id", mProf_id);
                    intent.putExtra("groupName", user.getName());
                    startActivity(intent);
                }

            }
        });
        Button btnListCOntact=(Button)findViewById(R.id.btnContact);
        btnListCOntact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActiveChat.this, ContactListActivity.class);
                //EditText editText = (EditText) findViewById(R.id.edit_message);
                intent.putExtra("prof_id", mProf_id);
             //   intent.putExtra("usernameOthers", user.getName());
                startActivity(intent);
            }
        });
    }


    private void refreshList(){
 //    final ListView listView = getListView();

    listView=(ListView)findViewById(R.id.list);
        mChatListAdapter =mfh.getFirstHistoryAdapter(50,R.layout.chat_history,this,mProf_id);
                //new ChatHistoryListAdapter(mFirebaseHistory.limit(50), this, R.layout.chat_history );
        listView.setAdapter(mChatListAdapter);
        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mChatListAdapter.getCount() - 1);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseHistory
                .getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mChatListAdapter.cleanup();
    }


}
