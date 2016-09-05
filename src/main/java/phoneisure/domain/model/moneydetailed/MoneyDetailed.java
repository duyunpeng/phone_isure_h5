package phoneisure.domain.model.moneydetailed;

import phoneisure.core.enums.FlowType;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.merchant.Merchant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/4/21.
 */
public class MoneyDetailed extends ConcurrencySafeEntity {

    private Merchant merchant;      //商户
    private FlowType flowType;      //流向类型
    private BigDecimal money;       //金额
    private String explain;         //说明

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
