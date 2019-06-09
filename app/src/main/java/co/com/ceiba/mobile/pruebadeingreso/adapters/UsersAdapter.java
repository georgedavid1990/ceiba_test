package co.com.ceiba.mobile.pruebadeingreso.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.common.OnClickListener;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{

    private static final int VIEW_TYPE_USERS = 1;
    private static final int VIEW_TYPE_EMPTY = 2;

    private List<User> mUsers;
    private OnClickListener clickListener;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public OnClickListener getClickListener() {
        return clickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView name;
        TextView phone;
        TextView email;
        Button btn_view_post;

        ViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.contentCard);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            email = view.findViewById(R.id.email);
            btn_view_post = view.findViewById(R.id.btn_view_post);
        }
    }

    static class ViewHolderEmpty extends ViewHolder {

        ViewHolderEmpty(View v) {
            super(v);
        }
    }

    public UsersAdapter(ArrayList<User> users) {
        mUsers = users;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public @NonNull
    UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case VIEW_TYPE_USERS:
                CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_list_item, parent, false);
                return new UsersAdapter.ViewHolder(cv);
            case VIEW_TYPE_EMPTY:
                RelativeLayout rl = (RelativeLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.empty_view, parent, false);
                return new UsersAdapter.ViewHolderEmpty(rl);
            default:
                CardView cvd = (CardView) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_list_item, parent, false);
                return new UsersAdapter.ViewHolder(cvd);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final UsersAdapter.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_USERS) {
            User user = mUsers.get(position);

            holder.name.setText(user.getName());
            holder.phone.setText(user.getPhone());
            holder.email.setText(user.getEmail());
            holder.btn_view_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v, holder.getAdapterPosition());
                }
            });
        }
    }

    public void addItems(List<User> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mUsers.size() == 0) {
            return VIEW_TYPE_EMPTY;
        }else{
            return VIEW_TYPE_USERS;
        }
    }

    @Override
    public int getItemCount() {
        if(mUsers.size() == 0){
            return 1;
        }else {
            return mUsers.size();
        }
    }

}