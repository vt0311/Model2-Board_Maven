package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDAO;
import vo.BoardBean;

@Service
public class BoardReplyProService {

	@Autowired
	private BoardDAO boardDAO;
	
	public boolean replyArticle(BoardBean article) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		insertCount = boardDAO.insertReplyArticle(article);
		
		if(insertCount > 0){
			isReplySuccess = true;
		}
		
		return isReplySuccess;
		
	}

}
