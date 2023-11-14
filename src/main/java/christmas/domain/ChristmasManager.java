package christmas.domain;

import christmas.ui.InputView;
import christmas.ui.OutputView;

public class ChristmasManager {

    private final InputView inputView;
    private final OutputView outputView;
    private DayOfWeekCalculator dayOfWeekCalculator;

    public ChristmasManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputVisitDate();
        if (dayOfWeekCalculator.isChristmasDDay()) {
            System.out.println("크리스마스 디데이 할인");
        }
        if (dayOfWeekCalculator.isWeekend()) {
            System.out.println("주말 할인");
        }
        if (dayOfWeekCalculator.isSpecialDay()) {
            System.out.println("특별 할인");
        }
    }

    private void inputVisitDate() {
        try {
            int date = inputView.readVisitDate();
            this.dayOfWeekCalculator = new DayOfWeekCalculator(date);
        } catch (IllegalArgumentException e) {
            System.out.print("[ERROR] " + e.getMessage());
            inputVisitDate();
        }
    }
}
