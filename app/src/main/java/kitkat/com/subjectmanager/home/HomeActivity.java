package kitkat.com.subjectmanager.home;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import kitkat.com.subjectmanager.R;
import kitkat.com.subjectmanager.database.AppDatabase;
import kitkat.com.subjectmanager.database.dao.SubjectDao;
import kitkat.com.subjectmanager.database.table.SubjectEntity;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppDatabase database = AppDatabase.getInstance();

        SubjectEntity entity = new SubjectEntity();
        SubjectDao dao = database.getSubjectDao();
        //增加
        dao.add(entity);
        //删除
        dao.delete(entity);
        //查询
        List<SubjectEntity> list = dao.getAll();
    }
}