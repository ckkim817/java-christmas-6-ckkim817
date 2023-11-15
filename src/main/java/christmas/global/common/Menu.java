package christmas.global.common;

import static christmas.global.common.MenuType.APPETIZER;
import static christmas.global.common.MenuType.BEVERAGE;
import static christmas.global.common.MenuType.DESSERT;
import static christmas.global.common.MenuType.MAIN;

public enum Menu {

    // APPETIZER
    WHITE_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6000),
    TAPAS(APPETIZER, "타파스", 5500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8000),

    // MAIN
    T_BONE_STEAK(MAIN, "티본스테이크", 55000),
    BBQ_RIB(MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25000),

    // DESSERT
    CHOCO_CAKE(DESSERT, "초코케이크", 15000),
    ICE_CREAM(DESSERT, "아이스크림", 5000),

    // BEVERAGE
    ZERO_COLA(BEVERAGE, "제로콜라", 3000),
    RED_WINE(BEVERAGE, "레드와인", 60000),
    CHAMPAGNE(BEVERAGE, "샴페인", 25000);

    private final MenuType type;
    private final String name;
    private final int price;

    Menu(MenuType type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public boolean isBeverage(MenuType type) {
        return type.equals(BEVERAGE);
    }

    public MenuType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
