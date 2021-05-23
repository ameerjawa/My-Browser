package com.example.mybrowser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id ==R.id.login_btn){
            Intent intent =new Intent(about.this,login.class);
            startActivity(intent);
        }
        if (id ==R.id.home_btn){
            Intent intent =new Intent(about.this,MainActivity.class);
            startActivity(intent);

        }
        if (id ==R.id.about_btn){

            Intent intent =new Intent(about.this,about.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void sharebtn(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Android Development Tutorials");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "www.ziki&ameer.com");
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }
}