package dsc.machung.bantuanbencana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "BantuanBencana";

    // table name
    private static final String TABLE_USERDATA = "UserData";

    // column tables
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ISLOGIN = "islogin";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_TELP = "telp";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_SETTING = "setting";
    private static final String KEY_TOTAL = "total";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERDATA + "("
                + KEY_USERNAME + " VARCHAR(40) PRIMARY KEY," + KEY_PASSWORD + " VARCHAR(40),"
                + KEY_ISLOGIN + " INT(1)," + KEY_PHOTO + " VARCHAR(256),"
                + KEY_NAME + " VARCHAR(40)," + KEY_EMAIL + " VARCHAR(256),"
                + KEY_TELP + " VARCHAR(40)," + KEY_ADDRESS + " VARCHAR(256),"
                + KEY_SETTING + " INT(1)," + KEY_TOTAL + " INT(20)" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDATA);
        onCreate(db);
    }

    public void addRecord(UserModel userModel){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, userModel.getUsername());
        values.put(KEY_PASSWORD, userModel.getPassword());
        values.put(KEY_ISLOGIN, userModel.getLogin());
        values.put(KEY_PHOTO, userModel.getPhoto());
        values.put(KEY_NAME, userModel.getName());
        values.put(KEY_EMAIL, userModel.getEmail());
        values.put(KEY_TELP, userModel.getTelp());
        values.put(KEY_ADDRESS, userModel.getAddress());
        values.put(KEY_SETTING, userModel.getAnonymous());
        values.put(KEY_TOTAL, userModel.getTotal());

        db.insert(TABLE_USERDATA, null, values);
        db.close();
    }

    public void deleteModel(UserModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERDATA, KEY_USERNAME + " = ?",
                new String[] { String.valueOf(contact.getUsername()) });
        db.close();
    }

    public UserModel getContact(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERDATA, new String[] { KEY_USERNAME, KEY_PASSWORD, KEY_ISLOGIN, KEY_PHOTO,
                        KEY_NAME, KEY_EMAIL, KEY_TELP, KEY_ADDRESS, KEY_SETTING, KEY_TOTAL }, KEY_USERNAME + "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserModel contact = new UserModel(cursor.getString(0),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getString(7), Integer.parseInt(cursor.getString(8)),
                Integer.parseInt(cursor.getString(9)));

        // return contact
        return contact;
    }

    public int updateContact(UserModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, contact.getPassword());
        values.put(KEY_ISLOGIN, contact.getLogin());
        values.put(KEY_PHOTO, contact.getPhoto());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_TELP, contact.getTelp());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_SETTING, contact.getAnonymous());
        values.put(KEY_TOTAL, contact.getTotal());

        // updating row
        return db.update(TABLE_USERDATA, values, KEY_USERNAME + " = ?",
                new String[] { String.valueOf(contact.getUsername()) });
    }

}
