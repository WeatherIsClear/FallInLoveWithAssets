package toy.loveinassets.app.domain.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static toy.loveinassets.app.domain.enums.AgeGroup.*;

class AgeGroupTest {

    @Test
    void ageGroupCalc() {

        AgeGroup twenties = getAgeGroup(1998);
        AgeGroup thirties = getAgeGroup(1993);
        AgeGroup forties = getAgeGroup(1981);
        AgeGroup fifties = getAgeGroup(1968);
        AgeGroup etc = getAgeGroup(2010);

        assertThat(twenties).isEqualTo(TWENTIES);
        assertThat(thirties).isEqualTo(THIRTIES);
        assertThat(forties).isEqualTo(FORTIES);
        assertThat(fifties).isEqualTo(FIFTIES);
        assertThat(etc).isEqualTo(ETC);
    }
}