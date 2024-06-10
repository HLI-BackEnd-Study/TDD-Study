import com.hanwha.generator.RandomBaseballNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.hanwha.constant.BaseballConstant.GAME_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseballGenerateTest {

    @Test
    @DisplayName("컴퓨터가 랜덤한 게임 수 자리만큼의 난수를 생성할 수 있다.")
    void generateTest() {
        RandomBaseballNumberGenerator randomBaseballNumberGenerator = new RandomBaseballNumberGenerator();
        assertThat(randomBaseballNumberGenerator.generate()).hasSize(GAME_COUNT);
    }

    @Test
    @DisplayName("생성된 난수에 중복된 숫자가 없는지 검증한다.")
    void generateUniqueNumbersTest() {
        RandomBaseballNumberGenerator randomBaseballNumberGenerator = new RandomBaseballNumberGenerator();
        List<Integer> generatedNumbers = randomBaseballNumberGenerator.generate();
        assertThat(generatedNumbers).doesNotHaveDuplicates();
    }
}
