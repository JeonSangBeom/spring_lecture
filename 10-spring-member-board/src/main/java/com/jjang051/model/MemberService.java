package com.jjang051.model;

import java.util.List;
import java.util.Map;


//구현해야 하는 것을 여기다가 쓰기(유연함 쓰임을 위해)
//인터페이스는 메서드 정의만 해준다 -> controller에서 한번에 받을 수 있다
public interface MemberService {
	public List<MemberDto> getAllMemberList(int start, int end, String search_select, String search_word);
	//public Map<String,Object> getAllMemberListJson(int start, int end, String search_select, String search_word);
	public MemberDto getSelectOne(String id);
	public MemberDto getLoggedMember(MemberDto pMemberDto);
	public int deleteMember(MemberDto memberDto);
	public int insertMember(MemberDto memberDto);
	public int updateMember(MemberDto memberDto);
	public int idCheck(String id);
	public int getTotal(String search_select, String search_word);
	// dao에 있는 것 복붙
}