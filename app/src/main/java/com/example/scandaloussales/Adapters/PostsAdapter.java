package com.example.scandaloussales.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.ConstraintReference;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.scandaloussales.Post;
import com.example.scandaloussales.R;
import com.example.scandaloussales.UserDetail;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Post> posts;
    private static List<Post> postList;
    private static List<Post> postListFull;


    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public int getItem(int position) {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvProductName;
        private TextView tvPrice;
        private TextView tvUpc;
        private TextView tvUsername;
        private TextView tvTimestamp;
        private ImageView ivImage;
        private PostsAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvUpc = itemView.findViewById(R.id.tvUPC);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }



        public void bind(Post post) {
            Log.d("PostsAdapter", post.toString());

            tvPrice.setText("" + post.getPrice());
            tvProductName.setText(post.getItemName());
            tvUpc.setText(""  + post.getUpc());
            tvUsername.setText(post.getUser().getUsername());
            // tvTimestamp.setText("" + post.getCreatedAt());
            ParseFile image = post.getImage();
            if(image != null){
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }


            tvUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, UserDetail.class);
                    i.putExtra("username", post.getUser().getUsername());
                    //i.putExtra("user", Parcels.wrap(post.getUser()));
                    context.startActivity(i);

                }
            });
        }
    }





    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return postFilter;
    }

    private final Filter postFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Post> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(postListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Post item : postListFull) {
                    if (item.getItemName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            postList.clear();
            postList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
