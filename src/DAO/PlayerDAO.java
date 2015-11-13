package DAO;

import java.util.ArrayList;
import java.util.List;

import Models.Player;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PlayerDAO {
	public static final String NOME_TABELA = "player";
	public static final String COLUNA_NOME = "nome";	
	public static final String COLUNA_MANA = "mana";
	public static final String COLUNA_TURN = "turn";	
	public static final String COLUNA_LIFE = "life";
	public static final String COLUNA_STATUS = "status";
	
	public static final String SCRIPT_CRIACAO_TABELA = "CREATE TABLE " + NOME_TABELA + "("
            + COLUNA_NOME + " TEXT PRIMARY KEY,"
            + COLUNA_MANA + " INTEGER,"  
            + COLUNA_TURN + " INTEGER,"
            + COLUNA_LIFE + " INTEGER,"
            + COLUNA_STATUS + " TEXT) ";

	
	 public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;	 
	 
	    private SQLiteDatabase dataBase = null;	 
	 
	    private static PlayerDAO instance;
	     
	    public static PlayerDAO getInstance(Context context) {
	        if(instance == null)
	            instance = new PlayerDAO (context);
	        return instance;
	    }
	 
	    private PlayerDAO (Context context) {
	        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
	        dataBase = persistenceHelper.getWritableDatabase();
	    }
	 
	    public void inserir(Player al) {
	        ContentValues values = gerarContentValeuesPlayer(al);
	        dataBase.insert(NOME_TABELA, null, values);
	    }
	 
	    public List<Player> recuperarTodos() {
	        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
	        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
	        List<Player> conf = construirPlayerPorCursor(cursor);
	 
	        return conf;
	    }
	    
	    public List<Player> recuperarNome(String nome) {
	        String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE " +
	        	COLUNA_NOME + " = ? ";
	        
	        Cursor cursor = dataBase.rawQuery(queryReturnAll, new String[]{""+nome});
	        List<Player> conf = construirPlayerPorCursor(cursor);
	 
	        return conf;
	    }
	   	 
	    public void deletar(String nome) {	    	

	    	if(nome == "Partidas Recentes")
	    	{
	    		dataBase.delete(NOME_TABELA, null,null);
	    	}
	    	else{
    		String[] valoresParaSubstituir = {nome};

    		dataBase.delete(NOME_TABELA, COLUNA_NOME + " =  ?", valoresParaSubstituir);
	    	}
    }
	 
	    public void editar(Player al) {
	    	ContentValues values = new ContentValues();		        
		      values.put(COLUNA_MANA, al.getMana());
		      values.put(COLUNA_TURN, al.getTurn());
		      values.put(COLUNA_LIFE, al.getLife());
		      values.put(COLUNA_STATUS, al.getStatus());
	 
	        dataBase.update(NOME_TABELA, values, COLUNA_NOME + " = ?",  new String[]{"" + al.getNome()});
	
	    }
	    
	 
	    public void fecharConexao() {
	        if(dataBase != null && dataBase.isOpen())
	            dataBase.close(); 
	    }
	    
	    private List<Player> construirPlayerPorCursor(Cursor cursor) {
	        List<Player> list = new ArrayList<Player>();
	        if(cursor == null)
	            return list;
	         
	        try {
	 
	            if (cursor.moveToFirst()) {
	                do {       
	                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);
	                    int indexMana = cursor.getColumnIndex(COLUNA_MANA);
	                    int indexTurn = cursor.getColumnIndex(COLUNA_TURN);
	                    int indexLife = cursor.getColumnIndex(COLUNA_LIFE);
	                    int indexStatus = cursor.getColumnIndex(COLUNA_STATUS);
	                   
	 	                  
	                    String nome = cursor.getString(indexNome);
	                    int mana  = cursor.getInt(indexMana);
	                    int turn  = cursor.getInt(indexTurn);
	                    int life  = cursor.getInt(indexLife);
	                    String status  = cursor.getString(indexStatus);
	                    
	                    Player a = new Player(nome, mana, turn, life, status);
	                    	 
	                    list.add(a);
	 
	                } while (cursor.moveToNext());
	            }
	             
	        } finally {
	            cursor.close();
	        }
	        return list;
	    }
	   	    
	    
	 
	    private ContentValues gerarContentValeuesPlayer(Player reg) {
	        ContentValues values = new ContentValues();
	        
	        values.put(COLUNA_NOME, reg.getNome());
	        values.put(COLUNA_MANA, reg.getMana());
	        values.put(COLUNA_TURN, reg.getTurn());
	        values.put(COLUNA_LIFE, reg.getLife());
	        values.put(COLUNA_STATUS, reg.getStatus());
	        
	        
	        return values;
	    }
}
