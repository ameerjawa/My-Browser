package com.example.mybrowser;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urlEdit;
    Button forward,reload,back;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        brow=(WebView)findViewById(R.id.wv_brow);
        urlEdit=(EditText)findViewById(R.id.edittext_url) ;
        forward = (Button)findViewById(R.id.forward) ;
        reload = (Button)findViewById(R.id.reload) ;

        back = (Button)findViewById(R.id.back) ;
        brow.setWebViewClient(new OurVIEWClient());
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                }

            }
        });
        WebSettings websettings = brow.getSettings();
        websettings.setJavaScriptEnabled(true);

        brow.loadUrl("https://www.google.com");


        urlEdit.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    String edittextvalue=urlEdit.getText().toString();


                    if (!edittextvalue.startsWith("http://"))
                        edittextvalue="http://"+edittextvalue;
                    String url=edittextvalue;
                    brow.loadUrl(url);


                    InputMethodManager imm = ( InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(urlEdit.getWindowToken(),0);
                }
                return true;
            }
        });


        forward.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (brow.canGoForward()) {
                    brow.goForward();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (brow.canGoBack()) {
                    brow.goBack();
                }
            }
        });
        reload.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                brow.reload();
            }
        });

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
            Intent intent =new Intent(MainActivity.this,login.class);
            startActivity(intent);
        }
        if (id ==R.id.home_btn){
            Intent intent =new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);

        }
        if (id ==R.id.about_btn){

            Intent intent =new Intent(MainActivity.this,about.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }





}