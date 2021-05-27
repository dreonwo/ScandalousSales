package com.example.scandaloussales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scandaloussales.Adapters.UserPostsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserDetail extends AppCompatActivity {

    private RecyclerView rvDetail;
    public static final String TAG = "UserDetail";
    protected UserPostsAdapter adapter;
    protected List<Post> allPosts;
    private TextView tvName;
    private ParseUser user;
    private Button backBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        getSupportActionBar().hide();

        tvName = findViewById(R.id.tvName);
        tvName.setText(user.getUsername().substring(0, 1).toUpperCase() + user.getUsername().substring(1) + "'s posts");

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        query.whereEqualTo(Post.KEY_USER, user);
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