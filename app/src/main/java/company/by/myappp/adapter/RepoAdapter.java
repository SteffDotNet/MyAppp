package company.by.myappp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import company.by.myappp.R;
import company.by.myappp.model.Repository;

/**
 * Created by Egor on 09.01.2018.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewHolder> {

    private Context context;
    private List<Repository> repos;
    private LayoutInflater inflater;

    public RepoAdapter(Context context, List<Repository> repos) {
        this.context = context;
        this.repos = repos;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.repo_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Repository repo = getRepo(position);

        holder.texName.setText(repo.getName());
        holder.textDescription.setText(repo.getDescription());
        holder.textURL.setText(repo.getUrl());
        holder.textLanguage.setText(repo.getLanguage());
        holder.textForks.setText(String.valueOf(repo.getForks()));
        holder.textStars.setText(String.valueOf(repo.getStars()));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public Repository getRepo(int id){
        return repos.get(id);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView texName;
        public TextView textDescription;
        public TextView textURL;
        public TextView textStars;
        public TextView textForks;
        public TextView textLanguage;

        public MyViewHolder(View itemView) {
            super(itemView);
            texName = (TextView) itemView.findViewById(R.id.nameRepo);
            textDescription = (TextView) itemView.findViewById(R.id.repo_description);
            textURL = (TextView) itemView.findViewById(R.id.repo_url);
            textLanguage = (TextView) itemView.findViewById(R.id.repo_language);
            textStars = (TextView) itemView.findViewById(R.id.repo_stars);
            textForks = (TextView) itemView.findViewById(R.id.repo_forks);
        }
    }


}
