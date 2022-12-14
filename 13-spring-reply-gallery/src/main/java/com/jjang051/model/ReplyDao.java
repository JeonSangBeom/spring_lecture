package com.jjang051.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplyDao {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public int insertReply(ReplyDto replyDto) {// 통쨰로 넣기
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result = sqlSession.insert("insertReply",replyDto);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	public List<ReplyDto> getAllReply(int boardId) { // List를 리턴
		// TODO Auto-generated method stub
		List<ReplyDto> replyList = null;
		SqlSession sqlSession = sqlSessionFactory.openSession(); // 세션 열기
		replyList = sqlSession.selectList("getAllReply",boardId); //  getAllReply - id / boardId 너기기
		sqlSession.close();
		return replyList;
	}

	public int deleteReply(int no) {
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result = sqlSession.delete("deleteReply",no);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}