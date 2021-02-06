import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreditCalendar implements DatesList {
    private GregorianCalendar firstPayment;
    private GregorianCalendar lastPayment;
    private int quantityPayments;
    private List<Date> schedule;

    public CreditCalendar(String startOffer, int payDay, int quantityPayments) {
        this(DatesList.dateParser(startOffer), payDay, quantityPayments);
    }

    public CreditCalendar(Date startOffer, int payDay, int quantityPayments) {
        this.quantityPayments = quantityPayments;
        this.schedule = new ArrayList<>(quantityPayments);
        calculateMainPayments(payDay, startOffer);
        checkWeekend(this.firstPayment);
        generateSchedule();
    }

    private void calculateMainPayments(int payDay, Date start) {
        GregorianCalendar startOffer = new GregorianCalendar();
        startOffer.setTime(start);
        this.firstPayment = calculateFirstPayment(payDay, startOffer);
        this.lastPayment = calculateLastPayment(startOffer);
    }

    private GregorianCalendar calculateFirstPayment(int paymentDay, GregorianCalendar startOffer) {
        if (paymentDay > startOffer.get(Calendar.DAY_OF_MONTH)) {
            return new GregorianCalendar(startOffer.get(Calendar.YEAR), startOffer.get(Calendar.MONTH), paymentDay);
        } else {
            GregorianCalendar currentStart = new GregorianCalendar();
            currentStart.setTime(startOffer.getTime());
            currentStart.add(Calendar.MONTH, +1);
            currentStart.set(Calendar.DAY_OF_MONTH, paymentDay);
            return currentStart;
        }
    }

    private GregorianCalendar calculateLastPayment(GregorianCalendar startOffer) {
        if (firstPayment.get(Calendar.DAY_OF_MONTH) < startOffer.get(Calendar.DAY_OF_MONTH)) {
            startOffer.add(Calendar.MONTH, quantityPayments);
            startOffer.add(Calendar.DAY_OF_WEEK, +1);
            quantityPayments += 1;
        } else {
            startOffer.setTime(firstPayment.getTime());
            startOffer.add(Calendar.MONTH, quantityPayments - 1);
        }
        checkWeekend(startOffer);
        return startOffer;
    }

    public void generateSchedule() {
        schedule.add(firstPayment.getTime());
        for (int i = 1; i < quantityPayments - 1; i++) {
            GregorianCalendar paymentDate = new GregorianCalendar();
            paymentDate.setTime(firstPayment.getTime());
            paymentDate.add(Calendar.MONTH, +i);
            checkWeekend(paymentDate);
            schedule.add(paymentDate.getTime());
        }
        schedule.add(lastPayment.getTime());
    }

    private int findNearIndex(Date date) {
        int startIn = 0;
        int endIn = schedule.size() - 1;
        if (schedule.get(startIn).getTime() >= date.getTime()) {
            return startIn;
        } else if (schedule.get(endIn).getTime() <= date.getTime()) {
            return endIn + 1;
        } else {
            while (startIn < endIn) {
                int tmpIn = (startIn + endIn) / 2;
                if (schedule.get(tmpIn).getTime() <= date.getTime()
                        && schedule.get(tmpIn + 1).getTime() >= date.getTime()) {
                    return tmpIn + 1;
                } else if (schedule.get(tmpIn).getTime() > date.getTime()) {
                    endIn = tmpIn;
                } else {
                    startIn = tmpIn;
                }
            }
            return -1;
        }
    }

    public void addDate(String paymentDate) {
        Date date = DatesList.dateParser(paymentDate);
        schedule.add(findNearIndex(date), date);
    }

    private void checkWeekend(Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY:
                calendar.add(Calendar.DAY_OF_WEEK, +2);
                break;
            case Calendar.SUNDAY:
                calendar.add(Calendar.DAY_OF_WEEK, +1);
                break;
            default:
                break;
        }
        checkHoliday(calendar);
    }

    private void checkHoliday(Calendar calendar) {
        for (Holidays holiday : Holidays.values()) {
            if (calendar.get(Calendar.DAY_OF_MONTH) == holiday.getDate().get(Calendar.DAY_OF_MONTH)
                    && calendar.get(Calendar.MONTH) == holiday.getDate().get(Calendar.MONTH)) {
                calendar.add(Calendar.DAY_OF_WEEK, +1);
                checkWeekend(calendar);
            }
        }
    }

    @Override
    public String toString() {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        DateFormat df = new SimpleDateFormat("EE - dd.MM.yyyy");
        for (Date payment : schedule) {
            count++;
            sb.append(count)
                    .append(" ")
                    .append(df.format(payment))
                    .append("\n");
        }
        return sb.toString();
    }
}
