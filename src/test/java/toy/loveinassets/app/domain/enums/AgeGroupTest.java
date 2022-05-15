package toy.loveinassets.app.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static toy.loveinassets.app.domain.enums.AgeGroup.*;

class AgeGroupTest {

    @Test
    @DisplayName("연령대 계산 테스트")
    void ageGroupCalc() {

        AgeGroup twenties = getAgeGroup(1998);
        AgeGroup thirties = getAgeGroup(1990);
        AgeGroup forties = getAgeGroup(1980);
        AgeGroup fifties = getAgeGroup(1970);
        AgeGroup etc = getAgeGroup(2010);

        assertThat(twenties).isEqualTo(TWENTIES);
        assertThat(thirties).isEqualTo(THIRTIES);
        assertThat(forties).isEqualTo(FORTIES);
        assertThat(fifties).isEqualTo(FIFTIES);
        assertThat(etc).isEqualTo(ETC);
    }
}