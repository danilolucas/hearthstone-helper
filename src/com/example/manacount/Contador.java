package com.example.manacount;

import java.util.concurrent.CountDownLatch;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Contador extends Activity {
	
	public int cont=1, turn=1, timer, life=30;
	public String nome;
	public ImageView[] img;
	public TextView txt1;
	public ProgressBar bar;
	public Button btnEndTurn, btnEndGame;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contador_layout);
        
        img = new ImageView[9];
        img[1] = (ImageView) findViewById(R.id.imageView1);     
        img[2] = (ImageView) findViewById(R.id.imageView2); 
        img[3] = (ImageView) findViewById(R.id.imageView3); 
        img[4] = (ImageView) findViewById(R.id.imageView4); 
        img[5] = (ImageView) findViewById(R.id.imageView5); 
        img[6] = (ImageView) findViewById(R.id.imageView6); 
        img[7] = (ImageView) findViewById(R.id.imageView7); 
        img[8] = (ImageView) findViewById(R.id.imageView8);
        
        img[1].setVisibility(View.INVISIBLE);
        img[2].setVisibility(View.INVISIBLE);
        img[3].setVisibility(View.INVISIBLE);
        img[4].setVisibility(View.INVISIBLE);
        img[5].setVisibility(View.INVISIBLE);
        img[6].setVisibility(View.INVISIBLE);
        img[7].setVisibility(View.INVISIBLE);
        img[8].setVisibility(View.INVISIBLE);
        
        txt1 = (TextView) findViewById(R.id.textView1);
        
        btnEndTurn = (Button) findViewById(R.id.button1);
        btnEndGame = (Button) findViewById(R.id.button4);
        
        bar = (ProgressBar) findViewById(R.id.progressBar1);
        
        Intent intent = getIntent();
    	nome = intent.getStringExtra("nome");
        
        new CountDownTimer(2400000, 1200) {

            public void onTick(long millisUntilFinished) {
            	if(timer>=100)
                	btnEndTurn.performClick();
                
            	bar.setProgress(timer);
                timer++;
            }

            public void onFinish() {
            	//btnInfo.performClick();
            }
         }.start();        
    }
    
    public void endTurn(View v){
    	if(cont<=8)
    	img[cont].setVisibility(View.VISIBLE);
    	if(cont<8)
    		img[cont+1].setVisibility(View.INVISIBLE);
	    if(cont<=8) //mudar para 10 quando tiver as 10 manas
	    	cont++;
	    turn++;
	    
	    timer=0;
    }
    
    public void btn1(View v){
    	if(cont<=8){
    		img[cont].setVisibility(View.VISIBLE);
    	} else
    		Toast.makeText(this, "Você já tem manas demais.", Toast.LENGTH_SHORT).show();
    }
    
    public void btn2(View v){
    	if(cont<=7){
	    	img[cont].setVisibility(View.VISIBLE);
	    	img[cont+1].setVisibility(View.VISIBLE);
    	} else if(cont<=8){
    		img[cont].setVisibility(View.VISIBLE);
    		Toast.makeText(this, "Você perdeu uma mana.", Toast.LENGTH_SHORT).show();
    	} else
    		Toast.makeText(this, "Você já tem manas demais.", Toast.LENGTH_SHORT).show();
    		
    }
    
    public void moreLife(View v){
    	life++;
    	txt1.setText(String.valueOf(life));
    }
    
    public void lessLife(View v){
    	life--;
    	txt1.setText(String.valueOf(life));
    }
    
    public void endClick(View v){
    	Intent i = new Intent(this,Score.class);
    	i.putExtra("nome", nome);
    	i.putExtra("manas", cont);
    	i.putExtra("turn", turn);
    	i.putExtra("life", life);
    	startActivity(i);
    }
}
    