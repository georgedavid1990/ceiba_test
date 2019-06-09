package co.com.ceiba.mobile.pruebadeingreso.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapters.PostsAdapter;
import co.com.ceiba.mobile.pruebadeingreso.common.ActivityBase;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.data.viewmodel.PostViewModel;
import co.com.ceiba.mobile.pruebadeingreso.data.viewmodel.UserViewModel;

public class PostActivity extends ActivityBase {

    TextView name;
    TextView phone;
    TextView email;
    Resources resources;
    PostsAdapter recyclerViewAdapter;
    private UserViewModel userViewModel;
    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setControls();
        observeForData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void setControls() {
        RecyclerView recyclerViewPostsResults = findViewById(R.id.recyclerViewPostsResults);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        resources = getResources();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewPostsResults.setLayoutManager(mLayoutManager);
        recyclerViewAdapter = new PostsAdapter(new ArrayList<Post>());
        recyclerViewPostsResults.setAdapter(recyclerViewAdapter);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
    }

    private void observeForData(){
        int userId = getIntent().getIntExtra("userId", 0);
        if(userId > 0){
            userViewModel.findUserById(userId);
            userViewModel.getUser().observe(PostActivity.this, new Observer<User>() {
                @Override
                public void onChanged(@Nullable User user) {
                    if(user != null) {
                        name.setText(user.getName());
                        phone.setText(user.getPhone());
                        email.setText(user.getEmail());
                    }
                }
            });

            postViewModel.findByUserId(userId);
            postViewModel.getPostsByUser().observe(PostActivity.this, new Observer<List<Post>>() {
                @Override
                public void onChanged(@Nullable List<Post> posts) {
                    if(posts != null){
                        recyclerViewAdapter.addItems(posts);
                    }
                }
            });
        }

    }
}
