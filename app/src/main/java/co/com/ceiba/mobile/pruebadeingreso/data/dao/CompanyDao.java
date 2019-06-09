package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.Company;

@Dao
public interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Company company);

    @Update
    void update(Company company);

    @Delete
    void delete(Company... companies);

    @Query("SELECT * FROM Company")
    LiveData<List<Company>> getAll();

    @Query("SELECT * FROM Company WHERE userId=:userId")
    LiveData<Company> getFromUserId(int userId);

    @Query("DELETE FROM COMPANY")
    void deleteAll();
}
