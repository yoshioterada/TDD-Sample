import java.util.EnumMap;

/**
 *
 * @author Yoshio Terada
 */
public class YearAndMonth {

    private final static int YEAR_START_THRESHOLD = 1500;
    private final static int YEAR_END_THRESHOLD = 3000;

    private final static int YEAR_MONTH_FORMAT_LENGTH = 6;
    private final static String MONTH_PREFIX_STRING = "0";

    private final int year;
    private final int month;
    
    private static enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
        JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
    }

    private final static EnumMap<Month, Integer> MONTH_VALUE_MAP;

    static {
        MONTH_VALUE_MAP = new EnumMap<>(Month.class);
        MONTH_VALUE_MAP.put(Month.JANUARY, 1);
        MONTH_VALUE_MAP.put(Month.FEBRUARY , 2);
        MONTH_VALUE_MAP.put(Month.MARCH , 3);
        MONTH_VALUE_MAP.put(Month.APRIL , 4);
        MONTH_VALUE_MAP.put(Month.MAY , 5);
        MONTH_VALUE_MAP.put(Month.JUNE , 6);
        MONTH_VALUE_MAP.put(Month.JULY , 7);
        MONTH_VALUE_MAP.put(Month.AUGUST , 8);
        MONTH_VALUE_MAP.put(Month.SEPTEMBER , 9);
        MONTH_VALUE_MAP.put(Month.OCTOBER , 10);
        MONTH_VALUE_MAP.put(Month.NOVEMBER , 11);
        MONTH_VALUE_MAP.put(Month.DECEMBER , 12);
    }
    
    public YearAndMonth(String yyyyMM) {
        int length = yyyyMM.length();
        if (length != YEAR_MONTH_FORMAT_LENGTH) {
            throw new NumberFormatException("Invalid format");
        }
        String yearString = yyyyMM.substring(0, 4);
        String monthString = yyyyMM.substring(5, 6);
        Integer intYear = Integer.valueOf(yearString);
        Integer intMonth = Integer.valueOf(monthString);
        checkThresholdValueOfYearMonth(intYear, intMonth);
        this.year = intYear;
        this.month = intMonth;
    }

    public YearAndMonth(int year, int month) {
        checkThresholdValueOfYearMonth(year, month);
        this.year = year;
        this.month = month;
    }
    
    private void checkThresholdValueOfYearMonth(int year, int month) {
        if (year < YEAR_START_THRESHOLD || year > YEAR_END_THRESHOLD) {
            //TODO : should write new Exception for this
            throw new NumberFormatException("Invalid format");
        }
        if (month < MONTH_VALUE_MAP.get(Month.JANUARY) 
                || month > MONTH_VALUE_MAP.get(Month.DECEMBER)) {
            //TODO : should write new Exception for this
            throw new NumberFormatException("Invalid format");
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getPreMonth() {
        return (month == MONTH_VALUE_MAP.get(Month.JANUARY)) ? 
                MONTH_VALUE_MAP.get(Month.DECEMBER) : 
                month - 1;
    }

    public int getNextMonth() {
        return (month == MONTH_VALUE_MAP.get(Month.DECEMBER)) ? 
                MONTH_VALUE_MAP.get(Month.JANUARY) : 
                month + 1;
    }

    public String getYYYYMMFormat() {
        StringBuilder yyyymm = new StringBuilder();
        yyyymm.append(Integer.toString(year));
        if (month < MONTH_VALUE_MAP.get(Month.OCTOBER)) {
            yyyymm.append(MONTH_PREFIX_STRING);
        }
        yyyymm.append((Integer.toString(month)));
        return yyyymm.toString();
    }
}
