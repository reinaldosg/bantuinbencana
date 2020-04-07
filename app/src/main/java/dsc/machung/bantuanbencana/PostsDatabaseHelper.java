package dsc.machung.bantuanbencana;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostsDatabaseHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "BantuanBencana";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_USERDATA = "UserData";

    // Table Columns
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
        String CREATE_USERDATA_TABLE = "CREATE TABLE " + TABLE_USERDATA + "("
                + KEY_USERNAME + " VARCHAR(40) PRIMARY KEY,"
                + KEY_PASSWORD + " VARCHAR(40),"
                + KEY_ISLOGIN + " INT(1),"
                + KEY_PHOTO + " VARCHAR(256),"
                + KEY_NAME + " VARCHAR(40),"
                + KEY_EMAIL + " VARCHAR(256),"
                + KEY_TELP + " VARCHAR(40),"
                + KEY_ADDRESS + " VARCHAR(256),"
                + KEY_SETTING + " INT(1),"
                + KEY_TOTAL + " INT(20)" + ")";

        db.execSQL(CREATE_USERDATA_TABLE);

    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDATA);
            onCreate(db);
        }
    }
}
