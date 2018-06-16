package tdd;

import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {


    public static int adding(String data) {
        if (StringUtils.isBlank(data)) {
            return 0;
        } else if (data.trim().length() == 1) {
            String regex = "\\d{1}";
            if(!data.trim().matches(regex)){
                throw new IllegalArgumentException();
            }
            return Integer.valueOf(data.trim());
        } else {
            if (data.startsWith("//")){
                Matcher matcher = Pattern.compile("//(.+)\n(.+)").matcher(data);
                matcher.matches();
                String delimiter = matcher.group(1);
                String newData = matcher.group(2);
                String [] split = newData.split(Pattern.quote(delimiter));
                return returnSum(split);
            }

            String[] split = data.split(",|\n");
            return returnSum(split);
        }
    }

    private static int returnSum(String[] split) {
        return Arrays.stream(split).map(String::trim)
                .map(Integer::valueOf)
                .peek(e-> {
                    if (e<0){
                        throw new IllegalArgumentException();
                    }
                })
                .reduce((a,b)->a+b)
                .get();
//                    .orElseThrow(IllegalAccessError::new);
    }
}
