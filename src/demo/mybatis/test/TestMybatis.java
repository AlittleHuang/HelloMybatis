package demo.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import demo.mybatis.dao.UserMapper;
import demo.mybatis.pojo.Student;
import demo.mybatis.pojo.User;

public class TestMybatis {
	
	SqlSession sqlSession;
	
	@Before
	public void openSqlSession() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@After
	public void closeSqlsession(){
		sqlSession.close();
	}
	
	public void commit(){
		sqlSession.commit();
	}
	
	@Test
	public void testSelecteOne() {
		User user = sqlSession.selectOne("test.findByID",1);
		System.out.println(user);
	}
	
	@Test
	public void testInsert(){
		User user = new User();
		user.setUsername("jack");
		user.setPassword("rose");
		sqlSession.insert("test.insert",user);
		commit();
		System.out.println(user);
	}
	
	@Test 
	public void testInsertStu(){
		Student student = new Student();
		student.setName("jack");
		sqlSession.insert("test.insertStu", student);
		commit();
		System.out.println(student.getId().length());
	}
	
	@Test
	public void testDeleteUserById(){
		sqlSession.delete("test.deleteUserById",18);
		commit();
	}
	
	@Test
	public void testUserMapper(){
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findByID(19);
		System.out.println(user);
	}
	
}
