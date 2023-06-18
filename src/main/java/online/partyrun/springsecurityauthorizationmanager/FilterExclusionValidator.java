package online.partyrun.springsecurityauthorizationmanager;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterExclusionValidator {
    String[] exclusions;

    public FilterExclusionValidator(String[] exclusions) {
        this.exclusions = correctPath(exclusions);
    }

    private String[] correctPath(String[] exclusions) {
        return Arrays.stream(exclusions)
                .map(
                        s -> {
                            if (s.startsWith("/")) {
                                return s;
                            }
                            return "/" + s;
                        })
                .toArray(String[]::new);
    }

    public boolean validate(String path) {
        return Arrays.asList(exclusions).contains(path);
    }
}
