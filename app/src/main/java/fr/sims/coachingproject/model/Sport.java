package fr.sims.coachingproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dfour on 15/02/2016.
 */
@Table(name="Sport")
public class Sport extends Model implements Parcelable {

    @Column(name = "idDb", unique = true)
    @Expose
    @SerializedName("id")
    public long mIdDb;

    @Column(name = "name")
    @Expose
    @SerializedName("name")
    public String mName;

    public Sport() { }

    protected Sport(Parcel in) {
        mIdDb = in.readLong();
        mName = in.readString();
    }

    public static final Creator<Sport> CREATOR = new Creator<Sport>() {
        @Override
        public Sport createFromParcel(Parcel in) {
            return new Sport(in);
        }

        @Override
        public Sport[] newArray(int size) {
            return new Sport[size];
        }
    };

    public Sport saveOrUpdate(){
        Sport res = new Select().from(Sport.class).where("idDb = ?", mIdDb).executeSingle();
        if(res != null) {
            res.bindProperties(this);
            res.save();
        } else {
            this.save();
            res = this;
        }
        return res;
    }

    private void bindProperties(Sport sport) {
        this.mName = sport.mName;
    }

    public static Sport[] parseList(String json){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Sport[] res = null;
        try {
            res = gson.fromJson(json, Sport[].class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String toString() {
        return this.mName;
    }

    public long getmIdDb() {
        return mIdDb;
    }

    public static List<Sport> getAllSports(){
        return new Select().from(Sport.class).execute();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mIdDb);
        dest.writeString(mName);
    }
    public static void clear(){
        new Delete().from(Sport.class).execute();
    }

}
