package dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.BoardBean;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionFactory factory;

	//���� ���� ���ϱ�.
	public int selectListCount() {

		SqlSession session = factory.openSession();
		int listCount = session.selectOne("board.selectListCount");
		session.close();
		return listCount;

	}

	//�� ��� ����.	
	public ArrayList<BoardBean> selectArticleList(int page,int limit){

		SqlSession session = factory.openSession();
		int startrow=(page-1)*10; //�б� ������ row ��ȣ..		   
		List<BoardBean> articleList = session.selectList("board.selectArticleList", startrow);
		session.close();
		return (ArrayList<BoardBean>)articleList;

	}

	//�� ���� ����.
	public BoardBean selectArticle(int board_num) throws Exception{

		SqlSession session = factory.openSession();
		BoardBean boardBean = session.selectOne("board.selectArticle",board_num);
		session.close();
		return boardBean;

	}

	//�� ���.
	public int insertArticle(BoardBean article){

		SqlSession session = factory.openSession();

		int maxNum = session.selectOne("board.selectMaxNum");
		int  num = maxNum + 1;
		article.setBOARD_NUM(num);
		article.setBOARD_RE_REF(num);
		int insertCount = session.insert("board.insertArticle", article);
		session.close();
		return insertCount;

	}

	//�� �亯.
	public int insertReplyArticle(BoardBean article){

		SqlSession session = factory.openSession();
		int maxNum = session.selectOne("board.selectMaxNum");
		int  num = maxNum + 1;
		int insertCount=0;
		int re_lev=article.getBOARD_RE_LEV();
		int re_seq=article.getBOARD_RE_SEQ();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("BOARD_RE_SEQ",article.getBOARD_RE_SEQ());
		map.put("BOARD_RE_REF",article.getBOARD_RE_REF());

		session.update("board.updateBoardSeq", map);
		re_seq = re_seq + 1;
		re_lev = re_lev+1;
		article.setBOARD_RE_SEQ(re_seq);
		article.setBOARD_RE_LEV(re_lev);
		article.setBOARD_NUM(num);
		insertCount = session.insert("board.insertReplyArticle", article);
		session.close();
		return insertCount;

	}

	//�� ����.
	public int updateArticle(BoardBean article){

		SqlSession session = factory.openSession();
		int updateCount = session.update("board.updateArticle", article);
		session.close();
		return updateCount;

	}

	//�� ����.
	public int deleteArticle(int board_num){

		SqlSession session = factory.openSession();
		int deleteCount = session.delete("board.deleteArticle", board_num);
		session.close();
		return deleteCount;

	}

	//��ȸ�� ������Ʈ.
	public int updateReadCount(int board_num){

		SqlSession session = factory.openSession();
		int updateReadCount = session.update("board.updateReadCount", board_num);
		session.close();
		return updateReadCount;

	}

	//��й�ȣ Ȯ��
	public int getArticleCountByPass(int board_num,String pass){

		SqlSession session = factory.openSession();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("board_num", board_num + "");
		map.put("pass", pass);
		int articleCountByPass = session.selectOne("board.getArticleCountByPass", map);
		return articleCountByPass;

	}

}
