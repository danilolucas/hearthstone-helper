package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersistenceHelper extends SQLiteOpenHelper {
	 
    public static final String NOME_BANCO =  "escola";
    public static final int VERSAO =  1;
     
    private static PersistenceHelper instance;
     
    private PersistenceHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
     
    public static PersistenceHelper getInstance(Context context) {
        if(instance == null)
            instance = new PersistenceHelper(context);
         
        return instance;
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	Log.i("Create Banco", "Criou o banco");
        db.execSQL(PlayerDAO.SCRIPT_CRIACAO_TABELA);  
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	Log.i("Update Banco", "Atualizou o Banco");
    	db.execSQL(PlayerDAO.SCRIPT_DELECAO_TABELA);    	
        onCreate(db);
    }
 
}