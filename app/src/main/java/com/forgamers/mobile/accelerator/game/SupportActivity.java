package com.forgamers.mobile.accelerator.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class SupportActivity extends AppCompatActivity {

    TextView textView;
    SearchView mSearchView = null;
    ListView mListView = null;
    Boolean isScrollFoot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_text_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_btnback);
        Drawable d=getResources().getDrawable(R.drawable.ic_background);
        getSupportActionBar().setBackgroundDrawable(d);

        textView=(TextView)findViewById(R.id.tittle);
        textView.setText("支援加速遊戲列表");

        ArrayList<Game> gameList = new ArrayList<Game>();
        gameList.add(new Game("India","Delhi",R.drawable.ic1));
        gameList.add(new Game("China","Beijing",R.drawable.ic2));
        gameList.add(new Game("Nepal","Kathmandu",R.drawable.ic3));
        gameList.add(new Game("Bhutan","Thimphu",R.drawable.ic4));
        gameList.add(new Game("India","Delhi",R.drawable.ic1));
        gameList.add(new Game("China","Beijing",R.drawable.ic2));
        gameList.add(new Game("Nepal","Kathmandu",R.drawable.ic3));
        gameList.add(new Game("Bhutan","Thimphu",R.drawable.ic4));
        gameList.add(new Game("India","Delhi",R.drawable.ic1));
        gameList.add(new Game("China","Beijing",R.drawable.ic2));
        gameList.add(new Game("Nepal","Kathmandu",R.drawable.ic3));
        gameList.add(new Game("Bhutan","Thimphu",R.drawable.ic4));

        SupportGameList supportGameList = new SupportGameList(this, gameList );

        mSearchView = (SearchView) findViewById(R.id.searchView);
        mListView=(ListView) findViewById(R.id.listView);
        mListView.setAdapter(supportGameList);
        mListView.setTextFilterEnabled(true);

        int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);

        TextView textView = (TextView) mSearchView.findViewById(id);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setBackgroundColor(getResources().getColor(R.color.search_color));
        textView.setHintTextColor(getResources().getColor(R.color.white));

        int searchiconId = mSearchView.getContext().getResources().getIdentifier("android:id/search_button",null,null);
        ImageView imgView = (ImageView)findViewById(searchiconId);
        imgView.setBackgroundColor(getResources().getColor(R.color.search_color));
        //imgView.setBackgroundResource(R.drawable.btn_search);
        Drawable whiteIcon = imgView.getDrawable();
        whiteIcon.setTint(getResources().getColor(R.color.white)); //Whatever color you want it to be
        imgView.setImageDrawable(whiteIcon);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(!TextUtils.isEmpty(newText)) {
                    mListView.setFilterText(newText);
                }else{
                    mListView.clearTextFilter();
                }

                return false;
            }
        });

        mListView.setOnScrollListener( new AbsListView.OnScrollListener() {

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    //最底
                    isScrollFoot = true;
                } else {
                    isScrollFoot = false;
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isScrollFoot ) { // 滾動靜止且滾動到最底部
                    //停在最底部
                    Toast. makeText(SupportActivity.this, "最後一筆囉!", Toast.LENGTH_SHORT).show();
                } else {
                    // 不是停在最底部
                }
            }
        });
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}