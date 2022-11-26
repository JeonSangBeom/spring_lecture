package com.jjang051.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//2_db와 왔다갔다 연결된 곳

@Component
public class TodoDao {
	
	// mybatis불러오기
	@Autowired
	public SqlSessionFactory sqlSessionFactory;
	
	public int insertTodo(TodoDto todoDto) { // 집어 넣는 것
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result = sqlSession.insert("insertTodo",todoDto); // insertTodo 과 mapper에 있는 insert id와 같게 해줘야 한다
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	//insert , update, delete  리컨 타입이 정수
	//select return type이 다양하게 존재한다. 그중 많이 쓰이는게 dto(로그인)
	//List 를 리턴할때는 주로 여러줄 보여줄때...
	
	public List<TodoDto> getAllList(String pickedDate) { // TodoDto를 리턴하는 List를 만들어 주기
		List<TodoDto> todoList = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		todoList = sqlSession.selectList("getAllList",pickedDate);
		sqlSession.close();
		return todoList;
	}
	
	public int deleteTodo(int no) {//2) deleteTodo메서드 생성 해줄 것
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result =  sqlSession.delete("deleteTodo",no);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public int updateTodo(TodoDto todoDto) {
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result =  sqlSession.delete("updateTodo",todoDto);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}


