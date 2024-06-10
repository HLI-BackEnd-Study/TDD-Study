import com.hanwha.generator.RandomBaseballNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.hanwha.constant.BaseballConstant.GAME_COUNT;

public class BaseballGenerateTest {

    @Test
    @DisplayName("컴퓨터가 랜덤한 게임 수 자리만큼의 난수를 생성할 수 있다.")
    void generateTest() {
        RandomBaseballNumberGenerator randomBaseballNumberGenerator = new RandomBaseballNumberGenerator();
        Assertions.assertThat(randomBaseballNumberGenerator.generate()).hasSize(GAME_COUNT);
    }
}
