package company.by.myappp.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Egor on 10.01.2018.
 */

@Entity
public class Picture {
    @Id
    private long id;

    private String path;
    private byte[] bitmap;

    public Picture() {
    }

    public Picture(String path, byte[] bitmap) {
        this.path = path;
        this.bitmap = bitmap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }
}
