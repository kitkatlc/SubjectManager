package kitkat.com.subjectmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kitkat.com.subjectmanager.database.AppDatabase;
import kitkat.com.subjectmanager.database.dao.StudentDao;
import kitkat.com.subjectmanager.database.table.StudentEntity;
import kitkat.com.subjectmanager.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText accountEdit;
    private EditText passwordEdit;
    private StudentDao studentDao = AppDatabase.getInstance().getStudentDao();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        Button login = findViewById(R.id.Login);
        Button register = findViewById(R.id.Regite);
        Button fingPassword = findViewById(R.id.Find);
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login:
                Login();
                break;
            case R.id.Regite:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    break;
            case R.id.Find:
                // 暂时跳转到MainActivity
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                break;
                default:
                    break;
        }
    }
    public void Login(){
        String account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if(!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            List<StudentEntity> allStudent=studentDao.getAll();
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
                Intent intent = new Intent(this, HomeActivity.class);
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
