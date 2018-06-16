package Data;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gilbert-mac on 2017. 10. 24..
 */

public class SeatData {
    String date;
    String managerName;
    String managerPhonenumber;
    String seatCount;

    public SeatData() {}
    public SeatData(String date, String managerName, String managerPhonenumber, String seatCount) {
        this.date = date;
        this.managerName = managerName;
        this.managerPhonenumber = managerPhonenumber;
        this.seatCount = seatCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhonenumber() {
        return managerPhonenumber;
    }

    public void setManagerPhonenumber(String managerPhonenumber) {
        this.managerPhonenumber = managerPhonenumber;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
//        result.put("UID", UID);
        return result;
    }
}
