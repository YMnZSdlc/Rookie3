package example;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TestOfTests {

    @Test
    void name2() {
        List integers1 = Arrays.asList(new Integer[]{1, 2, 3});
        List integers2 = Arrays.asList(new int[]{1, 2, 3});
        System.out.println();
    }

    @Test
    void name() {
        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (!NumberUtils.isDigits(o1) && !NumberUtils.isDigits(o2)) {
                    return o1.compareTo(o2);
                }
                if (!NumberUtils.isDigits(o1)) {
                    return -1;
                }
                if (!NumberUtils.isDigits(o2)) {
                    return 54515; // Po prostu wartość większa od zera
                }
                return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
            }
        };

        Comparator<String> customComparatorLambda = (a,b) -> Integer.valueOf(a).compareTo(Integer.valueOf(b));

        TreeSet<String> objects = new TreeSet<>(customComparator);
        objects.add("14");
        objects.add("123");
        objects.add("12");
        objects.add("12");
        objects.add("21");
        objects.add("8");
        objects.add("51");
        objects.add("a");
        objects.add("a");
        objects.add("b");
        System.out.println(objects);
    }


}
