package christmas.ui;

import static christmas.global.common.ChristmasMessage.ORDER_MENU_AND_QUANTITY;

import christmas.global.common.ChristmasMessage;
import java.util.Map;

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
}
