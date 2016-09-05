package phoneisure.domain.model.role;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.permission.Permission;

import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public class Role extends ConcurrencySafeEntity {

    private String name;                    //角色名称
    private String description;                //角色描述
    private List<Permission> permissions;   //角色包含的权限集合
    private AppKey appKey;                  //应用标识
    private EnableStatus status;            //状态

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
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

    public Role() {
        super();
    }

    public Role(String name, String description, List<Permission> permissions, AppKey appKey, EnableStatus status) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.appKey = appKey;
        this.status = status;
    }
}
