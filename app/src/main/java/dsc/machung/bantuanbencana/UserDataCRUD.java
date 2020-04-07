package dsc.machung.bantuanbencana;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class UserDataCRUD {

    private static final String TABLE_USERDATA = "UserData";

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

    public void addRecord(UserModel userModel, Context context) {
        // Create and/or open the database for writing
        SQLiteDatabase db = PostsDatabaseHelper.getInstance(context).getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
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

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insert(TABLE_USERDATA, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }
}
