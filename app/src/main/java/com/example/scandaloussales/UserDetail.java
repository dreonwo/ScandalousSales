package com.example.scandaloussales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class UserDetail extends AppCompatActivity {

    private RecyclerView rvDetail;
    public static final String TAG = "UserDetail";
    protected UserPostsAdapter adapter;
    protected List<Post> allPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        rvDetail = findViewById(R.id.rvDetail);
        allPosts = new ArrayList<>();
        adapter = new UserPostsAdapter(this, allPosts);
        rvDetail.setAdapter(adapter);
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();

    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        Log.d(TAG, getIntent().getStringExtra("username"));
        query.whereEqualTo(Post.KEY_USER, Parcels.unwrap(getIntent().getParcelableExtra("user")));
        query.addDescendingOrder(Post.KEY_CREATED_KEY);

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                for(Post post: posts){
                    Log.i(TAG, "Post: " + post.getItemName() + ", price: " + post.getPrice() + ", upc " + post.getUpc() + ", username: " + post.getUser().getUsername());                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}