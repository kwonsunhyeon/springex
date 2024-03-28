package hello.springex.sample;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2  // Log4j2 어노테이션 : Log4j2 로깅을 위한 어노테이션으로, 클래스 내에서 로그를 기록할 때 사용됨
@ExtendWith(SpringExtension.class) // SpringExtension 어노테이션: JUnit 5와 함께 Spring 테스트를 실행할 수 있도록 확장을 지원하는 어노테이션
@ContextConfiguration(locations = "file:src/main/resources/templates/root-context.xml") // ContextConfiguration 어노테이션: 스프링 테스트 컨텍스트가 로드될 때 어떤 설정 파일을 사용할 것인지 지정하는 어노테이션
public class SampleTests {

    @Autowired // SampleService 객체를 주입받는 필드
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;

    @Test // SampleService 를 테스트하는 메서드
    public void testService1() {
        log.info(sampleService); // 로그 출력
        Assertions.assertNotNull(sampleService); // SampleService 가 null 이 아닌지 검증
    }

    @Test
    public void testConnection() throws Exception {

        Connection connection =  dataSource.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);

        connection.close();
    }
}
