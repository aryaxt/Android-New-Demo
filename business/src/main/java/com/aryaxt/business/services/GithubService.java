package com.aryaxt.business.services;

import com.aryaxt.business.models.Repository;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by aryaxt on 10/31/15.
 */
public interface GithubService {

    @GET("/users/{user}/repos")
    Observable<List<Repository>> fetchRepositories(@Path("user") String user);

}
