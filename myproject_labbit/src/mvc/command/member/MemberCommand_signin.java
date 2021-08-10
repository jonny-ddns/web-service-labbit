package mvc.command.member;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mvc.db.dao.MemberDao;
import mvc.db.dto.MemberDto;

public class MemberCommand_signin implements MemberCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(">>MemberCommand_signin()");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			MemberDao mdao = MemberDao.getInstance();
			boolean isVerified = mdao.memberVerify(id, pw);
			
			HttpSession session	= null;
			MemberDto member = null;
			if(isVerified) {
				member = mdao.memberSearchByID(id);
				session = request.getSession();
				session.setAttribute("memberLogin", member);
			}
			request.setAttribute("isVerified", isVerified);
			System.out.println(">>MemberCommand_signin() end");
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
