package com.example.harri.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("0pbQyECiVX7mg6grUsJl0L7v8gBJI03ZEP87Utaw")
                // if defined
                .clientKey("4TUdZ2IdENc1fUBf5VtkXQncoEXn8mDphwJJlnyg")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
