package company.by.myappp.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import company.by.myappp.model.Picture;
import company.by.myappp.model.Picture_;
import company.by.myappp.model.Repository;
import company.by.myappp.model.User;
import io.objectbox.Box;
import io.objectbox.BoxStore;

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

        for(User user : users){
            userBox.put(user);
            savePicture(user.getAvatar_url());
        }
    }

    @Override
    public void saveRepos(long id_user, List<Repository> repos){
        Box<User> userBox = boxStore.boxFor(User.class);
        Box<Repository> repoBox = boxStore.boxFor(Repository.class);

        User user = userBox.get(id_user);


        for(Repository repo : repos){
            repo.getUser().setTarget(user);
            repoBox.put(repo);
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
                pictureBox.put(picture);

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public User getUserById(long id) {
        Box<User> userBox = boxStore.boxFor(User.class);
        return userBox.get(id);
    }

    @Override
    public List<User> getUsers(){
        Box<User> userBox = boxStore.boxFor(User.class);
        return userBox.getAll();
    }

    @Override
    public List<Repository> getRepos(long id_user){
        Box<User> userBox = boxStore.boxFor(User.class);
        return userBox.get(id_user).getRepos();
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


