package com.forgamers.mobile.accelerator.game;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    ImageButton logoutBtn;
    TextView userName,userEmail/*,userId*/;
    ImageView profileImage;
    TextView userText;
    TextView privateText;
    TextView unsubscribeText;
    TextView textView;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_text_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_btnback);
        Drawable d=getResources().getDrawable(R.drawable.ic_background);
        getSupportActionBar().setBackgroundDrawable(d);

        logoutBtn=(ImageButton)findViewById(R.id.logoutBtn);
        userName=(TextView)findViewById(R.id.name);
        userEmail=(TextView)findViewById(R.id.email);
        //userId=(TextView)findViewById(R.id.userId);
        profileImage=(ImageView)findViewById(R.id.profileImage);
        userText=(TextView)findViewById(R.id.user_link_text);
        privateText=(TextView)findViewById(R.id.private_link_text);
        textView=(TextView)findViewById(R.id.tittle);
        unsubscribeText=(TextView)findViewById(R.id.cancel_link_text);
        textView.setText("帳號資訊");

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        setProfile();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,UserAgreeActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ProfileActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        privateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,PrivateActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ProfileActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        unsubscribeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        CustomDialogClass cdd=new CustomDialogClass(ProfileActivity.this);
                        cdd.show();
                        //gotoMainActivity();
                    }
                });
        Toast.makeText(this, "登出成功!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setProfile()
    {
        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("photourl")).into(profileImage);
        userName.setText(intent.getStringExtra("displayname"));
        userEmail.setText(intent.getStringExtra("email"));
    }

    private void gotoMainActivity(){
        Intent intent=new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ProfileActivity.this);
        startActivity(intent,options.toBundle());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}