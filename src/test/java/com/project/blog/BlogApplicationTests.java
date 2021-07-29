package com.project.blog;

import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserRepository userRepository;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/blog?serverTimezone=UTC&characterEncoding=UTF-8";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	@Test
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)){
			System.out.println(connection);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void userTest() throws Exception {
		// 팀1 저장
		UserEntity user = new UserEntity("test", "pass");
		userRepository.save(user);
	}
}
