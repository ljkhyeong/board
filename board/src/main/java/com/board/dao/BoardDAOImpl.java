package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.board.mappers.board";
	
	
	@Override
	public List<BoardVO> list() throws Exception {
		
		return sql.selectList(namespace + ".list");
	}


	@Override
	public void write(BoardVO vo) throws Exception {
		
		sql.insert(namespace + ".write", vo); 
		
	}


	@Override
	public BoardVO view(int board_id) throws Exception {
		return sql.selectOne(namespace + ".view", board_id);
	}


	@Override
	public void modify(BoardVO vo) throws Exception {
		sql.update(namespace + ".modify", vo);
		
	}


	@Override
	public void delete(int board_id) throws Exception {
		sql.delete(namespace + ".delete", board_id);
		
	}


	@Override
	public int count() throws Exception {
		return sql.selectOne(namespace + ".count");
	}


	@Override
	public List<BoardVO> listPage(int displayPost, int postNumber) throws Exception {
		
		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNumber", postNumber);
		
		return sql.selectList(namespace + ".listPage", data);
	}
		
	

}
