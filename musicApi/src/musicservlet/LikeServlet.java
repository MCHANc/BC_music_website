package musicservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import musicservlet.Conn;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
@MultipartConfig
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
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
		response.setContentType("text/plain;charSet=utf-8");
		PrintWriter out = response.getWriter();
		String userName=request.getParameter("userName");
		String musicName=request.getParameter("musicName");
		String singer=request.getParameter("singer");
		String album=request.getParameter("album");
		String musicType=request.getParameter("musicType");
		String uploader=request.getParameter("uploader");
		String mp3File=request.getParameter("mp3File");
		Connection conn=Conn.getConn();
		String sql = "select * from mymusic where userName=? and mp3File=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			pst.setString(2, mp3File);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {//存在
				sql = "delete from mymusic where userName=? and mp3File=?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, userName);
				pst.setString(2, mp3File);
				if(pst.executeUpdate()>0) {
					out.print("已取消收藏");
				}else {
					out.print("收藏失败!");
					return;
				}
				pst.close();
				rs.close();
				conn.close();
				return;
			}else{
				sql = "insert into mymusic values(null,?,?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				pst.setString(1, userName);
				pst.setString(2, musicName);
				pst.setString(3, singer);
				pst.setString(4, album);
				pst.setString(5, musicType);
				pst.setString(6, uploader);
				pst.setString(7, mp3File);
				
				if(pst.executeUpdate()>0) {
					out.print("收藏成功!");		
				}else {
					out.print("收藏失败!");
					return;
				}
				pst.close();
				rs.close();
				conn.close();
				return;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.print("错误");
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
