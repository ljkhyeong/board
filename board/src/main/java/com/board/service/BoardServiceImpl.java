package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public void write(BoardVO vo) throws Exception {
		
		dao.write(vo);
	}

	@Override
	public BoardVO view(int board_id) throws Exception {
		
		return dao.view(board_id);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		
		dao.modify(vo);
		
	}

	@Override
	public void delete(int board_id) throws Exception {
		
		dao.delete(board_id);
		
	}

	@Override
	public int count() throws Exception {
		return dao.count();
	}

	@Override
	public List<BoardVO> listPage(int displayPost, int postNumber) throws Exception {
		return dao.listPage(displayPost, postNumber);
	}
	


}
