package mvc.command.member;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.db.dao.MemberDao;
import mvc.db.dto.MemberDto;

public class MemberCommand_update implements MemberCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(">>MemberCommand_memberEdit()");
			MemberDto member = new MemberDto();
			member.setMemName(request.getParameter("memName"))
				  .setPasswd(request.getParameter("passwd"))
				  .setBirthday(request.getParameter("birthday"))
				  .setGender(request.getParameter("gender"))
				  .setEmail(request.getParameter("email"))
				  .setPhone(request.getParameter("phone"));			
			MemberDao mdao = MemberDao.getInstance();
			String memId = request.getParameter("memId");
			mdao.memberUpdate(member, memId);			
			System.out.println("MemberCommand_memberEdit() end");
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
