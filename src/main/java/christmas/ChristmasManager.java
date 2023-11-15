package christmas;

import static christmas.global.common.ChristmasConstant.EVENT_BENEFIT_LIMIT_AMOUNT;
import static christmas.global.common.ChristmasConstant.GIFT_LIMIT_PRICE;
import static christmas.global.common.ChristmasConstant.SPECIAL_DAY_DISCOUNT_AMOUNT;

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
        if (outputView.calculateTotalOrderPrice(menuMap) < EVENT_BENEFIT_LIMIT_AMOUNT) {
            outputView.print(ChristmasMessage.NO_EVENT);

            return;
        }

        outputView.printGiftMenu(menuMap);
        EventBenefit eventBenefit = new EventBenefit();
        printBenefitDetail(eventBenefit, menuMap);
        outputView.printTotalEventBenefit(menuMap, calculateTotalBenefit(eventBenefit, menuMap));
        printPaymentAmountAfterDiscount(menuMap, eventBenefit);
        printEventBadge(eventBenefit, menuMap);
    }

    private void printPaymentAmountAfterDiscount(Map<String, String> menuMap, EventBenefit eventBenefit) {
        outputView.printPaymentAmountAfterDiscount(menuMap, calculateTotalBenefit(eventBenefit, menuMap));
    }

    private int calculateTotalBenefit(EventBenefit eventBenefit, Map<String, String> menuMap) {
        int totalBenefit = dayOfWeekCalculator.christmasDDayDiscountAmount();

        if (dayOfWeekCalculator.isSpecialDay()) {
            totalBenefit += SPECIAL_DAY_DISCOUNT_AMOUNT;
        }
        if (dayOfWeekCalculator.isWeekend()) {
            return totalBenefit + eventBenefit.weekendDiscountAmount(menuMap);
        }

        return totalBenefit + eventBenefit.weekdayDiscountAmount(menuMap);
    }

    private void printBenefitDetail(EventBenefit eventBenefit, Map<String, String> menuMap) {
        outputView.print(ChristmasMessage.BENEFIT_DETAIL);
        checkChristmasDDayAndSpecialDay();

        if (outputView.calculateTotalOrderPrice(menuMap) > GIFT_LIMIT_PRICE) {
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

    private void printEventBadge(EventBenefit eventBenefit, Map<String, String> menuMap) {
        outputView.print(ChristmasMessage.DECEMBER_EVENT_BADGE);
        if (calculateTotalBenefit(eventBenefit, menuMap) >= 20000) {
            System.out.print("별\n");
            return;
        }
        if (calculateTotalBenefit(eventBenefit, menuMap) >= 10000) {
            System.out.print("트리\n");
            return;
        }
        if (calculateTotalBenefit(eventBenefit, menuMap) >= 5000) {
            System.out.print("산타\n");
            return;
        }
        System.out.println("없음\n");
    }
}
