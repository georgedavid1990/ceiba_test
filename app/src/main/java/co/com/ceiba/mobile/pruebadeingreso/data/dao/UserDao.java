package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Update
    void update(User user);

    @Delete
    void delete(User... user);

    @Query("SELECT * FROM USER")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM USER WHERE ID=:userId")
    LiveData<User> getUserById(int userId);

    @Query("DELETE FROM USER")
    void deleteAll();
}
