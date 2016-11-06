package svc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDAO;
import vo.BoardBean;

@Service
public class BoardListService {

	@Autowired
	private BoardDAO boardDAO;
	
	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		listCount = boardDAO.selectListCount();
		return listCount;
		
	}

	public ArrayList<BoardBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<BoardBean> articleList = null;
		articleList = boardDAO.selectArticleList(page,limit);
		return articleList;
		
	}

}
