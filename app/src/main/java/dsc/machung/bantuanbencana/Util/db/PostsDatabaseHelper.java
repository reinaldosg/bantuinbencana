package dsc.machung.bantuanbencana.Util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostsDatabaseHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "BantuanBencana";
    private static final int DATABASE_VERSION = 2;


    private static PostsDatabaseHelper sInstance;

    public static synchronized PostsDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new PostsDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private PostsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERDATA_TABLE = "CREATE TABLE " + DatabaseTable.TABLE_USERDATA + "("
                + DatabaseTable.USERDATA_USERNAME + " VARCHAR(40) PRIMARY KEY,"
                + DatabaseTable.USERDATA_ISLOGIN + " INT(1),"
                + DatabaseTable.USERDATA_PHOTO + " VARCHAR(256),"
                + DatabaseTable.USERDATA_NAME + " VARCHAR(40),"
                + DatabaseTable.USERDATA_EMAIL + " VARCHAR(256),"
                + DatabaseTable.USERDATA_TELP + " VARCHAR(40),"
                + DatabaseTable.USERDATA_ADDRESS + " VARCHAR(256),"
                + DatabaseTable.USERDATA_SETTING + " INT(1),"
                + DatabaseTable.USERDATA_TOTAL + " INT(20)" + ")";

        db.execSQL(CREATE_USERDATA_TABLE);

    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + DatabaseTable.TABLE_USERDATA);
            onCreate(db);
        }
    }
}
