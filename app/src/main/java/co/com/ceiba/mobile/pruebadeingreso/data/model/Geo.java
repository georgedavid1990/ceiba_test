package co.com.ceiba.mobile.pruebadeingreso.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = Address.class,
        parentColumns = "id",
        childColumns = "addressId",
        onDelete = CASCADE),
        indices = {@Index("addressId")})

public class Geo {

    @PrimaryKey
    @NonNull
    private String id;
    private String addressId;
    private String lat;
    private String lng;

    public Geo(@NonNull String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
