package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.data.model.Address;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Company;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Geo;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;

@Database(entities = {User.class, Address.class, Geo.class, Company.class, Post.class},
    version = 1, exportSchema = false)
public abstract class CeibaDataBase extends RoomDatabase{

    public abstract UserDao getUserDao();
    public abstract AddressDao getAddressDao();
    public abstract CompanyDao getCompanyDao();
    public abstract GeoDao getGeoDao();
    public abstract PostDao getPostDao();

    private static CeibaDataBase INSTANCE;

    public static CeibaDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            CeibaDataBase.class, "ceiba_db").build();
        }
        return INSTANCE;
    }


}
