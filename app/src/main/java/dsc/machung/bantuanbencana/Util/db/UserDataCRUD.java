package dsc.machung.bantuanbencana.Util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import dsc.machung.bantuanbencana.UserModel;

import static android.content.ContentValues.TAG;

public class UserDataCRUD {
    //UserData
    public void addRecord(Context context, UserModel userModel) {
        // Create and/or open the database for writing
        SQLiteDatabase db = PostsDatabaseHelper.getInstance(context).getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(DatabaseTable.USERDATA_USERNAME, userModel.getUsername());
            values.put(DatabaseTable.USERDATA_ISLOGIN, userModel.getLogin());
            values.put(DatabaseTable.USERDATA_PHOTO, userModel.getPhoto());
            values.put(DatabaseTable.USERDATA_NAME, userModel.getName());
            values.put(DatabaseTable.USERDATA_EMAIL, userModel.getEmail());
            values.put(DatabaseTable.USERDATA_TELP, userModel.getTelp());
            values.put(DatabaseTable.USERDATA_ADDRESS, userModel.getAddress());
            values.put(DatabaseTable.USERDATA_SETTING, userModel.getAnonymous());
            values.put(DatabaseTable.USERDATA_TOTAL, userModel.getTotal());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insert(DatabaseTable.TABLE_USERDATA, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }
}
