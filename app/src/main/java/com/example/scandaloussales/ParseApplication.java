package com.example.scandaloussales;

import android.app.Activity;

public class ParseApplication extends Activity {

    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("GgZXjy3e0jewumep3w2EcIx2EbsXarKgWijycE7o")
                .clientKey("PpKmX6XTKh551hRBYqoytMNypMBKXuFPLE0hYNwe")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
