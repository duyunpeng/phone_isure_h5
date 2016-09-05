package phoneisure.domain.model.policy;

/**
 * Created by YJH on 2016/5/7.
 */
public class PolicyCount {

    private Integer all;
    private Integer back;
    private Integer claim;

    public PolicyCount() {
    }

    public PolicyCount(Integer all, Integer back, Integer claim) {
        this.all = all;
        this.back = back;
        this.claim = claim;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getBack() {
        return back;
    }

    public void setBack(Integer back) {
        this.back = back;
    }

    public Integer getClaim() {
        return claim;
    }

    public void setClaim(Integer claim) {
        this.claim = claim;
    }
}
