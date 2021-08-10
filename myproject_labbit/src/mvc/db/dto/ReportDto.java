package mvc.db.dto;

public class ReportDto {
	private String reportNum;
	private String memId;
	private int boardNum;
	private String reportKind;
	private String reportCause;
	private String isResolved;
	
	public String getReportNum() {
		return reportNum;
	}
	public ReportDto setReportNum(String reportNum) {
		this.reportNum = reportNum;
		return this;
	}
	public String getMemId() {
		return memId;
	}
	public ReportDto setMemId(String memId) {
		this.memId = memId;
		return this;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public ReportDto setBoardNum(int boardNum) {
		this.boardNum = boardNum;
		return this;
	}
	public String getReportKind() {
		return reportKind;
	}
	public ReportDto setReportKind(String reportKind) {
		this.reportKind = reportKind;
		return this;
	}
	public String getReportCause() {
		return reportCause;
	}
	public ReportDto setReportCause(String reportCause) {
		this.reportCause = reportCause;
		return this;
	}
	public String getIsResolved() {
		return isResolved;
	}
	public ReportDto setIsResolved(String isResolved) {
		this.isResolved = isResolved;
		return this;
	}	
}