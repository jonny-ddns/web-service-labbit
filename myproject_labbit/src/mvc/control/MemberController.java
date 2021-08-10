package mvc.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.member.MemberCommand;
import mvc.command.member.MemberCommand_memberDelete;
import mvc.command.member.MemberCommand_memberEdit;
import mvc.command.member.MemberCommand_memberView;
import mvc.command.member.MemberCommand_pwCompare;
import mvc.command.member.MemberCommand_signin;
import mvc.command.member.MemberCommand_signup;

@WebServlet("*.bo")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionBo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionBo(request, response);
	}
	
	private void actionBo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberCommand com = null;
		String command = "";
		String viewPage = "";
		
		//������ �ܾ url���� �����ϴ� ����		
		String uri = request.getRequestURI();
		command = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));		
		System.out.println("command : "+ command); 

		if(command.equals("signup")) {
			System.out.println("MemberCommand_signup ȣ��");
			com = new MemberCommand_signup();
			com.execute(request, response);
			viewPage = "../index.jsp";
		}
		else if(command.equals("signin")) {
			System.out.println("MemberCommand_signin ȣ��");
			com = new MemberCommand_signin();
			com.execute(request, response);
			viewPage = "./signin_result.jsp";
		}
		else if(command.equals("compare")) {
			System.out.println("MemberCommand_pwCompare ȣ��");
			com = new MemberCommand_pwCompare();
			com.execute(request, response);			
			viewPage = "./memberVerify.jsp";
			if((boolean) request.getAttribute("compareResult")) {
				viewPage = "./memberEdit_form.jsp";
			}
		}
		else if(command.equals("memberEdit")) {
			System.out.println("MemberCommand_memberEdit ȣ��");
			com = new MemberCommand_memberEdit();
			com.execute(request, response);
			viewPage = "../index.jsp";
		}
		else if(command.equals("memberDelete")) {
			System.out.println("MemberCommand_memberDelete ȣ��");
			com = new MemberCommand_memberDelete();
			com.execute(request, response);
			viewPage = "../index.jsp";
		}
		else if(command.equals("memberView")) {
			System.out.println("MemberCommand_memberView ȣ��");
			com = new MemberCommand_memberView();
			com.execute(request, response);
			viewPage = "./memberView.jsp";
		}
		else {
			System.out.println("--�������� ã�� ����");
			viewPage = "../index.jsp";
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
