package com.example.android.myblogapp;

import retrofit2.Retrofit;

/**
 * Created by Divyani on 19-03-2017.
 */
public class APIManager
{
    private static APIInterface apiinterface;
    private static void createApiInterface()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
    //intializing the api interface
       apiinterface = retrofit.create(APIInterface.class);

    }
    private static APIInterface getApiInterface()
    {
        if(apiinterface ==null)
        {
            createApiInterface();
        }
        return apiinterface;
    }
}
