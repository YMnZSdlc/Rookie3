package tdd;

import org.junit.platform.commons.util.StringUtils;

public class StringCalculator {

    public static int adding(String data) {
        if(StringUtils.isBlank(data)){
            return  0;
        } else if(data.trim().length() == 1){
            String regex = "\\d{1}";
            if(!data.trim().matches(regex)){
                throw new IllegalArgumentException();
            }
            return Integer.valueOf(data.trim());
        }
        return -1; //todo
    }
}
