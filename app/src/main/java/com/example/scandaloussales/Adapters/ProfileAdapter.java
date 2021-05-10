package com.example.scandaloussales.Adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.scandaloussales.ItemDetail;
import com.example.scandaloussales.Post;
import com.example.scandaloussales.R;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public ProfileAdapter(Context context, List<Post> posts) {
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
        private TextView tvUsername;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvUpc = itemView.findViewById(R.id.tvUPC);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            //tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
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

            tvProductName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ItemDetail.class);
                    i.putExtra("post", Parcels.wrap(post));
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
