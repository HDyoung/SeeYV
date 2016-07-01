
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import data.RequestData;
import data.ResponseData;
import data.VoiceData;

public class DBManager {

	private DBConnectionMgr pool = null;
	private VoiceData param;
	private RequestData Id;
	public DBManager(){

		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : Exception");
		}
	}

	public void setParam (VoiceData val)  {param = val;}
	public void setParam (RequestData val) {Id = val;}
	
	public boolean getResult(List<ResponseData> list) {
		   Connection conn = null;
		   Statement stmt = null;
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   Id.sendPerson='"'+Id.sendPerson+'"';
		   Id.receivePerson='"'+Id.receivePerson+'"';
		   		   
	       try {
	    	   conn = pool.getConnection();
	    	   //조건절은 더 변수가 있다면 추가
	    	   String strQuery = "select sendPerson,time,data from room where (receivePerson ="+ Id.receivePerson+ "AND sendPerson ="+ Id.sendPerson+ ") OR (receivePerson =" + Id.sendPerson+ "AND sendPerson= " + Id.receivePerson+ ")" + "Order by time" ;	    	   		    	   
	    	   ps = (PreparedStatement) conn.prepareStatement(strQuery);
	    	   rs = ps.executeQuery();
	    	   System.out.println("id :"+ Id.sendPerson);
	    	   
	    	   
	    	   while(rs.next()){
	    		   ResponseData rd = new ResponseData();
	    		   rd.sendPerson = rs.getString(1);
	    		   rd.time = rs.getString(2);
	    		   rd.data = rs.getString(3);
	    		   list.add(rd);
	    		   System.out.println(rd);
	    	   }
	    	   System.out.println("size :"+ list.size());
	    	   return true;

	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);

	       } finally {
		      pool.freeConnection(conn);
	       }
	       return false;
	}

	public boolean onSave() {
		   Connection conn = null;
		   Statement stmt = null;
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   //String time = sdf.format(param.time);
		  
		   long time = Long.parseLong(param.time);
		   
		   param.sendPerson='"'+param.sendPerson+'"';
		   param.receivePerson='"'+param.receivePerson+'"';
		   param.data='"'+param.data+'"';
		   
		   System.out.println("time : "+param.time);
		   System.out.println("data : "+param.data);
		   System.out.println("you : "+param.receivePerson);
		   System.out.println("me : "+param.sendPerson);
	       try {
	    	   conn = pool.getConnection();
	    	   String strQuery = "insert into room (sendPerson,receivePerson,time,data)"
	    	   		+ " values("+param.sendPerson+","+param.receivePerson+","+time+","+param.data+");";//select * from usertable";
	    	   //stmt = conn.createStatement();
	    	   ps = (PreparedStatement) conn.prepareStatement(strQuery);
	    	   if( !ps.execute()){
	    		   return true;
	    	   }
	    	   else{ 
	    		   return false;
	    	   }
	    	  // rs = stmt.executeQuery(strQuery);	 	    	

	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);

	       } finally {
		      pool.freeConnection(conn);
	       }
	       return false;
	}

}