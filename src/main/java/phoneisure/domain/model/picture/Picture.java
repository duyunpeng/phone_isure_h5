package phoneisure.domain.model.picture;

import phoneisure.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by YJH on 2016/4/12.
 */
public class Picture extends ConcurrencySafeEntity {

    private String picPath;
    private String miniPicPath;
    private String mediumPicPath;

    private Double size;
    private String name;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getMiniPicPath() {
        return miniPicPath;
    }

    public void setMiniPicPath(String miniPicPath) {
        this.miniPicPath = miniPicPath;
    }

    public String getMediumPicPath() {
        return mediumPicPath;
    }

    public void setMediumPicPath(String mediumPicPath) {
        this.mediumPicPath = mediumPicPath;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
