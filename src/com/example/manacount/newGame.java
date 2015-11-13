package com.example.manacount;

import java.util.List;

import DAO.PlayerDAO;
import Models.Player;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class newGame extends Activity{
	
	List <Player> listPlayer;
	public EditText edt1;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);
        
        edt1 = (EditText) findViewById(R.id.editText1);
    }
	
	public void newClick(View v){
		PlayerDAO aDAO = PlayerDAO.getInstance(this);
		
		Player a = new Player();
		a.setNome(edt1.getText().toString());
		a.setMana(1);
		a.setTurn(1);
		a.setLife(30);
		
		aDAO.inserir(a);	
		
		Intent i = new Intent(this, Contador.class);
		i.putExtra("nome", edt1.getText().toString());
    	startActivity(i);
	}
}
