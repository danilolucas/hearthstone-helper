package com.example.manacount;

import java.util.List;

import DAO.PlayerDAO;
import Models.Player;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.Intent;

public class Score extends ListActivity {
	
	List <Player> listPlayer;
	int manas, turn, life;
	String nome;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
    	manas = intent.getIntExtra("manas", 1);
    	turn = intent.getIntExtra("turn", 1);
    	life = intent.getIntExtra("life", 1);
    	
    	String[] varList = {"Turnos = " + turn, "Manas = " + manas, "Life = " + life, "Finalizar Partida"};
        
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, varList);
        
        this.setListAdapter(array);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		switch(position){
			case 0:
				Toast.makeText(this, "Você jogou " + turn + " Turnos.", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(this, "Você tem " + manas + " Manas.", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(this, "Você tem " + life + " de Vida.", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				PlayerDAO aDAO = PlayerDAO.getInstance(this);
				Intent intent = getIntent();
				String nome = intent.getStringExtra("nome");
				listPlayer = aDAO.recuperarNome(nome);
				
				listPlayer.get(0).setMana(manas);
				listPlayer.get(0).setTurn(turn);
				listPlayer.get(0).setLife(life);
				if(life > 0)
					listPlayer.get(0).setStatus("Ganhou");
				else
					listPlayer.get(0).setStatus("Perdeu");
				
				aDAO.editar(listPlayer.get(0));
				
				Intent i = new Intent(this, MainActivity.class);
				startActivity(i);
				break;
			default:
				break;
		}
	}
}
