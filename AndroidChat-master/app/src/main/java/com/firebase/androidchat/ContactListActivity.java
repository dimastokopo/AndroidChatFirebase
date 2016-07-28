package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.androidchat.Adapter.ChatUsersContactAdapter;
import com.firebase.androidchat.Entity.ChatHistory;
import com.firebase.androidchat.Entity.ChatProfileContact;
import com.firebase.androidchat.Entity.ChatUsersContact;
import com.firebase.androidchat.util.FirebaseHelper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ContactListActivity extends Activity {
    private String mProf_id;
    private Firebase mFirebaseContact;
    ChatUsersContactAdapter mContactAdapter;
    private FirebaseHelper mfh=new FirebaseHelper();
    ListView listView;

    private ValueEventListener mConnectedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        listView=(ListView)findViewById(R.id.list);
        Intent intent = getIntent();
        String prof_id = intent.getStringExtra("prof_id");
        mProf_id=prof_id;

        mFirebaseContact=mfh.getFirebaseUserContact(mProf_id);
        refreshList();
        mConnectedListener = mFirebaseContact.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
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

                 final ChatProfileContact user =(ChatProfileContact) mContactAdapter.getItem(position);

                    Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                    intent.putExtra("prof_id", mProf_id);
                    intent.putExtra("prof_id_Others",user.getProf_id() );
                    startActivity(intent);


            }
        });
    }


    private void refreshList(){
        //    final ListView listView = getListView();

        listView=(ListView)findViewById(R.id.list);
        mContactAdapter =mfh.getUsersContact(50,R.layout.chat_contact,this,mProf_id);
        //new ChatHistoryListAdapter(mFirebaseHistory.limit(50), this, R.layout.chat_history );
        listView.setAdapter(mContactAdapter);

        mContactAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mContactAdapter.getCount() - 1);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseContact
                .getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mContactAdapter.cleanup();
    }


}
