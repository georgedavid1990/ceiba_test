package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Post post);

    @Update
    void update(Post post);

    @Delete
    void delete(Post... post);

    @Query("SELECT * FROM POST")
    LiveData<List<Post>> getAll();

    @Query("SELECT * FROM POST WHERE userId=:userId")
    LiveData<List<Post>> getAllFromUser(int userId);

    @Query("DELETE FROM POST")
    void deleteAll();
}
