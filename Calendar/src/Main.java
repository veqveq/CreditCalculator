import java.text.ParseException;

public class Main {
    /**
     * Задача: построить календарь в котором на заданное количество месяцев определяется дата платежа по кредиту
     * Исходные данные:
     * 1. Дата первого платежа
     * 2. Срок кредита
     * Условия:
     * 1. При выпадении на выходной/праздничный день платеж переносится на следующий будний день
     */
    public static void main(String[] args) throws ParseException {
        CreditCalendar calendar = new CreditCalendar("24.11.2020", 10, 60);
        calendar.addDate("14.1.2021");
        System.out.println(calendar);
    }
}
