package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import svc.BoardDetailService;
import vo.BoardBean;

@Controller
 public class BoardDetailController{
	
	@Autowired
	private BoardDetailService boardDetailService;
	 
	 @RequestMapping("/boardDetail.bo")
	 public ModelAndView execute(HttpServletRequest request) throws Exception{ 
	   	
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		BoardBean article = boardDetailService.getArticle(board_num);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", page);
		mav.addObject("article", article);
		mav.setViewName("/board/qna_board_view.jsp");
		
   		return mav;

	 }
	 
}