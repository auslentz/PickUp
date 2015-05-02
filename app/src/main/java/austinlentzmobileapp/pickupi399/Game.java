package austinlentzmobileapp.pickupi399;

/**
 * Created by Austin on 3/5/2015.
 */
public class Game {
    private String mTitle;
    private String mTime;
    private String mSport;
    private String mDescription;
    private String mLatitude;
    private String mLongitude;


    public Game(String title, String time, String sport, String description, String latitude, String longitude) {
        mTitle = title;
        mTime = time;
        mSport = sport;
        mDescription = description;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public Game() {

    }
    public String getLatitude() {return mLatitude;}
    public void setLatitude(String latitude) { mLatitude = latitude;}
    public String getLongitude() {return mLongitude;}
    public void setLongitude(String longitude) {mLongitude = longitude;}
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public String getSport() {
        return mSport;
    }
    public void setSport(String sport) {
        mSport = sport;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    public String getTime() {
        return mTime;
    }
    public void setTime(String time) {
        mTime = time;
    }
}
