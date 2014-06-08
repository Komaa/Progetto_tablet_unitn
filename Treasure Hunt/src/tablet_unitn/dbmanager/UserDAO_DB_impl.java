package tablet_unitn.dbmanager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import tablet_unitn.treasurehunt.Login;
import tablet_unitn.treasurehunt.User;

public class UserDAO_DB_impl implements UserDAO { 
	 
	 private SQLiteDatabase database; 
	 private UserSQLiteHelper dbHelper; 
	 
	private String[] allColumns = {
			UserSQLiteHelper.COLUMN_ID, 
			UserSQLiteHelper.COLUMN_NAME, 
			UserSQLiteHelper.COLUMN_MAIL,
			UserSQLiteHelper.COLUMN_PASSWORD,
			UserSQLiteHelper.COLUMN_POINTS,
			UserSQLiteHelper.COLUMN_LOGGED, }; 
	 
	@Override 
	public void open() throws SQLException {
		if (dbHelper==null) 
			dbHelper = new UserSQLiteHelper(Login.getAppContext()); 
		database = dbHelper.getWritableDatabase();
		Log.d("ciao", "path: "+database);
	} 
	
	@Override 
	public void close() { 
		dbHelper.close(); 
	}
	
	private ContentValues UserToValues(User user) { 
		ContentValues values = new ContentValues(); 
		values.put(UserSQLiteHelper.COLUMN_ID, user.getID());
		values.put(UserSQLiteHelper.COLUMN_NAME, user.getName()); 
		values.put(UserSQLiteHelper.COLUMN_MAIL, user.getMail()); 
		values.put(UserSQLiteHelper.COLUMN_PASSWORD, user.getPsw()); 
		values.put(UserSQLiteHelper.COLUMN_POINTS, user.getPoints());
		values.put(UserSQLiteHelper.COLUMN_LOGGED, user.getLogged());
		return values; 
	} 
		 
	private User cursorToUser(Cursor cursor) { 
		String id = cursor.getString(0); 
		String name=cursor.getString(1); 
		String mail=cursor.getString(2); 
		String password=cursor.getString(3); 
		int points=cursor.getInt(4);
		int logged=cursor.getInt(5);
		return new User(id,name,mail,password,points, logged); 
	}
	
	@Override 
	public User insertUser(User user) { 
		database.insert(UserSQLiteHelper.TABLE_USER, null, UserToValues(user));
		
		Cursor cursor = database.query(UserSQLiteHelper.TABLE_USER, allColumns, 
				UserSQLiteHelper.COLUMN_ID + " = ?" , new String[] {user.getID()}, 
				null, null, null); 
		cursor.moveToFirst();
		User p=cursorToUser(cursor);
		return p; 
	 }
	
	 @Override 
	 public void deleteUser(User user) { 
		 String id = user.getID(); 
		 		 
		 database.delete(UserSQLiteHelper.TABLE_USER, 
				 UserSQLiteHelper.COLUMN_ID + " = ?", 
				 new String[] {id}); 
	 }

	@Override
	public List<User> getAllUser() {
		List<User> people = new ArrayList<User>(); 
		Cursor cursor = database.query(UserSQLiteHelper.TABLE_USER, 
				 allColumns, null, null, null, null, null);
		cursor.moveToFirst(); 
		while (!cursor.isAfterLast()) { 
			 User User = cursorToUser(cursor); 
			 people.add(User); 
			 cursor.moveToNext(); 
		} 
		cursor.close(); // Remember to always close the cursor! 
		return people; 
	}

	@Override
	public User updateUser(User user) {
		Cursor cursor = database.query(UserSQLiteHelper.TABLE_USER, 
				allColumns,
				UserSQLiteHelper.COLUMN_ID + " = ?", 
				new String[] {user.getID()}, null, null, null);
		Log.d("ciao123", "cursor: "+cursor);
		if (cursor != null) {// record exists
			cursor.moveToFirst();
			database.update(UserSQLiteHelper.TABLE_USER,
					UserToValues(user),
					UserSQLiteHelper.COLUMN_ID + " = ?", 
					new String[] {user.getID()}); 
		} else {// record not found
			insertUser(user);
		}
		return user;
	}

	@Override
	public User getInfo(String ID) {
		Cursor cursor = database.query(UserSQLiteHelper.TABLE_USER, 
				allColumns,
				UserSQLiteHelper.COLUMN_ID + " = ?", 
				new String[] {ID}, null, null, null);
		cursor.moveToFirst();
		return cursorToUser(cursor);
	}

	@Override
	public void logout(String ID) {
		Cursor cursor = database.query(UserSQLiteHelper.TABLE_USER, 
				new String[] {UserSQLiteHelper.COLUMN_MAIL},
				UserSQLiteHelper.COLUMN_ID + " = ?", 
				new String[] {ID}, null, null, null);
		ContentValues cv = new ContentValues();
		cv.put("logged",0);
		if (cursor != null) {// record exists
			database.update(UserSQLiteHelper.TABLE_USER,
					cv, UserSQLiteHelper.COLUMN_ID + " = ?", 
					new String[] {ID}); 
		}
	}
}