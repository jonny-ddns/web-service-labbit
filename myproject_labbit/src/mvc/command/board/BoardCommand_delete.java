package mvc.command.board;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.db.dao.BoardDao;

//게시글 삭제하기 - active 항목 n값으로 변경
public class BoardCommand_delete implements BoardCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(">>BoardCommand_delete()");			
			int boardNum = Integer.parseInt(request.getParameter("boardNum"));			
			BoardDao dao = BoardDao.getInstance();
			dao.boardDelete(boardNum);
			System.out.println("BoardCommand_delete end");
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}