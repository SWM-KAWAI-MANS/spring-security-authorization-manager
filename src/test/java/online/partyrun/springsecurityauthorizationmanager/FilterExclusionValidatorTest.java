package online.partyrun.springsecurityauthorizationmanager;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FilterExclusionValidator 에서")
class FilterExclusionValidatorTest {
    final String requestPath = "/hello";

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 주어진_값들이_슬래시가_붙어있을_때 {
        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 환경변수_값이_비어있으면 {
            final FilterExclusionValidator exclusionValidator =
                    new FilterExclusionValidator(List.of());

            @Test
            @DisplayName("아무 값을 넣어도 false를 반환한다.")
            void returnFalse() {
                assertThat(exclusionValidator.validate(requestPath)).isFalse();
            }
        }

        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 환경변수_값이_하나있으면 {
            @Test
            @DisplayName("path가 포함되면 true를 반환한다.")
            void returnTrue() {
                final FilterExclusionValidator exclusionValidator =
                        new FilterExclusionValidator(List.of(requestPath));
                assertThat(exclusionValidator.validate(requestPath)).isTrue();
            }

            @Test
            @DisplayName("path가 포함되지않으면 false를 반환한다.")
            void returnFalse() {
                final FilterExclusionValidator exclusionValidator =
                        new FilterExclusionValidator(List.of("/ttttt"));
                assertThat(exclusionValidator.validate(requestPath)).isFalse();
            }
        }

        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 환경변수_값이_여러개일_때 {
            @Test
            @DisplayName("하나의 값만 들어가도 true를 반환한다.")
            void returnTrue() {
                final FilterExclusionValidator exclusionValidator =
                        new FilterExclusionValidator(List.of("/asdf", "/qwerweq",requestPath));

                assertThat(exclusionValidator.validate(requestPath)).isTrue();
            }

            @Test
            @DisplayName("하나의 값도 없으면 false를 반환한다.")
            void returnFalse() {
                final FilterExclusionValidator exclusionValidator =
                        new FilterExclusionValidator(List.of("/asdf", "/qwerweq", "/ttttt"));
                assertThat(exclusionValidator.validate(requestPath)).isFalse();
            }
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 주어진_값들이_슬래시가_붙지않을_때 {
        @Test
        @DisplayName("슬래시를 추가해서 변환을 진행한 후에 검증을 진행한다")
        void returnTrue() {
            final FilterExclusionValidator exclusionValidator =
                    new FilterExclusionValidator(List.of("hello"));
            assertThat(exclusionValidator.validate(requestPath)).isTrue();
        }
    }
}
