package christmas.ui;

import static christmas.global.common.ChristmasMessage.ORDER_AMOUNT_BEFORE_DISCOUNT;
import static christmas.global.common.ChristmasMessage.ORDER_MENU_AND_QUANTITY;

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
        int totalAmount = 0;

        System.out.print(ORDER_AMOUNT_BEFORE_DISCOUNT.getText());

        for (Map.Entry<String, String> entry : menuMap.entrySet()) {
            totalAmount += calculateMenuPrice(entry);
        }

        printPrice(totalAmount);
    }

    private static int calculateMenuPrice(Entry<String, String> entry) {
        int price = 0;

        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(entry.getKey())) {
                price += menu.getPrice() * Integer.parseInt(entry.getValue());
            }
        }

        return price;
    }

    public void printPrice(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(price) + "원";
        System.out.println(formattedPrice);
    }
}
