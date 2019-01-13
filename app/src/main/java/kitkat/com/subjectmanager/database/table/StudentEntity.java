package kitkat.com.subjectmanager.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class StudentEntity {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String password;

    @ColumnInfo
    public String account;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public StudentEntity(String account, String password) {
        this.account =account;
        this.password = password;
    }




}
