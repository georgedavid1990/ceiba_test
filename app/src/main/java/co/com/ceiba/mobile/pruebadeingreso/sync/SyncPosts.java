package co.com.ceiba.mobile.pruebadeingreso.sync;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.common.ActivityBase;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.CeibaDataBase;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.rest.RestClient;
import co.com.ceiba.mobile.pruebadeingreso.rest.result.PostResult;

public class SyncPosts implements ISyncData {

    @Override
    public void download(ActivityBase context) throws Exception {
        RestClient restClient = RestClient.getRestClient();
        List<PostResult> posts = restClient.getPosts().execute().body();

        if(posts != null){
            CeibaDataBase appDatabase = CeibaDataBase.getDatabase(context.getApplication());
            PostDao postDao = appDatabase.getPostDao();
            postDao.deleteAll();

            for (PostResult p : posts) {
                Post post = new Post();
                post.setId(p.getId());
                post.setUserId(p.getUserId());
                post.setBody(p.getBody());
                post.setTitle(p.getTitle());
                postDao.insert(post);
            }
        }
    }
}
