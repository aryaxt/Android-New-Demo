package com.aryaxt.demo.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.aryaxt.business.models.Repository;
import com.aryaxt.business.services.GithubService;
import com.aryaxt.test.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Inject
    GithubService githubService;
    @InjectView(R.id.repos_recycler_view)
    RecyclerView recyclerView;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        subscription = githubService.fetchRepositories("aryaxt")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Repository>>() {
                    @Override
                    public void call(List<Repository> repositories) {
                        recyclerView.setAdapter(new RepositoriesAdapter(repositories));
                        Log.v("", "got data");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.v("", throwable.getMessage());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        super.onDestroy();
    }
}
