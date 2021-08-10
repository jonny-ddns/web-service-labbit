package mvc.command.board;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.db.dao.BoardDao;
import mvc.db.dto.BoardDto;

public class BoardCommand_read implements BoardCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(">>BoardCommand_read()");
			
			int artiNum = Integer.parseInt(request.getParameter("artiNum"));			
			BoardDao dao = BoardDao.getInstance();
			BoardDto board = dao.boardSearchByArtiNum(artiNum);
			
			request.setAttribute("artiNum", artiNum);
			request.setAttribute("board", board);
			System.out.println("BoardCommand_read() end");
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
