package phoneisure.domain.service.permission.command;


import phoneisure.core.enums.EnableStatus;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public class ListPermissionCommand {

    private EnableStatus status;
    private String appKey;

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

}
