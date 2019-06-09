package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.Geo;

@Dao
public interface GeoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Geo geo);

    @Update
    void update(Geo geo);

    @Delete
    void delete(Geo... geo);

    @Query("SELECT * FROM GEO")
    LiveData<List<Geo>> getAll();

    @Query("SELECT * FROM GEO WHERE addressId=:addressId")
    LiveData<Geo> getFromAddressId(int addressId);

    @Query("DELETE FROM GEO")
    void deleteAll();
}
