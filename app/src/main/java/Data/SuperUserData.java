package Data;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gilbert-mac on 2017. 10. 24..
 */

public class SuperUserData {
    String id;
    String uid;
    String pcroomId;
    String managerName;
    String managerNumber;
    ArrayList<SeatData> seatDataList;
    ArrayList<ReservationData> reservationDataList;
    ArrayList<VisitorData> visitorList = new ArrayList<>();

    public SuperUserData(){}

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
//        result.put("UID", UID);
//        result.put("NAME", NAME);
//        result.put("NICKNAME_CHANGEDATE", NICKNAME_CHANGEDATE);
//        result.put("NICKNAME", NICKNAME);
//        result.put("BIRTH", BIRTH);
//        result.put("EMAIL", EMAIL);
//        result.put("GENDER", GENDER);
//        result.put("ANONYMOUS_DATE", ANONYMOUS_DATE);
//        result.put("ANONYMOUS_NAME", ANONYMOUS_NAME);
//        result.put("ANONYMOUS_URL", ANONYMOUS_URL);
//        result.put("DISABLED", DISABLED);
//        result.put("FREEPOST_LIST", FREEPOST_LIST);
//        result.put("FREEREPLY_LIST", FREEREPLY_LIST);
//        result.put("QNAPOST_LIST", QNAPOST_LIST);
//        result.put("QNAREPLY_LIST", QNAREPLY_LIST);
//        result.put("INFOPOST_LIST", INFOPOST_LIST);
//        result.put("INFOREPLY_LIST", INFOREPLY_LIST);
//        result.put("PLAY_LIST", PLAY_LIST);
//        result.put("SOLOTASK_LIST", SOLOTASK_LIST);
//        result.put("TOGETHERTASK_LIST", TOGETHERTASK_LIST);

        return result;
    }
    // [END post_to_map]
}
