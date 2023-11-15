package christmas.global.common;

public enum ChristmasMessage {

    INPUT_VISIT_DATE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n"),
    VISIT_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요.\n"),
    INPUT_ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n"),
    ORDER_MENU_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요.\n"),
    ORDER_ONLY_BEVERAGE_ERROR("음료만 주문할 수는 없습니다. 다시 입력해 주세요.\n"),
    OVER_QUANTITY_LIMIT_ERROR("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.\n"),
    EVENT_BENEFIT_PREVIEW("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    ORDER_MENU_AND_QUANTITY("\n<주문 메뉴>\n"),
    ORDER_AMOUNT_BEFORE_DISCOUNT("\n<할인 전 총주문 금액>\n"),
    GIFT_MENU("\n<증정 메뉴>\n"),
    CHAMPAGNE_GIFT("샴페인 1개\n"),
    NO_GIFT("없음"),
    BENEFIT_DETAIL("\n<혜택 내역>\n"),
    TOTAL_BENEFIT_AMOUNT("\n<총혜택 금액>\n"),
    PAYMENT_AMOUNT_AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>\n"),
    DECEMBER_EVENT_BADGE("\n<12월 이벤트 배지>\n");

    private final String text;

    ChristmasMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
