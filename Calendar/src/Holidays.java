import java.util.Calendar;
import java.util.GregorianCalendar;

public enum Holidays {
    NEW_YEAR(new GregorianCalendar(0, Calendar.JANUARY, 1)),
    CHRISTMAS(new GregorianCalendar(0, Calendar.JANUARY, 7)),
    ARMY_DAY(new GregorianCalendar(0, Calendar.FEBRUARY, 23)),
    WOMAN_DAY(new GregorianCalendar(0, Calendar.MARCH, 8)),
    LABOR_DAY(new GregorianCalendar(0, Calendar.MAY, 1)),
    VICTORY_DAY(new GregorianCalendar(0, Calendar.MAY, 9)),
    RUSSIA_DAY(new GregorianCalendar(0, Calendar.JUNE, 12)),
    INDEPENDENCE_DAY(new GregorianCalendar(0, Calendar.NOVEMBER, 4));

    private final Calendar date;

    Holidays(Calendar date) {
        this.date = date;
    }

    public Calendar getDate() {
        return date;
    }
}
