package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @Auther: wjy
 * @Date: 2018/9/25 18:33
 * @Description:
 */
public class Register extends HttpServlet {
    public int flag = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        String pass1 = request.getParameter("password1");
        mysql(user,pass,pass1);
        if(flag == 1){
            response.sendRedirect("../login.jsp");
        }
    }
    public void mysql(String user,String pass,String pass1){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/words" +
                    "?useSSL=true","root","993344");
            String sql1 = "SELECT * FROM words.users WHERE users_name='" + user +"'";
            String sql2 = "INSERT INTO users VALUES(null,?,?)";
            Statement sta = con.createStatement();
            PreparedStatement pre = con.prepareStatement(sql2);
            if(!pass.equals(pass1)){
                System.out.println("密码不正确");
            }
            else if (sta.executeQuery(sql1).next()==true){
                //System.out.println(sta.execute(sql1));
                // bug点对于execute方法来说,查询结果为空也算的是返回结果集 返回true。
                // 所有不能把它用做判断是否查到结果。
                System.out.println("用户名已经存在");
            }
            else {
                pre.setString(1, user);
                pre.setString(2, pass);
                pre.executeUpdate();
                flag = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
