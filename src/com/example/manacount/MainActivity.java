package com.example.manacount;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	ProgressDialog builder;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        builder = new ProgressDialog(this);
    }
    
    public void btnPlay(View v){
    	Intent i = new Intent(this, newGame.class);
    	startActivity(i);
    }
    
    public void btnSair(View v){
    	finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void scoreClick(View v){
    	Intent i = new Intent(this, GameScores.class);
    	startActivity(i);  	
    }
}
