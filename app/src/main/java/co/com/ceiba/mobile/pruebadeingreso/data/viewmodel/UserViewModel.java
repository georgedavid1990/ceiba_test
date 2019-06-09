package co.com.ceiba.mobile.pruebadeingreso.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.dao.CeibaDataBase;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;

public class UserViewModel extends AndroidViewModel {

    private CeibaDataBase appDatabase;
    private final LiveData<List<User>> users;
    private LiveData<User> user;

    public UserViewModel(Application application) {
        super(application);
        appDatabase = CeibaDataBase.getDatabase(this.getApplication());
        users = appDatabase.getUserDao().getAll();
    }

    public void findUserById(int userId){
        user = appDatabase.getUserDao().getUserById(userId);
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<User> getUser() {
        return user;
    }
}
