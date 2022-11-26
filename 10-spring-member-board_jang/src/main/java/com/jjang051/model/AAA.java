package com.jjang051.model;

import java.util.List;

public class AAA implements ReplyBoardService {

	@Override
	public int insertBoard(ReplyBoardDto replyBoardDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReplyBoard(ReplyBoardDto replyBoardDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(ReplyBoardDto replyBoardDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReLevel(ReplyBoardDto replyBoardDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxRegroup() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyBoardDto> getAllList(int start, int end, String search_select, String search_word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReplyBoardDto> getSearchAllList(String search_select, String search_word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReplyBoardDto getSelectOne(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReplyBoardDto getPrevSelect(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReplyBoardDto getNextSelect(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateHit(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(ReplyBoardDto replyBoardDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotal(String search_select, String search_word) {
		// TODO Auto-generated method stub
		return 0;
	}

}
