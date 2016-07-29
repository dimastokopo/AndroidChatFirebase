package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.androidchat.util.FirebaseHelper;

public class NewGroup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
Intent it=getIntent();
        String prof_id=it.getStringExtra("prof_id");
        Button btnCreate=(Button) findViewById(R.id.btnCreate);
        final EditText txtgroupname=(EditText)findViewById(R.id.txtGroupName);
        final EditText txtgroupAvatar=(EditText)findViewById(R.id.txtgroupAvatar);
        final EditText txtprof_id_creator=(EditText)findViewById(R.id.txtCreatedProfID);
        txtprof_id_creator.setText(prof_id);
        EditText txtProf_name=(EditText)findViewById(R.id.txtprof_name);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseHelper fbh=new FirebaseHelper();
                fbh.CreateGroup(txtgroupname.getText().toString(),
                        txtgroupAvatar.getText().toString(),txtprof_id_creator.getText().toString(),"");
            }
        });

    }
}
