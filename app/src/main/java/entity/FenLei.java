package entity;

/**
 * Created by Administrator on 2017/5/9.
 * 推荐类，包括图片id，和名字
 */

public class FenLei {
    private int imgID;
    private String name;

    public FenLei(int imgID, String name) {
        this.imgID = imgID;
        this.name = name;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
