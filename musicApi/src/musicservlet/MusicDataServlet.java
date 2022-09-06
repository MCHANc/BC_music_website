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

import javabean.Music;
import musicservlet.Conn;

/**
 * Servlet implementation class MusicDataServlet
 */
@WebServlet("/MusicDataServlet")
public class MusicDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicDataServlet() {
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
		String item=request.getParameter("item");
		String sql = "select * from music where musicName like '%' ? '%' or musicType like '%' ? '%' or singer like '%' ? '%' or album like '%' ? '%'";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, item);
			pst.setString(2, item);
			pst.setString(3, item);
			pst.setString(4, item);
			ResultSet rs = pst.executeQuery();
			List<Music> musics = new ArrayList<Music>();
			while(rs.next()) {
				Music music = new Music();
				music.setMusicName(rs.getString("musicName"));
				music.setAlbum(rs.getString("album"));
				music.setSinger(rs.getString("singer"));
				music.setMusicType(rs.getString("musicType"));
				music.setMp3File(rs.getString("mp3File"));
				music.setUploader(rs.getString("uploader"));
				music.setUploadTime(rs.getString("uploadTime"));
				musics.add(music);
			}
			ObjectMapper om=new ObjectMapper();
			String json=om.writeValueAsString(musics);
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
