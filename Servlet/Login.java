package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @Auther: wjy
 * @Date: 2018/9/25 17:38
 * @Description:
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            int flag = mysql(username, password);
            if(flag == 1){
                request.getSession().setAttribute("user", username);
                request.getRequestDispatcher("../main.jsp").forward(request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public int mysql(String username,String password) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/words" +
                    "?useSSL=true","root","993344");
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM words.user WHERE user_name = '" + username + "' AND user_pass = '" + password + "'";
            if(sta.executeQuery(sql).next()){
                return 1;
            }
            else{
                System.out.println("登录失败！");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
    }
}
