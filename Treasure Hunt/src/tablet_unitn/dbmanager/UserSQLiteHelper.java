package tablet_unitn.dbmanager;

import android.content.Context; 
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteOpenHelper; 
import android.util.Log; 


public class UserSQLiteHelper extends SQLiteOpenHelper { 
	 public static final String TABLE_USER = "users"; 
	 public static final String COLUMN_ID = "_id"; 
	 public static final String COLUMN_NAME = "name"; 
	 public static final String COLUMN_PASSWORD = "password";
	 public static final String COLUMN_MAIL = "mail";
	 public static final String COLUMN_POINTS = "points";
	 public static final String COLUMN_LOGGED = "logged";
	 private static final String DATABASE_NAME = "treasureHunt.db"; 
	 private static final int DATABASE_VERSION = 1; 
	 
	 // Database creation sql statement 
	 private static final String DATABASE_CREATE = "create table " 
			 + TABLE_USER + "( " 
			 + COLUMN_ID + " text primary key not null, " 
			 + COLUMN_NAME + " text not null, " 
			 + COLUMN_MAIL + " text not null, "
			 + COLUMN_PASSWORD + " text not null,"
			 + COLUMN_POINTS + " integer not null,"
			 + COLUMN_LOGGED + " integer not null);"; 

	 
	 public UserSQLiteHelper(Context context) { 
		 super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	 } 
	 @Override 
	 public void onCreate(SQLiteDatabase database) { 
		 database.execSQL(DATABASE_CREATE); 
	 } 
	 
	 public void onUpdate(SQLiteDatabase database) { 
		 database.execSQL(DATABASE_CREATE); 
	 } 
	 
	 @Override 
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
		 Log.w(UserSQLiteHelper.class.getName(), 
				 "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data"); 
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER); 
		 onCreate(db); 
	 } 
}