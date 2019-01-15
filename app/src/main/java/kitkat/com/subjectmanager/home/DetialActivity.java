package kitkat.com.subjectmanager.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import kitkat.com.subjectmanager.R;

public class DetialActivity extends AppCompatActivity {
    EditText editText;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        Intent intent=getIntent();
        String account=intent.getStringExtra("ACCOUNTNAME");
        Log.d("baijiededede",account);
        String coursename=intent.getStringExtra("COURSENAME");
        Log.d("baijiededede",coursename);
        SubjectDatabaseHelper dpHelper;
        dpHelper = new SubjectDatabaseHelper(DetialActivity.this, "BookStore.db", null, 1);
        SQLiteDatabase db = dpHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + account+" where course_name=? ", new String []{coursename});
        cursor.moveToFirst();
        String note=cursor.getString(cursor.getColumnIndex("note"));
        String num=cursor.getString(cursor.getColumnIndex("course_num"));
        editText=findViewById(R.id.note);
        editText.setText(note);
        EditText editText1=findViewById(R.id.subject_name);
        editText1.setText(coursename);
        EditText editText2=findViewById(R.id.subject_number);
        editText2.setText(num);
        EditText editText3=findViewById(R.id.note);
        EditText editText4=findViewById(R.id.note);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=getIntent();
        String note=editText.getText().toString();
        String account=intent.getStringExtra("ACCOUNTNAME");
        String coursename=intent.getStringExtra("COURSENAME");
        SubjectDatabaseHelper dpHelper;
        dpHelper = new SubjectDatabaseHelper(DetialActivity.this, "BookStore.db", null, 1);
        SQLiteDatabase db = dpHelper.getWritableDatabase();
        db.execSQL("update "+account+" set note=? "+"where course_name=? ",new String []{note,coursename});
    }
}
