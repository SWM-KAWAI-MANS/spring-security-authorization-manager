package online.partyrun.springsecurityauthorizationmanager;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterExclusionValidator {
    List<String> exclusions;

    public FilterExclusionValidator(List<String> exclusions) {
        this.exclusions = correctPath(exclusions);
    }

    private List<String> correctPath(List<String> exclusions) {
        return exclusions.stream()
                .map(
                        s -> {
                            if (s.startsWith("/")) {
                                return s;
                            }
                            return "/" + s;
                        })
                .toList();
    }

    public boolean validate(String path) {
        return exclusions.contains(path);
    }
}
