package com.hanwha.domain;


import com.hanwha.generator.BaseballNumberGenerator;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ComputerBaseballsTest {

    @Test
    @DisplayName("컴퓨터가 생성된 난수를 바탕으로 Baseball 객체를 생성한다.")
    void fromGenerateTest() {
        Set<Integer> numbers = new HashSet<>(List.of(1, 2, 3));
        TestBaseballNumberGenerator baseballNumberGenerator = new TestBaseballNumberGenerator(numbers);

        ComputerBaseballs computerBaseballs = ComputerBaseballs.generateComputerBaseballs(baseballNumberGenerator);
        List<Baseball> baseballList = computerBaseballs.getBaseballs().getBaseballs();
        List<Baseball> expectedBaseballs = List.of(
                Baseball.create(1, 0), Baseball.create(2, 1), Baseball.create(3, 2)
        );
        Baseballs expected = Baseballs.from(expectedBaseballs);

        SoftAssertions.assertSoftly(softly ->{
            softly.assertThat(baseballList).hasSize(3);
            softly.assertThat(baseballList).extracting("position").containsExactly(0, 1, 2);
            softly.assertThat(computerBaseballs.getBaseballs().equals(expected)).isTrue();
        });
    }
}


class TestBaseballNumberGenerator implements BaseballNumberGenerator {
    private final Set<Integer> predefinedNumbers;

    public TestBaseballNumberGenerator(Set<Integer> predefinedNumbers) {
        this.predefinedNumbers = predefinedNumbers;
    }

    @Override
    public Set<Integer> generate() {
        return predefinedNumbers;
    }
}
