package phoneisure.domain.model.phonebrand;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class PhoneBrand extends ConcurrencySafeEntity {

    private String name;            //名称
    private Integer sort;           //排序
    private EnableStatus status;    //状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
