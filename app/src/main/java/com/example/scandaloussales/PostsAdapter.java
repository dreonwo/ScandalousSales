package com.example.scandaloussales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;


    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        ImageView image = view.findViewById(R.id.ivImage);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_post_detail, parent, false);
                //Note: when using intents outside of activities, use context as first parameter
                Intent i = new Intent(context, PostDetail.class);
                context.startActivity(i);
            }
        });

        TextView tvUsername = view.findViewById(R.id.tvUsername);
        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.activity_user_detail, parent, false);
                //Note: when using intents outside of activities, use context as first parameter
                Intent i = new Intent(context, PostDetail.class);
                context.startActivity(i);
            }
        });
       // view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);
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

        private ImageView ivImage;
        private TextView tvProductName;
        private TextView tvPrice;
        private TextView tvUPC;
        private TextView tvUsername;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvUPC = itemView.findViewById(R.id.tvUPC);
            tvUsername = itemView.findViewById(R.id.tvUsername);
        }

        public void bind(Post post) {
            tvProductName.setText(post.getItemName());
            tvPrice.setText(post.getPrice());
            tvUPC.setText(post.getUPC());
            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if(image != null){
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);


            }
        }
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
}
