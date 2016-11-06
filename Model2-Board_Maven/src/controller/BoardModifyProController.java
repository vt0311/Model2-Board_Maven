package controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import svc.BoardModifyProService;
import vo.BoardBean;

@Controller
public class BoardModifyProController{
	
	@Autowired
	private BoardModifyProService boardModifyProService;

	@RequestMapping("/boardModifyPro.bo")
	public String execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		boolean isModifySuccess = false;
		int board_num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		BoardBean article=new BoardBean();
		boolean isRightUser=boardModifyProService.isArticleWriter(board_num, request.getParameter("BOARD_PASS"));
		String forwardURL = null;
		
		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			article.setBOARD_NUM(board_num);
			article.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			article.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT")); 
			isModifySuccess = boardModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forwardURL = "redirect:boardDetail.bo?board_num="+article.getBOARD_NUM();
			}

		}

		return forwardURL;
	}

}