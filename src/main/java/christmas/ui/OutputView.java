package christmas.ui;

import static christmas.global.common.ChristmasMessage.CHAMPAGNE_GIFT;
import static christmas.global.common.ChristmasMessage.GIFT_MENU;
import static christmas.global.common.ChristmasMessage.NO_GIFT;
import static christmas.global.common.ChristmasMessage.ORDER_AMOUNT_BEFORE_DISCOUNT;
import static christmas.global.common.ChristmasMessage.ORDER_MENU_AND_QUANTITY;
import static christmas.global.common.ChristmasMessage.TOTAL_BENEFIT_AMOUNT;

import christmas.global.common.ChristmasMessage;
import christmas.global.common.Menu;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public void print(ChristmasMessage message) {
        System.out.print(message.getText());
    }

    public void printOrderMenuAndQuantity(Map<String, String> menuMap) {
        System.out.print(ORDER_MENU_AND_QUANTITY.getText());

        for (Map.Entry<String, String> entry : menuMap.entrySet()) {
            System.out.print(entry.getKey() + " " + entry.getValue() + "개\n");
        }
    }

    public void printOrderAmountBeforeDiscount(Map<String, String> menuMap) {
        int totalAmount = calculateTotalOrderPrice(menuMap);

        System.out.print(ORDER_AMOUNT_BEFORE_DISCOUNT.getText());
        printPrice(totalAmount);
    }

    public void printGiftMenu(Map<String, String> menuMap) {
        System.out.print(GIFT_MENU.getText());
        System.out.print(checkGiftMenu(menuMap));
    }

    public void printTotalEventBenefit(Map<String, String> menuMap, int price) {
        System.out.print(TOTAL_BENEFIT_AMOUNT.getText());
        int totalAmount = calculateTotalOrderPrice(menuMap);

        if (totalAmount > 120000) {
            price += 25000;
        }
        if (price == 0) {
            printPrice(price);
            return;
        }

        printBenefitPrice(price);
    }

    private void printPrice(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(price) + "원\n";
        System.out.print(formattedPrice);
    }

    private void printBenefitPrice(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(price) + "원\n";
        System.out.print("-" + formattedPrice);
    }

    private String checkGiftMenu(Map<String, String> menuMap) {
        int totalAmount = calculateTotalOrderPrice(menuMap);

        if (totalAmount > 120000) {
            return CHAMPAGNE_GIFT.getText();
        }

        return NO_GIFT.getText();
    }

    private int calculateTotalOrderPrice(Map<String, String> menuMap) {
        int totalAmount = 0;

        for (Entry<String, String> entry : menuMap.entrySet()) {
            totalAmount += calculateMenuPrice(entry);
        }

        return totalAmount;
    }

    private int calculateMenuPrice(Entry<String, String> entry) {
        int price = 0;

        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(entry.getKey())) {
                price = menu.getPrice() * Integer.parseInt(entry.getValue());
            }
        }

        return price;
    }
}
