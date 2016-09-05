package phoneisure.domain.model.permission;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.appkey.AppKey;

import java.util.Date;

/**
 * Created by YJH on 2016/3/30.
 */
public class Permission extends ConcurrencySafeEntity {

    private String name;            //权限名称
    private String description;        //权限描述
    private String value;           //权限默认值
    private AppKey appKey;          //应用标识
    private EnableStatus status;    //状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AppKey getAppKey() {
        return appKey;
    }

    public void setAppKey(AppKey appKey) {
        this.appKey = appKey;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public Permission() {
        super();
    }

    public Permission(String name, String description, String value, AppKey appKey, EnableStatus status) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.appKey = appKey;
        this.status = status;
    }
}
