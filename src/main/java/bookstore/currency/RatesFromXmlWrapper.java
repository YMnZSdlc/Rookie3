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
    @JsonProperty
    public void setNo(String no) {
        super.setNo(no);
    }

    @Override
    @JsonProperty
    public void setEffectiveDate(String effectiveDate) {
        super.setEffectiveDate(effectiveDate);
    }

    @Override
    @JsonProperty
    public void setRates(List<RateFromXml> rates) {
        super.setRates(rates);
    }
}
