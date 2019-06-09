package co.com.ceiba.mobile.pruebadeingreso.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Post> mPosts;
    private View.OnClickListener clickListener;

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        LinearLayout linearLayout;
        TextView title;
        TextView body;

        ViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.contentCard);
            title = view.findViewById(R.id.title);
            body = view.findViewById(R.id.body);
        }
    }

    public PostsAdapter(ArrayList<Post> posts) {
        mPosts = posts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public @NonNull
    PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item, parent, false);
        return new PostsAdapter.ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostsAdapter.ViewHolder holder, int position) {

        Post post = mPosts.get(position);

        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());
    }

    public void addItems(List<Post> posts) {
        this.mPosts = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(v);
    }
}