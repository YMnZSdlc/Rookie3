package bookstore.currency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RatesWrapper<T extends Rate> {
    private String table;
    private String no;
    private String effectiveDate;
    private List<T> rates;
}
