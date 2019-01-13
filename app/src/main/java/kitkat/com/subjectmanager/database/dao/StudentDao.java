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

    @Query("select * from student where account = :account")
    Flowable<StudentEntity> getOne(String account);

    @Insert
    void add(StudentEntity  ... entities);

    @Delete
    void delete(StudentEntity entity);

    @Update
    void update(StudentEntity entity);
}