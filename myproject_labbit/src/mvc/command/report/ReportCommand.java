package mvc.command.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReportCommand {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
