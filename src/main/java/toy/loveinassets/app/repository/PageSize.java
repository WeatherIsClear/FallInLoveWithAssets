package toy.loveinassets.app.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageSize {
    TEN(10);

    private int size;
}
