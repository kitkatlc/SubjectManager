package kitkat.com.subjectmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit=findViewById(R.id.account);
        passwordEdit=findViewById(R.id.password);
        Button login=findViewById(R.id.Login);
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login:
                Login();
                break;
                default:
                    break;
        }
    }
    public void Login(){
        if(accountEdit.getText().toString().equals("admin")&&passwordEdit.getText().toString().equals("123456")){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "密码或账户错误", Toast.LENGTH_SHORT).show();
        }
    }
}
