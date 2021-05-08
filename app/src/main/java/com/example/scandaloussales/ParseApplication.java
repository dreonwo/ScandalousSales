package com.example.scandaloussales;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("GgZXjy3e0jewumep3w2EcIx2EbsXarKgWijycE7o")
                .clientKey("PpKmX6XTKh551hRBYqoytMNypMBKXuFPLE0hYNwe")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
