package Data;

/**
 * Created by gilbert-mac on 2017. 11. 30..
 */

public class ReviewData {
    String key;
    String date;
    String uid;
    String anonymous_name;
    float stars;
    String review;
    String pcroom_id;

    public ReviewData() {
    }

    public ReviewData(String date, String uid, String anonymous_name, float stars, String review, String pcroom_id) {
        this.date = date;
        this.uid = uid;
        this.anonymous_name = anonymous_name;
        this.stars = stars;
        this.review = review;
        this.pcroom_id = pcroom_id;
    }

    public ReviewData(String key, String date, String uid, String anonymous_name, float stars, String review, String pcroom_id) {
        this.key = key;
        this.date = date;
        this.uid = uid;
        this.anonymous_name = anonymous_name;
        this.stars = stars;
        this.review = review;
        this.pcroom_id = pcroom_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAnonymous_name() {
        return anonymous_name;
    }

    public void setAnonymous_name(String anonymous_name) {
        this.anonymous_name = anonymous_name;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPcroom_id() {
        return pcroom_id;
    }

    public void setPcroom_id(String pcroom_id) {
        this.pcroom_id = pcroom_id;
    }
}
