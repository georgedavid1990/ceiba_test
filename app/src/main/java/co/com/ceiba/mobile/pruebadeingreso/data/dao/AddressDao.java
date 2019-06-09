package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.Address;

@Dao
public interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Address address);

    @Update
    void update(Address address);

    @Delete
    void delete(Address... addresses);

    @Query("SELECT * FROM Address")
    LiveData<List<Address>> getAll();

    @Query("SELECT * FROM Address WHERE userId=:userId")
    LiveData<Address> getFromUserId(int userId);

    @Query("DELETE FROM Address")
    void deleteAll();
}
