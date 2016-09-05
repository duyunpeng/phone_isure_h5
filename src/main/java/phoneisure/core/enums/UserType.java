package phoneisure.core.enums;

/**
 * Created by YJH on 2016/4/23.
 */
public enum UserType {

    ALL("全部", 0, Boolean.TRUE),
    MERCHANT("商户", 1, Boolean.FALSE),
    AGENT("代理", 2, Boolean.FALSE);

    private UserType(String name, int value, Boolean onlyQuery) {
        this.name = name;
        this.value = value;
        this.onlyQuery = onlyQuery;
    }

    private String name;

    private int value;

    private Boolean onlyQuery;                  // 仅用于页面查询和业务逻辑无关

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Boolean isOnlyQuery() {
        return onlyQuery;
    }

}
