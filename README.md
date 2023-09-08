# spring-security-authorization-manager

![spring-security-authorization-manager](https://socialify.git.ci/SWM-KAWAI-MANS/spring-security-authorization-manager/image?description=1&descriptionEditable=&font=Raleway&language=1&name=1&owner=1&pattern=Floating%20Cogs&stargazers=1&theme=Light)

### Description
Spring security 인가 설정을 편리하게 지원하는 라이브러리입니다.

### Features
- JWT를 통한 인가 필터
- RequestMatcher에 허용되는 Role 구성에 따른 인가권한 부여
- Filter를 예외하는 exclusions 설정

### Getting Started
To get started with spring-security-authorization-manager, follow these steps:

1. Add the jitpack.io repository to your project's build.gradle file:
```gradle
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```

2. Add the spring-security-authorization-manager dependency to your app-level build.gradle file:
```
dependencies {
	      implementation 'com.github.SWM-KAWAI-MANS:spring-security-authorization-manager:1.1.0'
}
```

## 사용법
1. filter 처리를 안하는 엔드포인트를 설정합니다. application.yml 혹은 properties로 설정하면 됩니다. 만약 처리를 안하는 앤드포인트가 없으면 빈 값으로 두어도 상관 없습니다.
```
auth:
  filter:
    exclusions: hello, world
```

2. AuthorizationRegistry를 설정합니다. 기본값은 anyRequest().authenticated() 로 설정되어있습니다.
```java
public class CustomAuthorizationRegistry implements AuthorizationRegistry {
    @Override
    public AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
            match(AuthorizeHttpRequestsConfigurer<HttpSecurity>
                                    .AuthorizationManagerRequestMatcherRegistry r) {
        return r.anyRequest().authenticated();
    }
}
```
