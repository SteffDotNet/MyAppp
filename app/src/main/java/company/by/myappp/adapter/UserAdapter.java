package company.by.myappp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import company.by.myappp.R;
import company.by.myappp.db.DBService;
import company.by.myappp.model.User;

/**
 * Created by Egor on 08.01.2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> users;
    Context context;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d("TAG", "size = " + users.size());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView textViewLogin;
        public TextView textViewType;
        public TextView textViewURL;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.avatar = (ImageView) itemView.findViewById(R.id.avatar);
            this.textViewLogin = (TextView) itemView.findViewById(R.id.login);
            this.textViewType = (TextView) itemView.findViewById(R.id.type);
            this.textViewURL = (TextView) itemView.findViewById(R.id.url);

        }
    }

    private User getUser(int i){
        return users.get(i);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_user, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.SHOW.REPO");
                intent.putExtra("id_user",getUser(myViewHolder.getAdapterPosition()).getId());
                intent.putExtra("name", getUser(myViewHolder.getAdapterPosition()).getLogin());
                context.startActivity(intent);
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = getUser(position);

        if(isConnected()){
            Picasso.with(context).load(user.getAvatar_url()).placeholder(R.mipmap.ic_launcher_round).into(holder.avatar);
        } else {
            DBService manger = new DBService(context);
            holder.avatar.setImageBitmap(manger.getPicture(user.getAvatar_url()));
        }

        holder.textViewLogin.setText(user.getLogin());
        holder.textViewType.setText(user.getType());
        holder.textViewURL.setText(user.getGithub_url());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public boolean isConnected() {
        NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }
}
