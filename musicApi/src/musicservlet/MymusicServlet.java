package musicservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


import javabean.Mymusic;
import musicservlet.Conn;

/**
 * Servlet implementation class MymusicServlet
 */
@WebServlet("/MymusicServlet")
public class MymusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MymusicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection conn=Conn.getConn();
		String userName=request.getParameter("userName");
		String item=request.getParameter("item");
		String sql = "select * from mymusic where userName=? and (musicName like '%' ? '%' or musicType like '%' ? '%' or singer like '%' ? '%' or album like '%' ? '%')";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			pst.setString(2, item);
			pst.setString(3, item);
			pst.setString(4, item);
			pst.setString(5, item);
			ResultSet rs = pst.executeQuery();
			List<Mymusic> mymusics = new ArrayList<Mymusic>();
			while(rs.next()) {
				Mymusic mymusic = new Mymusic();
				mymusic.setMusicName(rs.getString("musicName"));
				mymusic.setAlbum(rs.getString("album"));
				mymusic.setSinger(rs.getString("singer"));
				mymusic.setMusicType(rs.getString("musicType"));
				mymusic.setMp3File(rs.getString("mp3File"));
				mymusic.setUploader(rs.getString("uploader"));
				mymusics.add(mymusic);
			}
			ObjectMapper om=new ObjectMapper();
			String json=om.writeValueAsString(mymusics);
			out.print(json); 

		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
