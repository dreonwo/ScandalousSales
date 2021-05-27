package com.example.scandaloussales.Adapters;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.scandaloussales.ItemDetail;
import com.example.scandaloussales.Post;
import com.example.scandaloussales.R;
import com.example.scandaloussales.UserDetail;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;
    public static final String tag = "PostsAdapter";

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

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvProductName;
        private TextView tvPrice;
        private TextView tvUpc;
        private TextView tvUpcLabel;
        private TextView tvUpcSign;
        private TextView tvUsername;
        private TextView tvUsernameLabel;
        private TextView tvCreatedAt;
        private ImageView ivImage;

        private CardView cvPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);

            tvPrice = itemView.findViewById(R.id.tvPrice);

            tvUpc = itemView.findViewById(R.id.tvUPC);

            tvUsername = itemView.findViewById(R.id.tvUsername);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            cvPost = itemView.findViewById(R.id.cvPost);
        }

        public void bind(Post post) {
            Log.d("PostsAdapter", post.toString());

            tvPrice.setText("Price: $" + post.getPrice());

            tvProductName.setText("Item: " + post.getItemName());

            tvUpc.setText("UPC: "  + post.getUpc());

            tvUsername.setText("Username: " + post.getUser().getUsername());

            tvCreatedAt.setText("" + DateFormat.getDateInstance().format(post.getCreatedAt()));

            Glide.with(context).load(post.getImage().getUrl()).into(ivImage);

            tvProductName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ItemDetail.class);
                    i.putExtra("post", Parcels.wrap(post));
                    context.startActivity(i);
                }
            });

            tvUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, UserDetail.class);
                    i.putExtra("user", Parcels.wrap(post.getUser()));
                    context.startActivity(i);

                }
            });

        }
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
}
