package christmas.domain;

import static christmas.global.common.ChristmasConstant.CHRISTMAS_D_DAY;
import static christmas.global.common.ChristmasConstant.SUNDAY_NUMBER;
import static christmas.global.common.ChristmasConstant.WEEKEND_NUMBER;
import static christmas.global.common.ChristmasConstant.WEEK_NUMBER;

public class DayOfWeekCalculator {

    private final int date;

    public DayOfWeekCalculator(int date) {
        this.date = date;
    }

    public boolean isChristmasDDay() {
        return this.date <= CHRISTMAS_D_DAY;
    }

    public boolean isWeekend() {
        return (this.date % WEEK_NUMBER) <= WEEKEND_NUMBER;
    }

    public boolean isSpecialDay() {
        return ((this.date % WEEK_NUMBER) == SUNDAY_NUMBER) || (this.date == CHRISTMAS_D_DAY);
    }

    public int christmasDDayDiscountAmount() {
        if (isChristmasDDay()) {
            return 1_000 + ((date - 1) * 100);
        }

        return 0;
    }

    public String monthAndDate() {
        return "12월 " + this.date;
    }
}
