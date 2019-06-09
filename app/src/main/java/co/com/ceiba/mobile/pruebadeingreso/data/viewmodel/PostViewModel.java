package co.com.ceiba.mobile.pruebadeingreso.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.dao.CeibaDataBase;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;

public class PostViewModel extends AndroidViewModel {

    private CeibaDataBase appDatabase;
    private final LiveData<List<Post>> posts;
    private LiveData<List<Post>> postsByUser;

    public PostViewModel(Application application) {
        super(application);
        appDatabase = CeibaDataBase.getDatabase(this.getApplication());
        posts = appDatabase.getPostDao().getAll();
    }

    public void findByUserId(int userId){
        postsByUser = appDatabase.getPostDao().getAllFromUser(userId);
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public LiveData<List<Post>> getPostsByUser() {
        return postsByUser;
    }


}
