package christmas.global.common;

public enum ChristmasMessage {

    INPUT_VISIT_DATE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n"),
    VISIT_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요.\n"),
    INPUT_ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n"),
    ORDER_MENU_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요.\n"),
    ORDER_ONLY_BEVERAGE_ERROR("음료만 주문할 수는 없습니다. 다시 입력해 주세요.\n"),
    OVER_QUANTITY_LIMIT_ERROR("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.\n");

    private final String text;

    ChristmasMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
