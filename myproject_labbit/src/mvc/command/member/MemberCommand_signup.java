package mvc.command.member;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.db.dao.MemberDao;
import mvc.db.dto.MemberDto;

public class MemberCommand_signup implements MemberCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(">>MemberCommand_signup()");
			
			//생년월일 값 변환하기
			String birth_year = request.getParameter("birth_year");
			String birth_month = request.getParameter("birth_month");
			String birth_day = request.getParameter("birth_day");
			String birthday = birth_year+ birth_month+ birth_day;
			
			//휴대전화 값 변환하기
			String nationNo = request.getParameter("nationNo");
			String phoneLastDigit = request.getParameter("phoneLastDigit");
			String phone = nationNo+ phoneLastDigit;
			
			
			//디버깅
			System.out.println("--디버깅");
			System.out.println(request.getParameter("memId"));
			System.out.println(request.getParameter("memName"));
			System.out.println(request.getParameter("passwd"));
			System.out.println(birthday);
			System.out.println(request.getParameter("gender"));
			System.out.println(request.getParameter("email"));
			System.out.println(phone);
			System.out.println("--디버깅 end");
			
			
			
			
			MemberDto member = new MemberDto();
			member.setMemId(request.getParameter("memId"))
				  .setMemName(request.getParameter("memName"))
			  	  .setPasswd(request.getParameter("passwd"))
				  .setBirthday(birthday)
				  .setGender(request.getParameter("gender"))
				  .setEmail(request.getParameter("email"))
				  .setPhone(phone);
			MemberDao mdao = MemberDao.getInstance();
			mdao.memberSignup(member);			
			System.out.println("MemberCommand_signup() end");
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
