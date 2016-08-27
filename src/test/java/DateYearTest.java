
import java.util.stream.IntStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yoshio Terada
 */
public class DateYearTest {

    @Test(expected = NumberFormatException.class)
    public void testInvalidConstructor() {
        //フォーマットがおかしいため例外送出
        YearAndMonth yearMonthArgString = new YearAndMonth("1508");
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidConstructor2() {
        //不正な月の入力値のため例外送出
        YearAndMonth yearMonthArgString = new YearAndMonth("22221508");
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidConstructor3() {
        //不正な年の入力値のため例外送出
        YearAndMonth yearMonthArgString = new YearAndMonth("100001");
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidConstructor4() {
        //不正な月の入力値のため例外送出
        YearAndMonth yearMonthArgInt = new YearAndMonth(2016, 13);
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidConstructor5() {
        //不正な年の入力値のため例外送出
        YearAndMonth yearMonthArgInt = new YearAndMonth(1400, 1);
    }

    @Test
    public void testGetYear() {
        //文字列入力で正しい年の場合
        YearAndMonth yearArgString = new YearAndMonth("201608");
        assertEquals(2016, yearArgString.getYear());
        //数値入力で正しい年の場合
        YearAndMonth yearArgInt = new YearAndMonth(2015, 9);
        assertEquals(2015, yearArgInt.getYear());
    }

    @Test
    public void testGetMonth() {
        //文字列入力で正しい月の場合
        YearAndMonth yearArgString = new YearAndMonth("201608");
        assertEquals(8, yearArgString.getMonth());
        //数値入力で正しい月の場合
        YearAndMonth yearArgInt = new YearAndMonth(2015, 9);
        assertEquals(9, yearArgInt.getMonth());
    }

    @Test
    public void testGetPreMonth() {
        //全ての月を確認する必要はないが
        IntStream.rangeClosed(1, 12)
                .forEach((int month) -> {
                    YearAndMonth yearMonth = new YearAndMonth(2016, month);
                    if (month == 1) {
                        assertEquals(12, yearMonth.getPreMonth());
                    } else {
                        assertEquals(month - 1, yearMonth.getPreMonth());
                    }
                });
    }

    @Test
    public void testGetNextMonth() {
        //全ての月を確認する必要はないが
        IntStream.rangeClosed(1, 12)
                .forEach((int month) -> {
                    YearAndMonth yearMonth = new YearAndMonth(2016, month);
                    if (month == 12) {
                        assertEquals(1, yearMonth.getNextMonth());
                    } else {
                        assertEquals(month + 1, yearMonth.getNextMonth());
                    }
                });
    }

    @Test
    public void testGetYYYYMMFormat() {
        YearAndMonth yearMonth = new YearAndMonth(2016, 1);
        assertEquals("201601", yearMonth.getYYYYMMFormat());
    }
}
