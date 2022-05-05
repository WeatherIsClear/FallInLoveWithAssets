package toy.loveinassets;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class SeonJe {

    @Test
    void gitTest() {
        Feature feature = new Feature();
        String merge = feature.seonJePush() + feature.dongYeongPush();
        assertThat(merge).isEqualTo("선제 푸시 동영 푸시");
    }

    static class Feature {

        public String seonJePush() {
            return "선제 푸시 ";
        }

        public String dongYeongPush() {
            return "동영 푸시";
        }
    }
}
