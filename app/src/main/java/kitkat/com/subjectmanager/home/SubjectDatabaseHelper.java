package kitkat.com.subjectmanager.home;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SubjectDatabaseHelper extends SQLiteOpenHelper {
    public static final String CTEAT_BOOK="create table user(account text primary key,password text,question text)";
    private Context mcontext;
    public SubjectDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CTEAT_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

