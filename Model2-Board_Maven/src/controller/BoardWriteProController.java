package controller;

import java.io.File;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import svc.BoardWriteProService;
import vo.BoardBean;

@Controller
public class BoardWriteProController{

	@Autowired
	private BoardWriteProService boardWriteProService;
	
	@RequestMapping("/boardWritePro.bo")
	public String execute(BoardBean article ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println(article.getBOARD_NAME());
        System.out.println("boardWritePro.botest");
		String realFolder="";
		String saveFolder="/boardUpload";
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder); 
		
		MultipartFile multiFile = article.getBOARD_FILE();
		String filename = multiFile.getOriginalFilename();
		File uploadFile = new File(realFolder + "/" + filename);
		try{
			multiFile.transferTo(uploadFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		article.setBOARD_FILE_NAME(filename);
		boolean isWriteSuccess = boardWriteProService.registArticle(article);
        String forwardURL = null;
		
		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forwardURL = "redirect:/boardList.bo";
		}

		return forwardURL;
		
	}  	
	
}