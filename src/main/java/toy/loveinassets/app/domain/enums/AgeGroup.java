package toy.loveinassets.app.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Getter
@AllArgsConstructor
public enum AgeGroup {
    TWENTIES(2),
    THIRTIES(3),
    FORTIES(4),
    FIFTIES(5),
    ETC(-1);

    private int ageGroup;

    private static final Map<Integer, AgeGroup> map =
            Arrays.stream(values()).collect(toMap(e -> e.ageGroup, Function.identity()));

    public static AgeGroup getAgeGroup(int year) {
        int age = ageGroupCalculate(year);
        Integer key = map.keySet().stream().filter(e -> e.equals(age)).findFirst().orElseGet(() -> -1);
        return map.get(key);
    }

    private static int ageGroupCalculate(int year) {
        return ((LocalDate.now().getYear() - year) + 1) / 10;
    }
}
