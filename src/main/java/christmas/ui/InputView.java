package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.global.Validator;
import christmas.global.common.ChristmasMessage;

public class InputView {

    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public int readVisitDate() {
        outputView.print(ChristmasMessage.INPUT_VISIT_DATE);
        String input = Console.readLine().trim();
        Validator.visitDateFormat(input);

        return Integer.parseInt(input);
    }
}
