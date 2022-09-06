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
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
		String userName=request.getParameter("username");
		String passWord=request.getParameter("password");
		Connection conn=Conn.getConn();
		String sql = "select * from User where userName=?";
		//用户注册
					try {
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, userName);
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {//存在
						out.print("用户名已存在!");
						return;
					}else {
						sql = "insert into User values(null,?,?)";
						pst = conn.prepareStatement(sql);
						pst.setString(1,userName);
						pst.setString(2, passWord);
						if(pst.executeUpdate()>0) {
							out.print("注册成功!");
						}else {
							out.print("注册失败!");
							return;
						}
					}
					pst.close();
					rs.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.print("注册失败！数据库查询出错");
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
