package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import svc.BoardDetailService;
import vo.BoardBean;

@Controller
public class BoardReplyFormController{
	
	@Autowired
	private BoardDetailService boardDetailService;
	
	@RequestMapping("/boardReplyForm.bo")
	 public ModelAndView execute(HttpServletRequest request) 
	 	throws Exception{
		 
	   		String nowPage = request.getParameter("page");
	   		int board_num=Integer.parseInt(request.getParameter("board_num"));
	   		BoardBean article=boardDetailService.getArticle(board_num);	
	   		
	   		ModelAndView mav = new ModelAndView();
	   		mav.addObject("article", article);
	   		mav.addObject("page", nowPage);
	   		mav.setViewName("/board/qna_board_reply.jsp");
	   		
	   		return mav;
	   		
	}
	 
}