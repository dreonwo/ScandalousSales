package com.example.scandaloussales.fragments;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scandaloussales.Adapters.ProfileAdapter;
import com.example.scandaloussales.Adapters.UserPostsAdapter;
import com.example.scandaloussales.LoginActivity;
import com.example.scandaloussales.Post;
import com.example.scandaloussales.R;
import com.example.scandaloussales.TimeFormatter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends PostsFragment {

    Button btnLogout;
    public static final String TAG = "ProfileFragment";
    private RecyclerView rvProfile;
    protected ProfileAdapter adapter;
    protected List<Post> allPosts;
    TextView tvFirstName;
    TextView tvLastName;
    TextView tvUsername;
    TextView tvJoinedAt;
    TextView tvPosts;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       //super.onViewCreated(view, savedInstanceState);

        tvFirstName = view.findViewById(R.id.tvFirstName);
        tvLastName = view.findViewById(R.id.tvLastName);
        tvUsername = view.findViewById(R.id.tvUsername);
        rvProfile = view.findViewById(R.id.rvProfile);
        tvJoinedAt = view.findViewById(R.id.tvJoinedAt);
        tvPosts = view.findViewById(R.id.tvPosts);
        allPosts = new ArrayList<>();
        adapter = new ProfileAdapter(getContext(), allPosts);
        rvProfile.setAdapter(adapter);
        rvProfile.setLayoutManager(new LinearLayoutManager(getContext()));

        tvFirstName.setText(""+ParseUser.getCurrentUser().get("firstName"));
        tvLastName.setText(""+ParseUser.getCurrentUser().get("lastName"));
        tvUsername.setText("@"+ParseUser.getCurrentUser().getUsername());
        tvJoinedAt.setText("Joined: " + DateFormat.getDateInstance().format(ParseUser.getCurrentUser().getCreatedAt()));

        queryPosts();

        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//          getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

    }

    public void logoutUser() {
        ParseUser.logOut();
        //2AA. if i dont use finish logout will not close app need getActivity when using in fragment
        getActivity().finish();
    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        try {
            tvPosts.setText("Posts made: " + query.count());
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
