package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class IOTest extends NsTest {

    @Test
    void 메뉴판에_없는_메뉴일_경우_예외_문구_처리() {
        assertSimpleTest(() -> {
            runException("1", "햄버거-1, 제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴의_개수가_1이상의_숫자가_아닐_경우_예외_문구_처리() {
        assertSimpleTest(() -> {
            runException("1", "해산물파스타-0,레드와인-1,초코케이크-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 중복_메뉴를_입력할_경우_예외_문구_처리() {
        assertSimpleTest(() -> {
            runException("1", "해산물파스타-2,레드와인-1,해산물파스타-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 음료만_주문할_경우_예외_문구_처리() {
        assertSimpleTest(() -> {
            runException("1", "레드와인-1");
            assertThat(output()).contains("[ERROR] 음료만 주문할 수는 없습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 스무_개가_넘는_메뉴를_주문할_경우_예외_문구_처리() {
        assertSimpleTest(() -> {
            runException("1", "해산물파스타-2,레드와인-1,초코케이크-18");
            assertThat(output()).contains("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
