package mvc.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.db.DBConnectionPool;
import mvc.db.dto.BoardDto;

/*
 * @todo
 * -페이징 기능
 */

public class BoardDao extends DAO {
	private static BoardDao boardDao		= null;
	private static List<BoardDto> boardList = null;
		
	private BoardDao(){ }
	
	public static BoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDao();
		}
		boardList = new ArrayList<BoardDto>();
		return boardDao;
	}
	
	public List<BoardDto> getBoardList(){
		return boardList;
	}
	
	/*------------------------------------------------------------------------*/
	
	//게시글 모두 가져오기
	public List<BoardDto> getBoardAll() throws SQLException, ClassNotFoundException {
		System.out.println("BoardDao - getBoardAll()");
		String sql = "SELECT * FROM board WHERE isActive='y' ORDER BY boardNum DESC;";
	
		dbcp 	= DBConnectionPool.getInstance();
		conn 	= dbcp.getConnection();
		pstmt 	= conn.prepareStatement(sql);
		rs 		= pstmt.executeQuery();
		
		List<BoardDto> boardList = getBoardList();
		BoardDto board;
		
		while(rs.next()){
			board = new BoardDto();
			board.setBoardNum(Integer.parseInt(rs.getString("artiNum")))
				 .setBoardTitle(rs.getString("artiTitle"))
				 .setMemId(rs.getString("writer"))
				 .setWriteDate(rs.getTimestamp("writeDate"))
				 .setContent(rs.getString("content"))
				 .setUploadFiles(rs.getString("uploadFiles"))
				 .setIsActive(rs.getString("isActive"))
				 .setBoardLike(rs.getInt("boardLike"))
				 .setBoardReport(rs.getInt("boardReport"));
			boardList.add(board);
		}
		if(rs != null) { rs.close(); };
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
	
		System.out.println("getBoardAll - end");
		return boardList;
	}

	//게시글 작성하기
	public void boardWrite(BoardDto board) throws SQLException, ClassNotFoundException {
		System.out.println("BoardDao - boardWrite()");
		String sql = "INSERT INTO board(boardTitle, memId, writeDate, content, uploadFiles, isActive) "
				+ "VALUES(?, ?, now(), ?, ?, 'y');";
		
		dbcp	= DBConnectionPool.getInstance();
		conn 	= dbcp.getConnection();
		pstmt 	= conn.prepareStatement(sql);
		
		pstmt.setString(1, board.getBoardTitle());
		pstmt.setString(2, board.getMemId());
		pstmt.setString(3, board.getContent());
		pstmt.setString(4, board.getUploadFiles());
		pstmt.executeUpdate();
	
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
		
		System.out.println("boardWrite - end");
	}
	
	//게시글번호로 게시글 찾기
	public BoardDto boardSearchByBoardNum(int BoardNum) throws SQLException, ClassNotFoundException {
		System.out.println("BoardDao - boardSearchByBoardNum()");
		String sql = "SELECT * FROM board WHERE boardNum=?;";	
		
		dbcp 	= DBConnectionPool.getInstance();
		conn	= dbcp.getConnection();
		pstmt	= conn.prepareStatement(sql);
		pstmt.setInt(1, BoardNum);
		rs		= pstmt.executeQuery();
				
		BoardDto board = null;
		if(rs.next()) {
			board = new BoardDto();
			
			board.setBoardNum(Integer.parseInt(rs.getString("artiNum")))
				 .setBoardTitle(rs.getString("artiTitle"))
				 .setMemId(rs.getString("writer"))
				 .setWriteDate(rs.getTimestamp("writeDate"))
				 .setContent(rs.getString("content"))
				 .setUploadFiles(rs.getString("uploadFiles"))
				 .setIsActive(rs.getString("isActive"))
				 .setBoardLike(rs.getInt("boardLike"))
				 .setBoardReport(rs.getInt("boardReport"));
		}
		if(rs != null) { rs.close(); }
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }

		System.out.println("boardSearchByBoardNum - end");
		return board;
	}

	//아이디로 게시글 찾기
	public BoardDto boardSearchByID(String id) throws SQLException, ClassNotFoundException {
		System.out.println("BoardDao - boardSearchByID()");
		String sql = "SELECT * FROM board WHERE id='"+ id+ "';";
		
		dbcp 	= DBConnectionPool.getInstance();
		conn	= dbcp.getConnection();
		pstmt	= conn.prepareStatement(sql);
		rs		= pstmt.executeQuery();
		
		BoardDto board = null;
		if(rs.next()) {
			board = new BoardDto();
			board.setBoardNum(Integer.parseInt(rs.getString("artiNum")))
				 .setBoardTitle(rs.getString("artiTitle"))
				 .setMemId(rs.getString("writer"))
				 .setWriteDate(rs.getTimestamp("writeDate"))
				 .setContent(rs.getString("content"))
				 .setUploadFiles(rs.getString("uploadFiles"))
				 .setIsActive(rs.getString("isActive"))
				 .setBoardLike(rs.getInt("boardLike"))
				 .setBoardReport(rs.getInt("boardReport"));
		}
		if(rs != null) { rs.close(); }
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }

		System.out.println("boardSearchByID - end");
		return board;
	}	
	
	//게시글 수정하기
	public void boardUpdate(BoardDto board, int boardNum) throws SQLException, ClassNotFoundException{
		System.out.println("BoardDao - boardUpdate()");
		String sql = "UPDATE board SET artiTitle=?, content=?, modifyDate=now() WHERE artiNum=?;";
		
		dbcp 	= DBConnectionPool.getInstance();
		conn	= dbcp.getConnection();
		pstmt	= conn.prepareStatement(sql);
		pstmt.setString(1, board.getBoardTitle());
		pstmt.setString(2, board.getContent());
		pstmt.setInt(3, boardNum);
		pstmt.executeUpdate();
		
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }

		System.out.println("boardUpdate - end");
	}

	//게시글 삭제하기
	public boolean boardDelete(int boardNum) throws SQLException, ClassNotFoundException{
		System.out.println("BoardDao - boardDelete()");
		String sql = "UPDATE board SET isActive='n' WHERE boardNum=?";
		
		boolean isDeleted = false;

		dbcp 	= DBConnectionPool.getInstance();
		conn	= dbcp.getConnection();
		pstmt	= conn.prepareStatement(sql);
		pstmt.setInt(1, boardNum);
		pstmt.executeUpdate();
	
		isDeleted = true;
		
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
		
		System.out.println("boardDelete() - end");
		
		return isDeleted;
	}
}