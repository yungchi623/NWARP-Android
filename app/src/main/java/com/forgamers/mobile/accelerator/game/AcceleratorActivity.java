package com.forgamers.mobile.accelerator.game;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class AcceleratorActivity extends AppCompatActivity{

    ImageView btnProfile,btnAccelerator;
    GifImageView ImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accelerator);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        Drawable d=getResources().getDrawable(R.drawable.ic_background);
        ImageView = findViewById(R.id.imageView);
        getSupportActionBar().setBackgroundDrawable(d);
        btnProfile = (ImageView) findViewById(R.id.profile);

        setProfileIcon();

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountIntent = getIntent();
                Intent intent=new Intent(AcceleratorActivity.this,ProfileActivity.class);

                intent.putExtra("displayname", accountIntent.getStringExtra("displayname"));
                intent.putExtra("givenname", accountIntent.getStringExtra("givenname"));
                intent.putExtra("familyname", accountIntent.getStringExtra("familyname"));
                intent.putExtra("id", accountIntent.getStringExtra("id"));
                intent.putExtra("idtoken", accountIntent.getStringExtra("idtoken"));
                intent.putExtra("email", accountIntent.getStringExtra("email"));
                intent.putExtra("photourl", accountIntent.getStringExtra("photourl"));

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AcceleratorActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        ImageView btnSupport= (ImageView) findViewById(R.id.support_items);
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AcceleratorActivity.this,SupportActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AcceleratorActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

        btnAccelerator= (ImageView) findViewById(R.id.accelerator_imgbtn);
        btnAccelerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToSubscribeActivity();
                //OnOffDisplay();
            }
        });
    }

    private void ToSubscribeActivity()
    {
        Intent accountIntent = getIntent();
        Intent intent=new Intent(AcceleratorActivity.this,SubscribeActivity.class);

        intent.putExtra("displayname", accountIntent.getStringExtra("displayname"));
        intent.putExtra("givenname", accountIntent.getStringExtra("givenname"));
        intent.putExtra("familyname", accountIntent.getStringExtra("familyname"));
        intent.putExtra("id", accountIntent.getStringExtra("id"));
        intent.putExtra("idtoken", accountIntent.getStringExtra("idtoken"));
        intent.putExtra("email", accountIntent.getStringExtra("email"));
        intent.putExtra("photourl", accountIntent.getStringExtra("photourl"));

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AcceleratorActivity.this);
        startActivity(intent,options.toBundle());
    }

    private void OnOffDisplay()
    {
        loadGif();
        ImageView.setVisibility(View.VISIBLE);
        Drawable d=getResources().getDrawable(R.drawable.ic_btnpoweron);
        btnAccelerator.setImageDrawable(d);
    }

    private void loadGif()
    {
        try{
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.speedup);
            ImageView.setImageDrawable(gifDrawable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setProfileIcon()
    {
        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("photourl")).into(btnProfile);
    }

    @Override
    public void onBackPressed() {
//        鎖住Back鍵
//        如tbtn被選的話，不執行super 就可以把Back預設行為無效
        return;
    }
}