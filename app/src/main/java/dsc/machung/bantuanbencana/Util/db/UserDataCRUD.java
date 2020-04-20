package dsc.machung.bantuanbencana.Util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.service.autofill.UserData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dsc.machung.bantuanbencana.UserModel;

import static android.content.ContentValues.TAG;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_ADDRESS;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_EMAIL;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_ISLOGIN;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_NAME;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_PHOTO;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_SETTING;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_TELP;
import static dsc.machung.bantuanbencana.Util.db.DatabaseTable.USERDATA_TOTAL;
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
            values.put(USERDATA_ISLOGIN, userModel.getLogin());
            values.put(USERDATA_PHOTO, userModel.getPhoto());
            values.put(USERDATA_NAME, userModel.getName());
            values.put(USERDATA_EMAIL, userModel.getEmail());
            values.put(USERDATA_TELP, userModel.getTelp());
            values.put(USERDATA_ADDRESS, userModel.getAddress());
            values.put(USERDATA_SETTING, userModel.getAnonymous());
            values.put(USERDATA_TOTAL, userModel.getTotal());

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
            values.put(USERDATA_ISLOGIN, userModel.getLogin());
            values.put(USERDATA_PHOTO, userModel.getPhoto());
            values.put(USERDATA_NAME, userModel.getName());
            values.put(USERDATA_EMAIL, userModel.getEmail());
            values.put(USERDATA_TELP, userModel.getTelp());
            values.put(USERDATA_ADDRESS, userModel.getAddress());
            values.put(USERDATA_SETTING, userModel.getAnonymous());
            values.put(USERDATA_TOTAL, userModel.getTotal());

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

    public List<UserModel> getAllRecords(Context context) {
        List<UserModel> users = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s ",
                        DatabaseTable.TABLE_USERDATA );

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = PostsDatabaseHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    UserModel newUser = new UserModel();
//                    newUser.userName = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
//                    newUser.profilePictureUrl = cursor.getString(cursor.getColumnIndex(KEY_USER_PROFILE_PICTURE_URL));

                    newUser.setUsername(cursor.getString(cursor.getColumnIndex(USERDATA_USERNAME)));
                    newUser.setLogin(cursor.getInt(cursor.getColumnIndex(USERDATA_ISLOGIN)));
                    newUser.setPhoto(cursor.getString(cursor.getColumnIndex(USERDATA_PHOTO)));
                    newUser.setName(cursor.getString(cursor.getColumnIndex(USERDATA_NAME)));
                    newUser.setEmail(cursor.getString(cursor.getColumnIndex(USERDATA_EMAIL)));
                    newUser.setTelp(cursor.getString(cursor.getColumnIndex(USERDATA_TELP)));
                    newUser.setAddress(cursor.getString(cursor.getColumnIndex(USERDATA_ADDRESS)));
                    newUser.setAnonymous(cursor.getInt(cursor.getColumnIndex(USERDATA_SETTING)));
                    newUser.setTotal(cursor.getInt(cursor.getColumnIndex(USERDATA_TOTAL)));

                    users.add(newUser);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return users;
    }

    public int updateUserProfilePicture(Context context, UserModel userModel) {
        SQLiteDatabase db = PostsDatabaseHelper.getInstance(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERDATA_PHOTO, userModel.getPhoto());

        // Updating profile picture url for user with that userName
        return db.update(DatabaseTable.TABLE_USERDATA, values, USERDATA_USERNAME + " = ?",
                new String[] { String.valueOf(userModel.getUsername()) });
    }

    public void deleteAllRecords(Context context) {
        SQLiteDatabase db = PostsDatabaseHelper.getInstance(context).getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(DatabaseTable.TABLE_USERDATA, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete all posts and users");
        } finally {
            db.endTransaction();
        }
    }
}
