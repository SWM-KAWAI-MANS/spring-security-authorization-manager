package online.partyrun.springsecurityauthorizationmanager;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("FilterExclusionValidator 에서")
class FilterExclusionValidatorTest {
    final String requestPath = "/hello";

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 환경변수가_정상적으로_설정된_경우 {
        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 주어진_값들이_슬래시가_붙어있을_때 {
            @Nested
            @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
            class 환경변수_값이_비어있을_때 {
                final FilterExclusionValidator exclusionValidator = new FilterExclusionValidator(new String[0]);

                @Test
                @DisplayName("아무 값을 넣어도 false를 반환한다.")
                void doFalse() {
                    assertThat(exclusionValidator.validate(requestPath)).isFalse();
                }
            }

            @Nested
            @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
            class 환경변수_값이_하나일_때 {
                @Test
                @DisplayName("path가 포함되면 true를 반환한다.")
                void doTrue() {
                    final FilterExclusionValidator exclusionValidator = new FilterExclusionValidator(new String[]{"/hello"});
                    assertThat(exclusionValidator.validate(requestPath)).isTrue();
                }

                @Test
                @DisplayName("path가 포함되지않으면 false를 반환한다.")
                void doFalse() {
                    final FilterExclusionValidator exclusionValidator = new FilterExclusionValidator(new String[]{"/ttttt"});
                    assertThat(exclusionValidator.validate(requestPath)).isFalse();
                }
            }

            @Nested
            @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
            class 환경변수_값이_여러개일_때 {
                @Test
                @DisplayName("하나의 값만 들어가도 true를 반환한다.")
                void doTrue() {
                    final FilterExclusionValidator exclusionValidator = new FilterExclusionValidator(new String[]{"/asdf", "/qwerweq", "/hello"});

                    assertThat(exclusionValidator.validate(requestPath)).isTrue();
                }

                @Test
                @DisplayName("하나의 값도 없으면 false를 반환한다.")
                void doFalse() {
                    final FilterExclusionValidator exclusionValidator = new FilterExclusionValidator(new String[]{"/asdf", "/qwerweq", "/ttttt"});
                    assertThat(exclusionValidator.validate(requestPath)).isFalse();
                }
            }
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 주어진_값들이_슬래시가_붙지않을_때 {
        final FilterExclusionValidator exclusionValidator = new FilterExclusionValidator(new String[]{"hello"});

        @Test
        @DisplayName("슬래시를 추가해서 변환을 진행한 후에 검증을 진행한다")
        void doTrue() {
            final FilterExclusionValidator exclusionValidator = new FilterExclusionValidator(new String[]{"hello"});
            assertThat(exclusionValidator.validate(requestPath)).isTrue();
        }
    }


}