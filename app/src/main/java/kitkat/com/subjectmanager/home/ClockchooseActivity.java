package kitkat.com.subjectmanager.home;


import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import kitkat.com.subjectmanager.R;

public class ClockchooseActivity extends AppCompatActivity implements View.OnClickListener {

    private Switch silent;
    private Switch shake;
    private Switch alarm;
    private Switch shakeAndalarm;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockchoose_layout);
        silent=findViewById(R.id.switch1);
        shake=findViewById(R.id.switch2);
        alarm=findViewById(R.id.switch3);
        shakeAndalarm=findViewById(R.id.switch4);
        silent.setOnClickListener(this);
        shake.setOnClickListener(this);
        alarm.setOnClickListener(this);
        shakeAndalarm.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.switch1:
             Slient();
             break;
            case R.id.switch2:
                Shake();
                break;
            case R.id.switch3:
                Alarm();
            case R.id.switch4:
                ShakeAndAlarm();
                default:
                    break;
        }
    }
    public void Slient()
    {
        Toast.makeText(this, "你已选择静音模式", Toast.LENGTH_SHORT).show();
    }
    public void Shake()
    {
//        Toast.makeText(this,"你已选择震动模式",Toast.LENGTH_SHORT).show();
        Calendar c=Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month= c.get(Calendar.MONTH);
        int day= c.get(Calendar.DAY_OF_MONTH);
        int hour= c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        Toast.makeText(this,year+"年"+(month+1)+"月"+day+"日"+hour+"时"+minute+"分",Toast.LENGTH_SHORT).show();
//        Vibrator vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
//        vibrator.vibrate(new long[]{0,1000}, -1);
    }
    public void Alarm()
    {
        Toast.makeText(this,"你已选择响铃模式",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(AlarmClock.ACTION_SET_ALARM);
        startActivity(intent);
    }
    void ShakeAndAlarm()
    {

    }
}
