package christmas.domain;

import static christmas.global.common.ChristmasConstant.THIS_YEAR_NUMBER;
import static christmas.global.common.MenuType.DESSERT;
import static christmas.global.common.MenuType.MAIN;

import christmas.global.common.Menu;
import java.util.Map;
import java.util.Map.Entry;

public class EventBenefit {

    public int weekdayDiscountAmount(Map<String, String> menuMap) {
        int totalDessertMenuQuantity = 0;

        for (Entry<String, String> entry : menuMap.entrySet()) {
            totalDessertMenuQuantity += calculateDessertMenuQuantity(entry);
        }

        return totalDessertMenuQuantity * THIS_YEAR_NUMBER;
    }

    private static int calculateDessertMenuQuantity(Entry<String, String> entry) {
        int quantity = 0;

        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(entry.getKey()) && menu.getType().equals(DESSERT)) {
                quantity = Integer.parseInt(entry.getValue());
            }
        }

        return quantity;
    }

    public int weekendDiscountAmount(Map<String, String> menuMap) {
        int totalMainMenuQuantity = 0;

        for (Entry<String, String> entry : menuMap.entrySet()) {
            totalMainMenuQuantity += calculateMainMenuQuantity(entry);
        }

        return totalMainMenuQuantity * 2023;
    }

    private static int calculateMainMenuQuantity(Entry<String, String> entry) {
        int quantity = 0;

        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(entry.getKey()) && menu.getType().equals(MAIN)) {
                quantity = Integer.parseInt(entry.getValue());
            }
        }

        return quantity;
    }
}
