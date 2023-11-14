package christmas.domain;

import christmas.ui.InputView;
import christmas.ui.OutputView;

public class ChristmasManager {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputVisitDate();
    }

    private DayOfWeekCalculator inputVisitDate() {
        try {
            int date = inputView.readVisitDate();
            return new DayOfWeekCalculator(date);
        } catch (IllegalArgumentException e) {
            System.out.print("[ERROR] " + e.getMessage());
            return inputVisitDate();
        }
    }
}
