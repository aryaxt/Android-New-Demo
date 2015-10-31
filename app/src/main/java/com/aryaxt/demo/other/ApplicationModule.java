package com.aryaxt.demo.other;

import android.content.Context;

import com.aryaxt.business.services.GithubService;
import com.aryaxt.demo.activities.BaseActivity;
import com.aryaxt.demo.activities.MainActivity;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by aryaxt on 10/31/15.
 */

@Module(library = true, injects = {
        BaseActivity.class,
        MainActivity.class
})
public class ApplicationModule {

    private final DemoApplication application;

    public ApplicationModule(DemoApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }
}
