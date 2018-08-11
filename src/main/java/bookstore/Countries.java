package bookstore;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Countries {
    POLAND("PL", "Polska"), RUSSIA("RUS", "Rosja"),
    GERMANY("DE", "Niemcy");
    private String symbol;
    private String plName;
}
