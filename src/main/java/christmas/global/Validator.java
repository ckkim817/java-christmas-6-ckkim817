package christmas.global;

import static christmas.global.RegularExpression.ORDER_MENU_PATTERN;
import static christmas.global.RegularExpression.VISIT_DATE_PATTERN;
import static christmas.global.common.ChristmasMessage.ORDER_MENU_ERROR;
import static christmas.global.common.ChristmasMessage.ORDER_ONLY_BEVERAGE_ERROR;
import static christmas.global.common.ChristmasMessage.OVER_QUANTITY_LIMIT_ERROR;
import static christmas.global.common.ChristmasMessage.VISIT_DATE_ERROR;

import christmas.global.common.Menu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Validator {

    private static final List<String> menuNames = new ArrayList<>();
    private static final List<String> menuQuantities = new ArrayList<>();

    public static void visitDate(String input) {
        if (!input.matches(VISIT_DATE_PATTERN)) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR.getText());
        }
    }

    public static void orderMenu(List<String> menus) {
        for (String menu : menus) {
            orderMenuFormat(menu);
            orderMenuName(menu);
        }
        orderMenuDuplicate();
        orderCaution();
    }

    public static Map<String, String> menuMapping() {
        if (menuNames.size() != menuQuantities.size()) {
            throw new IllegalArgumentException("두 리스트의 크기가 같아야 합니다.");
        }

        Map<String, String> menuMap = new HashMap<>();

        for (int i = 0; i < menuNames.size(); i++) {
            menuMap.put(menuNames.get(i), menuQuantities.get(i));
        }

        return menuMap;
    }

    private static void orderMenuFormat(String menu) {
        if (!menu.matches(ORDER_MENU_PATTERN)) {
            throw new IllegalArgumentException(ORDER_MENU_ERROR.getText());
        }
    }

    private static void orderMenuName(String menu) {
        List<String> menuAndQuantity = Arrays.asList(menu.split("-"));
        String menuName = menuAndQuantity.get(0);
        String menuQuantity = menuAndQuantity.get(1);

        if (isNotMenu(menuName)) {
            throw new IllegalArgumentException(ORDER_MENU_ERROR.getText());
        }

        menuNames.add(menuName);
        menuQuantities.add(menuQuantity);
    }

    private static boolean isNotMenu(String menuName) {
        return Arrays.stream(Menu.values())
                .noneMatch(menu -> menu.getName().equals(menuName));
    }

    private static void orderMenuDuplicate() {
        Set<String> noDuplicateMenus = new HashSet<>(menuNames);

        if (noDuplicateMenus.size() != menuNames.size()) {
            nameAndQuantityListClear();
            throw new IllegalArgumentException(ORDER_MENU_ERROR.getText());
        }
    }

    // 에러가 발생 했을 때, menuNames List와 menuQuantities List를 초기화 해주기 위함.
    // 초기화 해주지 않으면, 이후 정상 값을 입력 했을 때도 그대로 남아 메뉴 중복 등의 문제가 발생함.
    private static void nameAndQuantityListClear() {
        menuNames.clear();
        menuQuantities.clear();
    }

    private static void orderCaution() {
        if (isOnlyBeverage()) {
            nameAndQuantityListClear();
            throw new IllegalArgumentException(ORDER_ONLY_BEVERAGE_ERROR.getText());
        }
        if (isOverLimit()) {
            nameAndQuantityListClear();
            throw new IllegalArgumentException(OVER_QUANTITY_LIMIT_ERROR.getText());
        }
    }

    private static boolean isOnlyBeverage() {
        return menuNames.stream()
                .allMatch(menuName -> Arrays.stream(Menu.values())
                        .filter(menu -> menu.getName().equals(menuName))
                        .allMatch(menu -> menu.isBeverage(menu.getType()))
                );
    }

    private static boolean isOverLimit() {
        int sum = 0;

        for (String quantity : menuQuantities) {
            sum += Integer.parseInt(quantity);

            if (sum > 20) {
                return true;
            }
        }

        return false;
    }
}
