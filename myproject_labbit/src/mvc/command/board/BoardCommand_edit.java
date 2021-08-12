package mvc.command.board;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.db.dao.BoardDao;
import mvc.db.dto.BoardDto;

//�Խñ� ������û�� - �Խñ� ��ȣ�� �Խñ� ���� ��������
public class BoardCommand_edit implements BoardCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(">>BoardCommand_edit");
			
			BoardDao dao = BoardDao.getInstance();
			BoardDto board = dao.boardSearchByBoardNum(
								Integer.parseInt(request.getParameter("boardNum"))
							);
			request.setAttribute("board", board);
			System.out.println(">>BoardCommand_edit end");
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
