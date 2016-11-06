package controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import svc.BoardDeleteProService;

@Controller
public class BoardDeleteProController {

	@Autowired
	private BoardDeleteProService boardDeleteProService;
	
	@RequestMapping("/boardDeletePro.bo")
	public String execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 
		
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		String nowPage = request.getParameter("page");
		boolean isArticleWriter =boardDeleteProService.isArticleWriter(board_num, request.getParameter("BOARD_PASS"));
		String forwardURL = null;
		
		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(board_num);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forwardURL = "redirect:boardList.bo?page=" + nowPage;
			}
			
		}
		
		return forwardURL;
		
	}

}