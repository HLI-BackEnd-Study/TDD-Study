import com.hanwha.generator.RandomBaseballNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BaseballGenerateTest {

    @Test
    @DisplayName("컴퓨터가 랜덤한 세자리 난수를 생성할 수 있다.")
    void generateTest() {
        RandomBaseballNumberGenerator randomBaseballNumberGenerator = new RandomBaseballNumberGenerator();
        Assertions.assertThat(randomBaseballNumberGenerator.generate()).hasSize(3);
    }
}
