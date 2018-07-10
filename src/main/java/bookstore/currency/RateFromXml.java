package bookstore.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateFromXml extends Rate {

    @Override
    @JsonProperty("Mid")
    public void setMid(Double mid) {
        super.setMid(mid);
    }

    @Override
    @JsonProperty("Code")
    public void setCode(String code) {
        super.setCode(code);
    }

    @Override
    @JsonProperty
    public void setCurrency(String currency) {
        super.setCurrency(currency);
    }
}
