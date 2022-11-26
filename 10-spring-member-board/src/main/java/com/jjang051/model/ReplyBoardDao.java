package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReplyBoardDao implements ReplyBoardService {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	
	@Override
	public int insertBoard(ReplyBoardDto replyBoardDto) {
		return 0;
	}

	@Override
	public int insertReplyBoard(ReplyBoardDto replyBoardDto) {
		return 0;
	}

	@Override
	public int updateBoard(ReplyBoardDto replyBoardDto) {
		return 0;
	}

	@Override
	public int updateReLevel(ReplyBoardDto replyBoardDto) {
		return 0;
	}

	@Override
	public int getMaxRegroup() {
		return 0;
	}

	@Override
	public List<ReplyBoardDto> getAllList(int start, int end, String search_select, String search_word) {
		HashMap<String,Object> pageMap = new HashMap<>();
		pageMap.put("start",start);
		pageMap.put("end",end);
		pageMap.put("searchSelect",search_select);
		pageMap.put("searchWord",search_word);
		// boardMapper에 설정된 것 받아오기
		
		List<ReplyBoardDto> replyBoardList = null;//ReplyBoardDto를 담는 replyBoardList를 받아서
		SqlSession sqlSession = sqlSessionFactory.openSession();//openSession을 한 다음 getallList를 담고 페이지 맵을 던져주면
		replyBoardList = sqlSession.selectList("getAllList",pageMap); // boardMapper에 있는 id getallList
		sqlSession.close();
		return replyBoardList;// replyBoardList을 받게 된다
	}

	@Override
	public List<ReplyBoardDto> getSearchAllList(String search_select, String search_word) {
		return null;
	}

	@Override
	public ReplyBoardDto getSelectOne(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ReplyBoardDto replyBoardDto = sqlSession.selectOne("getSelectOne",no);
		sqlSession.close();
		return replyBoardDto;
	}

	@Override
	public ReplyBoardDto getPrevSelect(int num) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ReplyBoardDto replyBoardDto = sqlSession.selectOne("getPrevSelect",num);// boardMapper에 getprevselect(id)불러오기
		sqlSession.close();
		return replyBoardDto;
	}

	@Override
	public ReplyBoardDto getNextSelect(int num) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ReplyBoardDto replyBoardDto = sqlSession.selectOne("getNextSelect",num);
		sqlSession.close();
		return replyBoardDto;
	}

	@Override
	public int updateHit(int no) {
		return 0;
	}

	@Override
	public int deleteBoard(ReplyBoardDto replyBoardDto) {
		return 0;
	}

	@Override
	public int getTotal(String search_select, String search_word) {
		HashMap<String,Object> searchMap = new HashMap<>();
		searchMap.put("searchSelect",search_select);
		searchMap.put("searchWord",search_word);
		//boardMapper에 파라미터로 있는 값이 해쉬맵이기 때문에 searchMap으로 사용 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int total = sqlSession.selectOne("getTotal",searchMap);//boardMapper에 있는 getTotal 가져오기(하나라 selectOne 사용)		
		sqlSession.close();
		return total;
	}
	
}