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
        Assertions.assertEquals(expectedResult1,actualResult);
    }
    @Test
    void shouldReturnZeroWhenDataIsBlank() {
        //given
        String data1 = "  ";
        int expectedResult1 = 0;
        //when
        int actualResult = StringCalculator.adding(data1);
        //then
        Assertions.assertEquals(expectedResult1,actualResult);
    }

    @Test
    void shouldReturnNumberWhenDataIsOneNumber() {
        //given
        String data1 = "1";
        String data2 = "  7 ";
        String data3 = "  0 ";

        int expectedResult1 = 1;
        int expectedResult2 = 7;
        int expectedResult3 = 0;
        //when
        int actualResult1 = StringCalculator.adding(data1);
        int actualResult2 = StringCalculator.adding(data2);
        int actualResult3 = StringCalculator.adding(data3);
        //then
        Assertions.assertEquals(expectedResult1,actualResult1);
        Assertions.assertEquals(expectedResult2,actualResult2);
        Assertions.assertEquals(expectedResult3,actualResult3);
    }

    @Test
    void shouldThrowExceptionWhenLetter() {
        //given
        String data = "a ";
        //when
        try{
            int actualResult = StringCalculator.adding(data);
            fail("Didn't throw exception.");
        } catch (Exception exception){
            Assertions.assertEquals(IllegalArgumentException.class,exception.getClass());
            Assertions.assertNotEquals(NumberFormatException.class,exception.getClass());

        }
        //then
    }
}