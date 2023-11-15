package christmas;

import christmas.domain.DayOfWeekCalculator;
import christmas.domain.EventBenefit;
import christmas.global.Validator;
import christmas.global.common.ChristmasMessage;
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
        System.out.print(dayOfWeekCalculator.monthAndDate());
        outputView.print(ChristmasMessage.EVENT_BENEFIT_PREVIEW);
    }

    private void printOrder() {
        Map<String, String> menuMap = Validator.menuMapping();

        outputView.printOrderMenuAndQuantity(menuMap);
        outputView.printOrderAmountBeforeDiscount(menuMap);
        printEventBenefitPreview(menuMap);
    }

    private void printEventBenefitPreview(Map<String, String> menuMap) {
        if (outputView.calculateTotalOrderPrice(menuMap) < 10000) {
            outputView.print(ChristmasMessage.NO_EVENT);

            return;
        }

        outputView.printGiftMenu(menuMap);
        EventBenefit eventBenefit = new EventBenefit();
        printBenefitDetail(eventBenefit, menuMap);
        outputView.printTotalEventBenefit(menuMap, calculateTotalBenefit(eventBenefit, menuMap));
    }

    private int calculateTotalBenefit(EventBenefit eventBenefit, Map<String, String> menuMap) {
        int totalBenefit = dayOfWeekCalculator.christmasDDayDiscountAmount();

        if (dayOfWeekCalculator.isSpecialDay()) {
            totalBenefit += 1000;
        }
        if (dayOfWeekCalculator.isWeekend()) {
            return totalBenefit + eventBenefit.weekendDiscountAmount(menuMap);
        }

        return totalBenefit + eventBenefit.weekdayDiscountAmount(menuMap);
    }

    private void printBenefitDetail(EventBenefit eventBenefit, Map<String, String> menuMap) {
        outputView.print(ChristmasMessage.BENEFIT_DETAIL);
        checkChristmasDDayAndSpecialDay();

        if (outputView.calculateTotalOrderPrice(menuMap) > 120000) {
            outputView.printGiftEventAmount();
        }
        if (dayOfWeekCalculator.isWeekend()) {
            outputView.printWeekendDiscountAmount(eventBenefit.weekendDiscountAmount(menuMap));
            return;
        }
        outputView.printWeekdayDiscountAmount(eventBenefit.weekdayDiscountAmount(menuMap));
    }

    private void checkChristmasDDayAndSpecialDay() {
        if (dayOfWeekCalculator.isChristmasDDay()) {
            outputView.printChristmasDDayDiscountAmount(dayOfWeekCalculator.christmasDDayDiscountAmount());
        }
        if (dayOfWeekCalculator.isSpecialDay()) {
            outputView.printSpecialDiscountAmount();
        }
    }
}
