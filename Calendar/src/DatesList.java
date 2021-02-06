import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public interface DatesList {

    void generateSchedule();

    void addDate(String paymentDate);

    static Date dateParser(String parsedDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(parsedDate);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("Ошибка ввода даты в формате dd.MM.yyyy: %s", parsedDate));
        }
    }

}
