package kitkat.com.subjectmanager.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class StudentEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "account")
    public String account;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "sname")
    public String sname;

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount(String account) {

        this.account = account;
    }

    public long getId() {
        return id;

    }

    public void setId(long id) {
        this.id = id;
    }

    public StudentEntity(String account, String password, String sname) {
        this.account = account;
        this.password = password;
        this.sname = sname;
    }
}