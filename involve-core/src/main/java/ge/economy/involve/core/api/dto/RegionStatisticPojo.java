package ge.economy.involve.core.api.dto;

import java.math.BigDecimal;

/**
 * Created by nino on 11/20/16.
 */
public class RegionStatisticPojo {

    private Integer statisticTypeId;
    private BigDecimal count;

    public RegionStatisticPojo(Integer statisticTypeId, BigDecimal count) {
        this.statisticTypeId = statisticTypeId;
        this.count = count;
    }

    public Integer getStatisticTypeId() {
        return statisticTypeId;
    }

    public void setStatisticTypeId(Integer statisticTypeId) {
        this.statisticTypeId = statisticTypeId;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
