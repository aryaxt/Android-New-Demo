package com.aryaxt.demo.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aryaxt.business.models.Repository;
import com.aryaxt.test.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by aryaxt on 10/31/15.
 */
public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder> {

    private final List<Repository> repositories;

    public RepositoriesAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.repository_card, parent, false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.configure(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.repo_name)
        TextView titleTextView;

        public RepositoryViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void configure(Repository repository) {
            titleTextView.setText(repository.getName());
        }
    }

}
