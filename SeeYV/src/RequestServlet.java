

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.VoiceData;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/send")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		Gson gson = new Gson();

		DBManager dm = new DBManager();	
		VoiceData vd = new VoiceData();
		vd.data = request.getParameter("data");
		vd.receivePerson = request.getParameter("receivePerson");
		vd.sendPerson = request.getParameter("sendPerson");
		vd.time = request.getParameter("time");
	
		dm.setParam(vd);
		PrintWriter out = response.getWriter();
		if(dm.onSave()){
			out.print("success");
		}else
			out.print("fail");
	}

}
