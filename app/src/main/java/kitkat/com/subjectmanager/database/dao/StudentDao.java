package kitkat.com.subjectmanager.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import kitkat.com.subjectmanager.database.table.StudentEntity;

@Dao
public interface StudentDao {

    @Query("select * from student")
    List<StudentEntity> getAll();

    @Query("select sname from student where sname = :sname ")
    Flowable<StudentEntity> getsname(String sname);

    @Query("select password from student where password=:password ")
    Flowable<StudentEntity> getpassword(String password);

    @Query("select account from student where account=:account ")
    Flowable<StudentEntity> getaccount(String account);

    @Query("select * from student where account=:account ")
    Flowable<StudentEntity> getStudentByAccount(String account);

    @Insert
    void add(StudentEntity... entities);

    @Delete
    void delete(StudentEntity entity);

    @Update
    void update(StudentEntity entity);
}