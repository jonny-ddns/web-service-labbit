package mvc.db.dto;

import java.util.Date;

public class BoardDto {	
	private int boardNum;
	private String boardTitle;
	private String memId;
	private Date writeDate;
	private Date modifyDate;
	private String content;
	private String uploadFiles;
	private String isActive;
	private int boardLike;
	private int boardReport;
	
	public int getBoardNum() {
		return boardNum;
	}
	public BoardDto setBoardNum(int boardNum) {
		this.boardNum = boardNum;
		return this;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public BoardDto setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
		return this;
	}
	public String getMemId() {
		return memId;
	}
	public BoardDto setMemId(String memId) {
		this.memId = memId;
		return this;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public BoardDto setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
		return this;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public BoardDto setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
		return this;
	}
	public String getContent() {
		return content;
	}
	public BoardDto setContent(String content) {
		this.content = content;
		return this;
	}
	public String getUploadFiles() {
		return uploadFiles;
	}
	public BoardDto setUploadFiles(String uploadFiles) {
		this.uploadFiles = uploadFiles;
		return this;
	}
	public String getIsActive() {
		return isActive;
	}
	public BoardDto setIsActive(String isActive) {
		this.isActive = isActive;
		return this;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public BoardDto setBoardLike(int boardLike) {
		this.boardLike = boardLike;
		return this;
	}
	public int getBoardReport() {
		return boardReport;
	}
	public BoardDto setBoardReport(int boardReport) {
		this.boardReport = boardReport;
		return this;
	}
}