//1DDD. created composegragment and its xml

package com.example.scandaloussales.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scandaloussales.MainActivity;
//import com.example.scandaloussales.Post;
import com.example.scandaloussales.Post;
import com.example.scandaloussales.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ComposeFragment extends Fragment {

    public static final String TAG = "ComposeFragment";

    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 47;
    private TextView tvEnterDetails;
    private ImageView ivPostImage;
    private Button btnUploadImage;
    private EditText etProductName;
    private EditText etPrice;
    private EditText etUPC;
    private Button btnLogout;
    private Button btnPost;

    public EditText etResults;


    private File photoFile;
    public String photoFileName = "photo.jpg";



    public ComposeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivPostImage = view.findViewById(R.id.ivPostImage);
        btnPost = view.findViewById(R.id.btnPost);
        btnUploadImage = view.findViewById(R.id.btnUploadImage);
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick logout button");
                logoutUser();
            }
        });


    }

    public void logoutUser() {
        ParseUser.logOut();
        //2AA. if i dont use finish logout will not close app need getActivity when using in fragment
        getActivity().finish();
    }


}