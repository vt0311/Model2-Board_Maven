package controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import svc.BoardReplyProService;
import vo.BoardBean;

@Controller
public class BoardReplyProController{
	
	 @Autowired
	 private BoardReplyProService boardReplyProService;
	 
	 @RequestMapping("/boardReplyPro.bo")
	 public String execute(BoardBean article ,HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 
		    article.setBOARD_FILE_NAME("testFile");
		    String nowPage = request.getParameter("page");
		 	boolean isReplySuccess = boardReplyProService.replyArticle(article);
            String forwardURL = null;		 	

	   		if(isReplySuccess){
	   			forwardURL = "redirect:boardList.bo?page=" + nowPage;
	   		}
	   		else{
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('답장실패')");
	   			out.println("history.back()");
	   			out.println("</script>");
	   		}
	   		
	   		return forwardURL;
	   		
	}  	
	 
}