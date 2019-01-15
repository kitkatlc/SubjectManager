package kitkat.com.subjectmanager.database.subjectdatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubjectOperation {
    public Date StringToDate(int year,int month,int day){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String str = year+"-"+month+"-"+day;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(str);
        return  date;
    }
}
