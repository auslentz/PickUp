package austinlentzmobileapp.pickupi399;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GamesDBHandler extends SQLiteOpenHelper {
    //sets the DB
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";
    private static final String TABLE_GAMES = "games";
    public static final String COLUMN_GAMEID = "gameid";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_SPORT = "sport";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";

    //handles the data
    public GamesDBHandler(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAMES_TABLE = "CREATE TABLE " +
                TABLE_GAMES + "(" +
                COLUMN_GAMEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_SPORT + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_LATITUDE + " TEXT, " +
                COLUMN_LONGITUDE + " TEXT);";

        //execute sql command
        db.execSQL(CREATE_GAMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, game.getTitle());
        values.put(COLUMN_TIME, game.getTime());
        values.put(COLUMN_SPORT, game.getSport());
        values.put(COLUMN_DESCRIPTION, game.getDescription());
        values.put(COLUMN_LATITUDE, game.getLatitude());
        values.put(COLUMN_LONGITUDE, game.getLongitude());

        //insert into db
        db.insert(TABLE_GAMES, null, values);
        //close db
        db.close();
    }

    public Game[] findGames() {
        String sql_query = "SELECT * FROM " + TABLE_GAMES +
                ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myCursor = db.rawQuery(sql_query, null);
        Game[] myGames = new Game[0];

        if (myCursor != null) {
            int count = myCursor.getCount();
            int length = count;

            myGames = new Game[length];

            int index = count - length;
            myCursor.moveToFirst();
            myCursor.move(index);

            for (int i = 0; i < length; i++) {
                myGames[i] = new Game();
                myGames[i].setTitle(myCursor.getString(1));
                myGames[i].setTime(myCursor.getString(2));
                myGames[i].setSport(myCursor.getString(3));
                myGames[i].setDescription(myCursor.getString(4));
                myGames[i].setLatitude(myCursor.getString(5));
                myGames[i].setLongitude(myCursor.getString(6));
                myCursor.moveToNext();
            }
            myCursor.close();
        }
        db.close();
        return myGames;
    }
}

