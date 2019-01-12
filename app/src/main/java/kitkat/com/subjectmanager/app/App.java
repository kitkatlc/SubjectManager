package kitkat.com.subjectmanager.app;

import android.app.Application;
import kitkat.com.subjectmanager.database.AppDatabase;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.init(this);
    }
}