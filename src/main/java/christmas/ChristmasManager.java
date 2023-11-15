package christmas;

import static christmas.global.common.ChristmasMessage.EVENT_BENEFIT_PREVIEW;

import christmas.domain.DayOfWeekCalculator;
import christmas.domain.EventBenefit;
import christmas.global.Validator;
import christmas.ui.InputView;
import christmas.ui.OutputView;
import java.util.Map;

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
        inputOrderMenu();
        printEventBenefitPreview();
        printOrder();
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

    private void inputOrderMenu() {
        try {
            inputView.readOrderMenu();
        } catch (IllegalArgumentException e) {
            System.out.print("[ERROR] " + e.getMessage());
            inputOrderMenu();
        }
    }

    private void printEventBenefitPreview() {

        System.out.print(dayOfWeekCalculator.monthAndDate() + EVENT_BENEFIT_PREVIEW.getText());
    }

    private void printOrder() {
        Map<String, String> menuMap = Validator.menuMapping();

        outputView.printOrderMenuAndQuantity(menuMap);
        outputView.printOrderAmountBeforeDiscount(menuMap);
        outputView.printGiftMenu(menuMap);
        EventBenefit eventBenefit = new EventBenefit();
        outputView.printTotalEventBenefit(menuMap, calculateTotalBenefit(eventBenefit, menuMap));
    }

    private int calculateTotalBenefit(EventBenefit eventBenefit, Map<String, String> menuMap) {
        int totalBenefit = dayOfWeekCalculator.ChristmasDDayDiscountAmount();

        if (dayOfWeekCalculator.isSpecialDay()) {
            totalBenefit += 1000;
        }
        if (dayOfWeekCalculator.isWeekend()) {
            return totalBenefit += eventBenefit.weekendDiscountAmount(menuMap);
        }

        return totalBenefit += eventBenefit.weekdayDiscountAmount(menuMap);
    }
}
