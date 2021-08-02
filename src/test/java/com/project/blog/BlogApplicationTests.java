package com.project.blog;

import com.project.blog.domain.comment.CommentEntity;
import com.project.blog.domain.comment.CommentRepository;
import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.post.PostRepository;
import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;

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
		// user 생성
		UserEntity user = new UserEntity("test", "pass");
		userRepository.save(user);
	}

	@Test
	public void postTest() throws Exception {
		// post 생성
		PostEntity post = new PostEntity("test","test","testPost");
		postRepository.save(post);
	}

	@Test
	public void commentTest() throws Exception{
		// comment 생성
		CommentEntity comment = new CommentEntity("test",1,"testComment");
		commentRepository.save(comment);
	}
}
