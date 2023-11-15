package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.global.Validator;
import christmas.global.common.ChristmasMessage;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String SEPARATOR = ",";
    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public int readVisitDate() {
        outputView.print(ChristmasMessage.INPUT_VISIT_DATE);
        String input = Console.readLine().trim();
        Validator.visitDate(input);

        return Integer.parseInt(input);
    }

    public List<String> readOrderMenu() {
        outputView.print(ChristmasMessage.INPUT_ORDER_MENU);
        String input = Console.readLine().trim();
        List<String> menus = Arrays.asList(input.split(SEPARATOR));
        Validator.orderMenu(menus);

        return menus;
    }
}
