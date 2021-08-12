package mvc.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.board.BoardCommand;
import mvc.command.board.BoardCommand_delete;
import mvc.command.board.BoardCommand_edit;
import mvc.command.board.BoardCommand_list;
import mvc.command.board.BoardCommand_read;
import mvc.command.board.BoardCommand_search;
import mvc.command.board.BoardCommand_update;
import mvc.command.board.BoardCommand_write;

@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardCommand boardCom	= null;
		String command		= "";
		String viewPage		= "";
		
		String uri = request.getRequestURI();
		command = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));		
		System.out.println("command : "+ command);		
		
		//경로 설정
		if(command.equals("list")) {
			System.out.println("BoardCommand_list 호출");
			boardCom = new BoardCommand_list();
			boardCom.execute(request, response);
			//디버깅
			if(request.getAttribute("boardList") == null) {
				System.out.println("[error] parameter boardList is null");
			}
			viewPage = "./boardList.jsp";
		}
		else if(command.equals("search")) {
			System.out.println("BoardCommand_search 호출");
			boardCom = new BoardCommand_search();
			boardCom.execute(request, response);
			viewPage = "./temp.jsp";
		}
		else if(command.equals("read")) {
			System.out.println("BoardCommand_read 호출");
			boardCom = new BoardCommand_read();
			boardCom.execute(request, response);
			int artiNum = (int) request.getAttribute("artiNum");
			viewPage = "./boardRead.jsp?artiNum="+ artiNum;
		}
		else if(command.equals("edit")) {
			System.out.println("BoardCommand_edit 호출");
			boardCom = new BoardCommand_edit();
			boardCom.execute(request, response);
			viewPage = "./boardForm_edit.jsp";
		}
		else if(command.equals("write")) {
			System.out.println("BoardCommand_write 호출");
			boardCom = new BoardCommand_write();
			boardCom.execute(request, response);
			viewPage = "./board_temp.jsp";
		}
		else if(command.equals("update")) {
			System.out.println("BoardCommand_update 호출");
			boardCom = new BoardCommand_update();
			boardCom.execute(request, response);
			viewPage = "./board_temp.jsp";
		}
		else if(command.equals("delete")) {
			System.out.println("BoardCommand_delete 호출");
			boardCom = new BoardCommand_delete();
			boardCom.execute(request, response);
			viewPage = "./board_temp.jsp";
		}
		else {
			System.out.println("--error : 요청정보를 찾지못함");
			viewPage = "./temp.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);		
	}
}
