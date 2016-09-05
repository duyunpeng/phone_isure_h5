package phoneisure.domain.model.idtype;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class IdType extends ConcurrencySafeEntity {

    private String name;            //证件名称
    private EnableStatus status;    //状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
