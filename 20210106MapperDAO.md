# DAO와 Mapper

### Controller.java - Service.java - Mapper.java - Mapper.xml
### Controller.java - Service.java - DAO.java - Mapper.xml
### 두 구조의 프로젝트에 대한 차이점<br><br>
# Mapper / DAO

1. DAO
>>> DB에 접근, 조작을 위한 객체. <br>
    DAO의 사용 이유는 효율적인 커넥션 관리와 보안성 때문이다. DAO는 저수준의 Logic과 고급 비즈니스 Logic을 분리하고 domain logic으로부터 DB관련 mechanism을 숨기기 위해 사용한다.

2. Mapper
>>> Mybatis 매핑xml에 기재된 SQL을 호출하기 위한 인터페이스

<br><br>

# 차이점
# Sqlsession
# Mapper 구동 방식 / 설정



