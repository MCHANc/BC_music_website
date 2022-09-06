package musicservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import musicservlet.Conn;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
		String musicName=request.getParameter("musicname");
		String album=request.getParameter("album");
		String singer=request.getParameter("singer");
		String musicType=request.getParameter("type");
		String uploader=request.getParameter("username");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		String musicid=sdf.format(date);
		String uploadTime=sdf2.format(date);
		Part part=request.getPart("file");
		long len =part.getSize();
		String fileName=null;
		if(len>0){
			String s=part.getHeader("Content-Disposition");
			int pos=s.lastIndexOf(".");
			String ext="";//后缀名
			if(pos>0){
				ext=s.substring(pos,s.length()-1);
				
			}
			fileName=musicid+ext;
			
		}
		
		Connection conn=Conn.getConn();
		String sql = "select * from music where musicid=?";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, musicid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {//存在
					out.print("该歌曲已存在!");
				return;
			}else{
				sql = "select * from music where musicName=? and album=?";
				pst = conn.prepareStatement(sql);
				pst.setString(1,musicName);
				pst.setString(2,album);
				rs = pst.executeQuery();
				if(rs.next()) {//存在
					out.print("该歌曲已存在!");
					return;
				}else{
					part.write(getServletContext().getRealPath("/mp3/")+fileName);				
					sql = "insert into music values(?,?,?,?,?,?,?,?)";
					pst = conn.prepareStatement(sql);
					pst.setString(1,musicid);
					pst.setString(2, musicName);
					pst.setString(3, album);
					pst.setString(4, singer);
					pst.setString(5, musicType);
					pst.setString(6, fileName);
					pst.setString(7, uploader);
					pst.setString(8, uploadTime);
					if(pst.executeUpdate()>0) {
						out.print("歌曲添加成功!");
						
					}else {
						out.print("歌曲添加失败!");
						return;
					}
				}
			}
			pst.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("歌曲添加失败!");
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
