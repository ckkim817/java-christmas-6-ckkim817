package christmas.domain;

public class DayOfWeekCalculator {

    private final int date;

    public DayOfWeekCalculator(int date) {
        this.date = date;
    }

    public boolean isChristmasDDay() {
        return this.date <= 25;
    }

    public boolean isWeekend() {
        return (this.date % 7) <= 2;
    }

    public boolean isSpecialDay() {
        return ((this.date % 7) == 3) || (this.date == 25);
    }

    public int ChristmasDDayDiscountAmount() {
        if (isChristmasDDay()) {
            return 1000 + ((date - 1) * 100);
        }

        return 0;
    }

    public String monthAndDate() {
        return "12월 " + this.date;
    }
}
