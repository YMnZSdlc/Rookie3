package bookstore.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RatesFromXmlWrapper extends RatesWrapper<RateFromXml> {

    @Override
    @JsonProperty("Table")
    public void setTable(String table) {
        super.setTable(table);
    }

    @Override
    @JsonProperty("No")
    public void setNo(String no) {
        super.setNo(no);
    }

    @Override
    @JsonProperty("EffectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        super.setEffectiveDate(effectiveDate);
    }

    @Override
    @JsonProperty("Rates")
    public void setRates(List<RateFromXml> rates) {
        super.setRates(rates);
    }
}
