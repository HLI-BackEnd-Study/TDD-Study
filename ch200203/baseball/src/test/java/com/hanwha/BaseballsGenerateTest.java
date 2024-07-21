package com.hanwha;

import com.hanwha.generator.RandomBaseballNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseballsGenerateTest {

    @Test
    @DisplayName("컴퓨터가 랜덤한 3 자리만큼의 난수를 생성할 수 있다.")
    void generateTest() {
        RandomBaseballNumberGenerator randomBaseballNumberGenerator = new RandomBaseballNumberGenerator();
        assertThat(randomBaseballNumberGenerator.generate()).hasSize(3);
    }

    @Test
    @DisplayName("생성된 난수에 중복된 숫자가 없는지 검증한다.")
    @Deprecated(since = "Set 으로 변경 후 제거 예정")
    void generateUniqueNumbersTest() {
        RandomBaseballNumberGenerator randomBaseballNumberGenerator = new RandomBaseballNumberGenerator();
        Set<Integer> generatedNumbers = randomBaseballNumberGenerator.generate();
        assertThat(generatedNumbers).doesNotHaveDuplicates();
    }
}
