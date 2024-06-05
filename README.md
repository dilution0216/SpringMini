# SpringMini
커밋 컨벤션

> Commit Type
> 
> 
> 타입은 태그와 제목으로 구성되고, 태그는 영어로 쓰되 첫 문자는 대문자로 한다.
> 

**`태그 : 제목`의 형태이다.

- `feat` : 새로운 기능 추가
- `fix` : 버그 수정
- `docs` : 문서 수정
- `style` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- `refactor` : 코드 리펙토링
- `chore` : 빌드 업무 수정, 패키지 매니저 수정


구현 과정

1. 프로젝트 구조 및 설정
Spring Boot 프로젝트를 생성하고 필요한 의존성을 추가
application.properties 파일을 설정하여 데이터베이스와의 연결을 구성

2. 모델 생성
Company, Job, User, Application 엔터티 클래스를 생성

3. 리포지토리 생성
Spring Data JPA를 사용하여 JobRepository, ApplicationRepository 등의 인터페이스 생성
데이터 접근을 위한 메서드를 정의

4. 서비스 생성
JobService 클래스를 만들고, CRUD 및 비즈니스 로직을 구현
리포지토리를 사용하여 데이터베이스와 상호작용

5. 컨트롤러 생성
JobController 클래스를 만들고, HTTP 요청을 처리하는 메서드를 정의
서비스 클래스의 메서드를 호출하여 비즈니스 로직을 실행하고 응답을 반환

6. 단위 테스트 작성
JUnit과 Mockito를 사용하여 서비스와 컨트롤러의 단위 테스트를 작성
모의 객체와 예상되는 결과를 설정하여 테스트 케이스를 실행 후 검증
