package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void getList(Model model) throws Exception {
		
		List<BoardVO> list = null;
		list = service.list();
		
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value = "/write", method =RequestMethod.GET)
	public void getWrite() throws Exception {
		
	}
	
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String postWrite(BoardVO vo) throws Exception {
		service.write(vo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("board_id") int board_id, Model model) throws Exception {
		BoardVO vo = service.view(board_id);
		model.addAttribute("view", vo);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("board_id") int board_id, Model model) throws Exception {
		
		BoardVO vo = service.view(board_id);
		model.addAttribute("view", vo);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {
		
		service.modify(vo);
		
		return "redirect:/board/view?board_id=" + vo.getBoard_id(); 
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("board_id") int board_id) throws Exception {
		service.delete(board_id);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {
		
		
		int count = service.count();
		
		int postNumber = 10;
		
		int pageNumber = (int)Math.ceil((double)count/postNumber);
		
		int displayPost = (num - 1) * postNumber;
		
		List<BoardVO> list = null; 
		list = service.listPage(displayPost, postNumber);
		model.addAttribute("list", list);
		model.addAttribute("pageNumber", pageNumber);
	}
	
}
