package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardDeleteFormController {

		@RequestMapping("/boardDeleteForm.bo")
		public ModelAndView execute(HttpServletRequest request) throws Exception{
			 
			int board_num=Integer.parseInt(request.getParameter("board_num"));
		   	String nowPage = request.getParameter("page");
		   	
		   	ModelAndView mav = new ModelAndView();
		   	mav.addObject("page", nowPage);
		   	mav.addObject("board_num",board_num);
		   	mav.setViewName("/board/qna_board_delete.jsp");
	   		return mav;
	   		
	 }
}
