package musicservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import musicservlet.Conn;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		String userName = request.getParameter("username"); 
		String password = request.getParameter("password"); 
		
		PrintWriter out = response.getWriter();
		Connection conn=Conn.getConn();
		String sql = "select * from User where userName=?";
		//用户登录
				
					try {
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, userName);
					ResultSet rs = pst.executeQuery();			
					if(rs.next()) {
						if(!rs.getString("password").equals(password)){
							out.print("密码错误!");
							return;
						}else{
							out.print("登录成功!");
						}
					}else {
						out.print("用户不存在!");
						return;
					}
					rs.close();
					pst.close();
					conn.close();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.print("登录失败！数据库查询错误!");
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
