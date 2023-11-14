package christmas;

import christmas.domain.ChristmasManager;
import christmas.ui.InputView;
import christmas.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(outputView);
        ChristmasManager christmasManager = new ChristmasManager(inputView, outputView);
        christmasManager.run();
    }
}
