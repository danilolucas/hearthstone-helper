package com.example.manacount;

import java.util.ArrayList;
import java.util.List;

import DAO.PlayerDAO;
import Models.Player;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class GameScores extends ListActivity{
	
	AlertDialog.Builder dialog;
	boolean longClick = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		dialog = new AlertDialog.Builder(this);
		
		//função - pressionando o item
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				
				longClick = true;
				
				//mensagem de confirmação
				AlertDialog.Builder al = new AlertDialog.Builder(GameScores.this);
				al.setTitle("ATENÇÃO");
				al.setMessage("Deseja excluir este item ?");
				al.setPositiveButton("Sim", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						PlayerDAO aDAO = PlayerDAO.getInstance(GameScores.this);
						
						Object obj = getListView().getAdapter().getItem(position);
				        String value = obj.toString();

						aDAO.deletar(value);
						Listar();
					}
				});
				al.setNegativeButton("Não", null);
				al.show();
				
				longClick = false;
				return true;
			}
		});
		
		//função - click item
		getListView().setOnItemClickListener( new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    	if(!longClick){
			    	PlayerDAO alDAO = PlayerDAO.getInstance(getApplicationContext());
					
					List<Player> listPlayer = alDAO.recuperarTodos();				
					
					Object obj = getListView().getAdapter().getItem(arg2);
			        String value = obj.toString();
			        
					for(Player al : listPlayer){
						if(al.getNome().equals(value)){				
							dialog.setTitle("Informações");
							dialog.setMessage("Nome: " + al.getNome() + "\nManas: " + al.getMana() + "\nTurnos: " + al.getTurn() + "\nVida: " + al.getLife() + "\nStatus: " + al.getStatus());
							dialog.show();
						}
					}
		    	}
	    	}
		});
			
		Listar();
	}
	
	public void Listar(){
		PlayerDAO alDAO = PlayerDAO.getInstance(this);

		ArrayList<String> lista = new ArrayList<String>();
		
		List<Player> lisPlayer = alDAO.recuperarTodos();
		
		lista.add("Partidas Recentes");
		
		for(Player al : lisPlayer)
		{
			Log.i("1",al.getNome());
			lista.add(al.getNome());
		}
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1,lista);
		
		setListAdapter(ad);
	}

//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
//		
//		PlayerDAO alDAO = PlayerDAO.getInstance(this);
//		
//		List<Player> listPlayer = alDAO.recuperarTodos();
//		
//		dialog = new AlertDialog.Builder(this);
//		
//		Object obj = this.getListAdapter().getItem(position);
//        String value = obj.toString();
//		
//		for(Player al : listPlayer){
//			if(al.getNome().equals(value)){				
//				dialog.setTitle("Informações");
//				dialog.setMessage("Nome: " + al.getNome() + "\nManas: " + al.getMana() + "\nTurnos: " + al.getTurn() + "\nVida: " + al.getLife());
//				dialog.show();
//			}
//		}
//		
//
//	}
	
	
}
