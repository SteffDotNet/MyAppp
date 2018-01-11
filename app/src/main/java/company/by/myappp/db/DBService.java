package company.by.myappp.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import company.by.myappp.model.Picture;
import company.by.myappp.model.Picture_;
import company.by.myappp.model.Repository;
import company.by.myappp.model.Repository_;
import company.by.myappp.model.User;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.Property;

/**
 * Created by Egor on 10.01.2018.
 */

public class DBService implements DBServiceInterface {
    private BoxStore boxStore;
    private Context context;

    public DBService(Context context) {
        this.context = context;
        boxStore = ((App)context.getApplicationContext()).getBoxStore();
    }

    @Override
    public void saveUsers(List<User> users){
        Box<User> userBox = boxStore.boxFor(User.class);
        Box<Picture> pictureBox = boxStore.boxFor(Picture.class);

        for(User user : users){
            long id = userBox.put(user);
            savePicture(user.getAvatar_url());
            Log.d("TAG", "insert user to db, id = " + id);
        }
    }

    @Override
    public void saveRepos(long idUser, List<Repository> repos){
        Box<User> userBox = boxStore.boxFor(User.class);
        Box<Repository> repoBox = boxStore.boxFor(Repository.class);

        Log.d("TAG", "user id = " + idUser);
        User user = userBox.get(idUser);


        for(Repository repo : repos){
            repo.getUser().setTarget(user);
            long id = repoBox.put(repo);

            Log.d("TAG", "insert repo to db, id = " + id);
        }
    }

    @Override
    public void savePicture(String path) {

        ImageView imageView = new ImageView(context);



        Picasso.with(context).load(path).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Box<Picture> pictureBox = boxStore.boxFor(Picture.class);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Picture picture = new Picture(path, getByteArray(bitmap));
                long id = pictureBox.put(picture);

                Log.d("TAG", "insert picture id = " + id);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public List<User> getUsers(){
        Box<User> userBox = boxStore.boxFor(User.class);
        return userBox.getAll();
    }

    @Override
    public List<Repository> getRepos(long id){
        Box<User> userBox = boxStore.boxFor(User.class);
        Box<Repository> repoBox = boxStore.boxFor(Repository.class);

        return userBox.get(id).getRepos();
    }


    @Override
    public Bitmap getPicture(String path){
        Box<Picture> pictureBox = boxStore.boxFor(Picture.class);
        Picture picture = pictureBox.find(Picture_.path, path).get(0);

        return getImage(picture.getBitmap());
    }

    public byte[] getByteArray(Bitmap bitmap){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        return os.toByteArray();
    }

    public Bitmap getImage(byte[] array){
        ByteArrayInputStream is = new ByteArrayInputStream(array);
        return BitmapFactory.decodeStream(is);
    }
}


