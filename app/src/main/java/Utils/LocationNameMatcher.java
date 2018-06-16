package Utils;
import java.util.HashMap;

/**
 * Created by gilbert-mac on 2017. 11. 25..
 */

public class LocationNameMatcher {
    public HashMap<String, String> map = new HashMap<>();

    public LocationNameMatcher() {
        map.put("서울특별시", "seoul");

        map.put("도봉구", "dobonggu");
        map.put("동대문구", "dongdaemungu");
        map.put("동작구", "dongjakgu");
        map.put("은평구", "eunpyunggu");
        map.put("강북구", "gangbukgu");
        map.put("강동구", "gangdonggu");
        map.put("강남구", "gangnamgu");
        map.put("강서구", "gangseogu");
        map.put("금천구", "geumchungu");
        map.put("구로구", "gurogu");
        map.put("관악구", "gwanakgu");
        map.put("광진구", "gwangjingu");
        map.put("종로구", "jongrogu");
        map.put("중구", "junggu");
        map.put("중랑구", "jungranggu");
        map.put("마포구", "mapogu");
        map.put("노원구", "nowongu");
        map.put("서초구", "seochogu");
        map.put("서대문구", "seodaemungu");
        map.put("성북구", "seongbukgu");
        map.put("성동구", "seongdonggu");
        map.put("송파구", "songpagu");
        map.put("양천구", "yangchungu");
        map.put("영등포구", "yeongdeungpogu");
        map.put("용산구", "yongsangu");
    }
}
