package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vo.BoardBean;
import dao.BoardDAO;

@Service
public class BoardModifyProService {

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

	public boolean modifyArticle(BoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		int updateCount = boardDAO.updateArticle(article);
		
		if(updateCount > 0){
			isModifySuccess=true;
		}

		return isModifySuccess;
		
	}

}
