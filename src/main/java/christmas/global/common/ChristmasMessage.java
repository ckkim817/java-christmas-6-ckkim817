package christmas.global.common;

public enum ChristmasMessage {

    INPUT_VISIT_DATE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n"),
    VISIT_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요.\n");

    private final String text;

    ChristmasMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
