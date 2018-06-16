package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void shouldReturnZeroWhenDataIsEmpty() {
        //given
        String data1 = "";
        int expectedResult1 = 0;

        //when
        int actualResult = StringCalculator.adding(data1);

        //then
        Assertions.assertEquals(expectedResult1, actualResult);
    }

    @Test
    void shouldReturnZeroWhenDataIsBlank() {
        //given
        String data1 = "  ";
        int expectedResult1 = 0;

        //when
        int actualResult = StringCalculator.adding(data1);

        //then
        Assertions.assertEquals(expectedResult1, actualResult);
    }

    @Test
    void shouldReturnNumberWhenDataIsSingleNumber() {
        //given
        String data1 = "1";
        String data2 = " 2 ";
        String data3 = " 0 ";
        int expectedResult1 = 1;
        int expectedResult2 = 2;
        int expectedResult3 = 0;

        //when
        int actualResult1 = StringCalculator.adding(data1);
        int actualResult2 = StringCalculator.adding(data2);
        int actualResult3 = StringCalculator.adding(data3);

        //then
        Assertions.assertEquals(expectedResult1, actualResult1);
        Assertions.assertEquals(expectedResult2, actualResult2);
        Assertions.assertEquals(expectedResult3, actualResult3);
    }

    @Test
    void shouldThrowExeptionWhenDataIsLetter() {
        //given
        String data = " a ";

        //when
        try {
            int actualResult = StringCalculator.adding(data);
            fail("Didn't throw exeption");
        } catch (Exception exeptionName) {
            Assertions.assertEquals(IllegalArgumentException.class, exeptionName.getClass());
            Assertions.assertNotEquals(NumberFormatException.class, exeptionName.getClass());
        }

        //then
    }

    @Test
    void shouldReturnSumForManyDigitsSeparateWithComa() {
        //given
        String data = "1,2, 4 , 13   ";
        int expectedResult = 20;

        //when
        int actualResult = StringCalculator.adding(data);

        //then
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnSumForManyDigitsSeparateWithComaAndNewLines() {
        //given
        String data = "1,2 \n 4, 13   ";
        int expectedResult = 20;

        //when
        int actualResult = StringCalculator.adding(data);

        //then
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnSumForManyDigitsSeparateWithCustomDeliniter() {
        //given
        String data1 = "//****\n1****2****4****23";
        String data2 = "//;\n1;2;4;23";
        int expectedResult = 30;

        //when
        int actualResult1 = StringCalculator.adding(data1);
        int actualResult2 = StringCalculator.adding(data2);

        //then
        Assertions.assertEquals(expectedResult, actualResult1);
        Assertions.assertEquals(expectedResult, actualResult2);
    }

    @Test
    void shouldThrowExeptionWhenNegativeNumber() {
        //given
        String data = "-1";

        //when
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> StringCalculator.adding(data));

        //then

    }
}