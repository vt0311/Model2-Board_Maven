package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.BoardDAO;
import vo.BoardBean;

@Service
public class BoardWriteProService {

	@Autowired
	private BoardDAO boardDAO;
	
	public boolean registArticle(BoardBean boardBean) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isWriteSuccess = false;
		int insertCount = boardDAO.insertArticle(boardBean);
		
		if(insertCount > 0){
			isWriteSuccess = true;
		}

		return isWriteSuccess;
		
	}

}
