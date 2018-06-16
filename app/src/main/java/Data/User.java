package Data;

import java.util.HashMap;

/**
 * Created by gilbert-mac on 2017. 11. 30..
 */

public class User {
    String uid;
    String id;
    String mileage;
    String deviceId;
    String joinDate;
    String code;
    HashMap<String, ReviewData> review_map = new HashMap<>();

    public User(){};

    public User(String uid, String id, String mileage, String deviceId, String joinDate, String code, HashMap<String, ReviewData> review_map) {
        this.uid = uid;
        this.id = id;
        this.mileage = mileage;
        this.deviceId = deviceId;
        this.joinDate = joinDate;
        this.code = code;
        this.review_map = review_map;
    }

    public User(String uid, String id, String mileage, String deviceId, String joinDate, String code) {
        this.uid = uid;
        this.id = id;
        this.mileage = mileage;
        this.deviceId = deviceId;
        this.joinDate = joinDate;
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HashMap<String, ReviewData> getReview_map() {
        return review_map;
    }

    public void setReview_map(HashMap<String, ReviewData> review_map) {
        this.review_map = review_map;
    }

    public void addReviewMap(String key, ReviewData data) {
        try {
            review_map.put(key, data);
        }catch (Exception e) {
            review_map = new HashMap<>();
            review_map.put(key, data);
        }
    }

    public void addReviewMap(ReviewData data) {
        try {
            review_map.put(data.getKey(), data);
        }catch (Exception e) {
            review_map = new HashMap<>();
            review_map.put(data.getKey(), data);
        }
    }
}
