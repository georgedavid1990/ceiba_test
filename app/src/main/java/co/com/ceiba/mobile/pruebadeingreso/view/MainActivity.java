package co.com.ceiba.mobile.pruebadeingreso.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.common.ActivityBase;
import co.com.ceiba.mobile.pruebadeingreso.common.CustomProgressBar;
import co.com.ceiba.mobile.pruebadeingreso.common.OnClickListener;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.data.viewmodel.UserViewModel;
import co.com.ceiba.mobile.pruebadeingreso.sync.SyncPosts;
import co.com.ceiba.mobile.pruebadeingreso.sync.SyncUsers;

public class MainActivity extends ActivityBase {

    private RecyclerView recyclerViewSearchResults;
    private UsersAdapter recyclerViewAdapter;
    private EditText editTextSearch;
    private Resources resources;
    protected static CustomProgressBar progressBar;
    private UserViewModel userViewModel;
    private List<User> usersCopy;
    private List<User> filteredUsersCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControls();
        setAdapter(new ArrayList<User>());
        observeForData();
        setOnclickListener();
        setFilter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void setControls() {
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        editTextSearch = findViewById(R.id.editTextSearch);
        resources = getResources();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewSearchResults.setLayoutManager(mLayoutManager);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    private void setAdapter(ArrayList<User> users){
        recyclerViewAdapter = new UsersAdapter(users);
        recyclerViewSearchResults.setAdapter(recyclerViewAdapter);
    }

    private void observeForData(){
        userViewModel.getUsers().observe(MainActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if(users == null || users.size() == 0) {
                    new LoadDataWS(MainActivity.this).execute();
                }else {
                    usersCopy = users;
                    filteredUsersCopy = new ArrayList<>(usersCopy);
                    recyclerViewAdapter.addItems(filteredUsersCopy);
                }
            }
        });
    }

    private void setOnclickListener(){
        recyclerViewAdapter.setClickListener(new OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                openPosts(position);
            }
        });
    }

    private void setFilter(){
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredUsersCopy.clear();
                if(!TextUtils.isEmpty(s)){
                    for (User user : usersCopy) {
                        if(user.getName().toUpperCase().contains(s.toString().toUpperCase())){
                            filteredUsersCopy.add(user);
                        }
                    }
                }else{
                    filteredUsersCopy.addAll(usersCopy);
                }
                recyclerViewAdapter.addItems(filteredUsersCopy);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void openPosts(int position){
        User user = filteredUsersCopy.get(position);
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("userId", user.getId());
        startActivity(intent);
    }

    private class LoadDataWS extends AsyncTask<String, String, String> {

        String msgError = "";
        ActivityBase contexto;

        protected void onPreExecute() {
            progressBar = new CustomProgressBar();
            progressBar.show(contexto, resources.getString(R.string.custom_bar_message));
        }

        private LoadDataWS(ActivityBase contexto) {
            this.contexto = contexto;
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                new SyncUsers().download(contexto);
                new SyncPosts().download(contexto);
            } catch (Exception e) {
                //todo logCaughtException(e);
                msgError = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.getDialog().dismiss();
            if (!msgError.equals("")) {
                makeErrorDialog(msgError, MainActivity.this);
            }
        }
    }
}