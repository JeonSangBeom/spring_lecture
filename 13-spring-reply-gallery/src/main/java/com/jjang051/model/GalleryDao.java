package com.jjang051.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GalleryDao {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	//insert,upate,delete
	public int insert(GalleryDto galleryDto) {
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result = sqlSession.insert("insertGallery",galleryDto);//id="insertGallery" parameterType="GalleryDto"
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	//controller에서 만들때 필요 - mapper동일
	public List<GalleryDto> getAllList() { 
		List<GalleryDto>  galleryList = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		galleryList = sqlSession.selectList("getAllList");
		sqlSession.close();
		return galleryList;
	}
	
	public int deleteGallery(int no) {
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result = sqlSession.delete("deleteGallery", no);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}

