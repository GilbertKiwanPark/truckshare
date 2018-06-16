package Data;

/**
 * Created by gilbert-mac on 2017. 10. 9..
 */

public class Food {
    String imgurl;
    String name;

    public Food(String imgurl, String name) {
        this.imgurl = imgurl;
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
