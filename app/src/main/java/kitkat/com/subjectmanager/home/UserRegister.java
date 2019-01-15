package kitkat.com.subjectmanager.home;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kitkat.com.subjectmanager.database.AppDatabase;
import kitkat.com.subjectmanager.database.dao.StudentDao;
import kitkat.com.subjectmanager.database.table.StudentEntity;

public class UserRegister extends Activity {

    private EditText register_username;
    private EditText register_passwd;
    private EditText reregister_passwd;
    private Button register_submit;
    SubjectDatabaseHelper dpHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(kitkat.com.subjectmanager.R.layout.register_layout);
        register_username=(EditText)findViewById(kitkat.com.subjectmanager.R.id.registe_account);
        register_passwd=(EditText)findViewById(kitkat.com.subjectmanager.R.id.registe_password);
        reregister_passwd=(EditText)findViewById(kitkat.com.subjectmanager.R.id.reregiste_password);
        register_submit=(Button)findViewById(kitkat.com.subjectmanager.R.id.registe_commit);
        register_username.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(register_username.getText().toString().trim().length()<4){
                        Toast.makeText(UserRegister.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        register_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(register_passwd.getText().toString().trim().length()<6){
                        Toast.makeText(UserRegister.this, "密码不能小于8个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        reregister_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(!reregister_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
                        Toast.makeText(UserRegister.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        register_submit.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                if (!checkEdit()) {
                    return;
                }
                AppDatabase database = AppDatabase.getInstance();
                StudentEntity entity = new StudentEntity(register_username.getText().toString().trim(),register_passwd.getText().toString().trim());
                StudentDao dao = database.getStudentDao();
                //增加
                dao.add(entity);
               Toast.makeText(UserRegister.this,"恭喜你，注册成功",Toast.LENGTH_SHORT).show();
                dpHelper=new SubjectDatabaseHelper(UserRegister.this,"BookStore.db",null,1);
                SQLiteDatabase db=dpHelper.getWritableDatabase();
                String sql="create table "+register_username.getText().toString()+"(course_name text ,course_num text,note text)";
                db.execSQL(sql);
               Intent intent=new Intent(UserRegister.this,LoginActivity.class);
               startActivity(intent);
//                Boolean isRegister = dao.add(entity);
//                if (isRegister) {
//                    Toast.makeText(UserRegister.this, "注册成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(UserRegister.this, "注册失败", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private boolean checkEdit(){
        if(register_username.getText().toString().trim().equals("")){
            Toast.makeText(UserRegister.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(register_passwd.getText().toString().trim().equals("")){
            Toast.makeText(UserRegister.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){
            Toast.makeText(UserRegister.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }
}