package Data;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gilbert-mac on 2017. 10. 24..
 */

public class ReservationData {
    String date;
    String number;
    String uid;
    String accept; // "true", "false", "notyet"
    String manager;
    String reason;

    public ReservationData() {}

    public ReservationData(String date, String number, String uid, String accept, String manager, String reason) {
        this.date = date;
        this.number = number;
        this.uid = uid;
        this.accept = accept;
        this.manager = manager;
        this.reason = reason;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        //result.put("uid", uid);
        return result;
    }
}
