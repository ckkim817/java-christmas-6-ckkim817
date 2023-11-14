package christmas.global;

import static christmas.global.RegularExpression.VISIT_DATE_PATTERN;
import static christmas.global.common.ChristmasMessage.VISIT_DATE_ERROR;

public class Validator {

    public static void visitDateFormat(String input) {
        if (!input.matches(VISIT_DATE_PATTERN)) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR.getText());
        }
    }
}
