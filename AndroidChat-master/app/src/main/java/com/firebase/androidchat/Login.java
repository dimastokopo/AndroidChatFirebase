package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.androidchat.Entity.ChatUsersProfile;
import com.firebase.androidchat.util.FirebaseHelper;

import java.util.Random;

public class Login extends Activity {
Button btnLogin,btnreg,btnConnect;
    EditText txtUsername,txtusernameReg,txtProf_id_reg,txtAvatar,txtProf_id_session,txtProf_id_page;
    private String mUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnreg=(Button)findViewById(R.id.btnReg);
        btnConnect=(Button)findViewById(R.id.btnConnect);
        txtUsername=(EditText)findViewById(R.id.Username);
        txtusernameReg=(EditText)findViewById(R.id.usernamereg);
        txtProf_id_reg=(EditText)findViewById(R.id.prof_id_reg);
        txtAvatar=(EditText)findViewById(R.id.avatarreg);
        txtProf_id_session=(EditText)findViewById(R.id.prof_id_session);
        txtProf_id_page=(EditText)findViewById(R.id.prof_id_page);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ListActiveChat.class);
                if(txtUsername.getText().equals("")){
                    setupUsername();
                }else{
                    mUsername=txtUsername.getText().toString();
                }
                //EditText editText = (EditText) findViewById(R.id.edit_message);
                intent.putExtra("prof_id", mUsername);
                startActivity(intent);
            }
        });



        btnConnect.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        FirebaseHelper fh=new FirebaseHelper();
                        fh.addContact(txtProf_id_session.getText().toString(),txtProf_id_page.getText().toString());
                    }
                }

        );

        btnreg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseHelper fh=new FirebaseHelper();

                        ChatUsersProfile up=new ChatUsersProfile(txtProf_id_reg.getText().toString(),
                                txtAvatar.getText().toString(),txtusernameReg.getText().toString());
                        fh.addUser(up);

                    }
                }
        );


    }

    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        mUsername = prefs.getString("username", null);
        if (mUsername == null) {
            Random r = new Random();
            // Assign a random user name if we don't have one saved.
            mUsername = "putraxxx" + r.nextInt(100000);
            prefs.edit().putString("username", mUsername).commit();
        }
    }
}
