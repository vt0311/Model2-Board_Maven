package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDAO;
import vo.BoardBean;

@Service
public class BoardDetailService {

	@Autowired
	private BoardDAO boardDAO;
	
	public BoardBean getArticle(int board_num) throws Exception{
		// TODO Auto-generated method stub
		
		BoardBean article = null;
		int updateCount = boardDAO.updateReadCount(board_num);	
		if(updateCount > 0){
		article = boardDAO.selectArticle(board_num);
		}
		return article;
		
	}

}
