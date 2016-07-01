

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.OutputData;
import data.RequestData;
import data.ResponseData;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/getData")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		
		DBManager dm = new DBManager();
		RequestData rd = new RequestData();
		rd.sendPerson = request.getParameter("sendPerson");
		rd.receivePerson = request.getParameter("receivePerson");
		
		dm.setParam(rd);
		PrintWriter out = response.getWriter();
		
		OutputData od = new OutputData();
		od.resArr = new ArrayList<ResponseData>();
		if( dm.getResult(od.resArr)){
			System.out.println(""+od.resArr.size());
			out.print(gson.toJson(od));
		}else
			out.print("fail");
	}

}
