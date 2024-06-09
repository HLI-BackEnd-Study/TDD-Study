package domain;

import com.hanwha.domain.BaseBall;
import com.hanwha.domain.BaseBallNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseBallTest {

    @Test
    @DisplayName("postion과 number를 가진 baseball을 생성한다.")
    void createBaseball() {
        BaseBallNumber baseBallNumber = BaseBallNumber.of(1);
        BaseBall baseBall = new BaseBall(baseBallNumber, 1);
        assertThat(baseBall.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:1", "9:9"}, delimiter = ':')
    @DisplayName("Number 는 1이상 9이하의 숫자로 이루어져 있다.")
    void createBaseballNumber(int input, int expected) {
        assertThat(BaseBallNumber.of(input).getNumber()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "10"})
    @DisplayName("Number 가 경계값을 넘었을 경우 예외를 반환한다.")
    void baseBallCheckValid(int input) {
        assertThatThrownBy(() -> BaseBallNumber.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}