package phoneisure.core.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import phoneisure.core.common.Constants;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.permission.Permission;
import phoneisure.domain.model.role.Role;
import phoneisure.domain.service.auth.IAuthService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YJH on 2016/4/5 0005.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IAuthService authService;

    /**
     * 权限验证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ExtendSimplePrincipalCollection principalCollection = (ExtendSimplePrincipalCollection) principals;
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Account user = null;
        try {
            user = authService.searchByAccountName(userName);
        } catch (ApiRemoteCallFailedException e) {
            e.printStackTrace();
        }
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        authenticationInfo.setRoles(this.getAllRoles(user));
        authenticationInfo.setStringPermissions(this.getAllPermissions(user));
        return authenticationInfo;
    }

    /**
     * 获取用户所有角色
     *
     * @param user
     * @return
     */
    private Set<String> getAllRoles(Account user) {
        Set<String> roles = new HashSet<String>();
        List<Role> roleList = user.getRoles();
        if (!roleList.isEmpty()) {
            for (Role role : roleList) {
                if (role.getStatus() == EnableStatus.ENABLE) {
                    roles.add(role.getName());
                }
            }
        }
        return roles;
    }

    /**
     * 获取用户所有权限
     *
     * @param user
     * @return
     */
    private Set<String> getAllPermissions(Account user) {
        Set<String> permissions = new HashSet<String>();
        List<Role> roleList = user.getRoles();
        if (!roleList.isEmpty()) {
            for (Role role : roleList) {
                if (role.getStatus() == EnableStatus.ENABLE) {
                    List<Permission> permissionList = role.getPermissions();
                    if (!permissionList.isEmpty()) {
                        for (Permission permission : permissionList) {
                            if (permission.getStatus() == EnableStatus.ENABLE && permission.getAppKey().getName().equals(Constants.APP_KRY)) {
                                permissions.add(permission.getName());
                            }
                        }
                    }
                }
            }
        }
        return permissions;
    }

    /**
     * 登陆验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Account user = null;
        try {
            user = authService.searchByAccountName(token.getPrincipal().toString());
        } catch (ApiRemoteCallFailedException e) {
            e.printStackTrace();
        }

        if (null == user) {
            throw new UnknownAccountException(); //用户不存在
        } else {
            if (user.getStatus() == EnableStatus.DISABLE) {
                throw new LockedAccountException(); //用户被禁用
            }
        }

        ExtendSimpleAuthenticationInfo authenticationInfo = new ExtendSimpleAuthenticationInfo(
                user.getUserName(),
                user.getPassword(),
                new ExtendSimpleByteSource(user.getSalt()),
                getName(),
                user.getAppKey().getName());

        return authenticationInfo;
    }
}
