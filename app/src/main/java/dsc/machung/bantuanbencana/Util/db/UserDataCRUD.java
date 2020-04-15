package dsc.machung.bantuanbencana.Util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import dsc.machung.bantuanbencana.UserModel;

import static android.content.ContentValues.TAG;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_USERNAME;

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
            values.put(USERDATA_USERNAME, userModel.getUsername());
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


    public long addOrUpdateRecord(Context context, UserModel userModel) {
        // The database connection is cached so it's not expensive to call getWriteableDatabase() multiple times.
        SQLiteDatabase db = PostsDatabaseHelper.getInstance(context).getWritableDatabase();
        long userId = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(USERDATA_USERNAME, userModel.getUsername());
            values.put(DatabaseTable.USERDATA_ISLOGIN, userModel.getLogin());
            values.put(DatabaseTable.USERDATA_PHOTO, userModel.getPhoto());
            values.put(DatabaseTable.USERDATA_NAME, userModel.getName());
            values.put(DatabaseTable.USERDATA_EMAIL, userModel.getEmail());
            values.put(DatabaseTable.USERDATA_TELP, userModel.getTelp());
            values.put(DatabaseTable.USERDATA_ADDRESS, userModel.getAddress());
            values.put(DatabaseTable.USERDATA_SETTING, userModel.getAnonymous());
            values.put(DatabaseTable.USERDATA_TOTAL, userModel.getTotal());

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(DatabaseTable.TABLE_USERDATA, values, USERDATA_USERNAME + "= ?", new String[]{userModel.getUsername()});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        USERDATA_USERNAME, DatabaseTable.TABLE_USERDATA, USERDATA_USERNAME);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(userModel.getUsername())});
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                userId = db.insertOrThrow(DatabaseTable.TABLE_USERDATA, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update user");
        } finally {
            db.endTransaction();
        }
        return userId;
    }
}
