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
import com.example.scandaloussales.Post;
import com.example.scandaloussales.R;
import com.example.scandaloussales.UserDetail;
import com.parse.ParseFile;

import org.parceler.Parcels;

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
        //private TextView tvTimestamp;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);

            tvPrice = itemView.findViewById(R.id.tvPrice);

            tvUpc = itemView.findViewById(R.id.tvUPC);
            tvUpcLabel = itemView.findViewById(R.id.tvUPCLabel);
            tvUpcSign = itemView.findViewById(R.id.tvUPCSign);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUsernameLabel = itemView.findViewById(R.id.tvUsernameLabel);

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

            tvUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, PostDetail.class);
                    //i.putExtra("username", post.getUser().getUsername());
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
}
