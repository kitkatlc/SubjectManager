package kitkat.com.subjectmanager.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kitkat.com.subjectmanager.R;
import kitkat.com.subjectmanager.database.AppDatabase;
import kitkat.com.subjectmanager.database.dao.StudentDao;
import kitkat.com.subjectmanager.database.table.StudentEntity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText accountEdit;
    private EditText passwordEdit;
    private StudentDao studentDao = AppDatabase.getInstance().getStudentDao();
    private SharedPreferences pref;
    private CheckBox rememberPassword;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);
        rememberPassword = findViewById(R.id.rememberpass);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isRemember = pref.getBoolean("remember_password",false);
        login.setOnClickListener(this);
       register.setOnClickListener(this);
        if(isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                Login();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this,UserRegister.class));
                break;
            default:
                break;
        }
    }

    public void Login(){
        String account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        pref=PreferenceManager.getDefaultSharedPreferences(this);
        editor=pref.edit();
        if(rememberPassword.isChecked()){
            editor.putBoolean("remember_password",true);
            editor.putString("account",account);
            editor.putString("password",password);
        }else {
            editor.clear();
        }
        editor.apply();
        if(!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            List<StudentEntity> allStudent = studentDao.getAll();
//            if (allStudent == null) {
//                Toast.makeText(this, "dd", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(this, "fdgvd", Toast.LENGTH_SHORT).show();
//            }
            boolean match = false;
            for (int i = 0; i < allStudent.size(); i++) {
                StudentEntity s = allStudent.get(i);
                if (account.equals(s.getAccount()) && password.equals(s.getPassword())) {
                    match = true;
                    break;
                } else {
                    match = false;
                }
            }
            if (match) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SubjectActivity.class);
                intent.putExtra("ACCOUNTNAME",account);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"请输入账户和密码",Toast.LENGTH_SHORT).show();
        }
       }
    }

