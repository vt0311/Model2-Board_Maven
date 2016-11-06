package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDAO;

@Service
public class BoardDeleteProService {

	@Autowired
	private BoardDAO boardDAO;
	
	public boolean isArticleWriter(int board_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		int articleCountByPass = boardDAO.getArticleCountByPass(board_num, pass);
		
		if(articleCountByPass > 0){
			isArticleWriter = true;
		}
		
		return isArticleWriter;
		
	}

	public boolean removeArticle(int board_num) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		int deleteCount = boardDAO.deleteArticle(board_num);
		
		if(deleteCount > 0){
			isRemoveSuccess=true;
		}
		
		return isRemoveSuccess;
		
	}

}
