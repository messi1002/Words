package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
/**
 * @Auther: wjy
 * @Date: 2018/9/27 17:14
 * @Description:
 */
public class Review extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getSession().getAttribute("user");
        System.out.println(user);
        String[] s = new String[0];
        try {
            s = mysql(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("buffer",s);
        request.getRequestDispatcher("../review.jsp").forward(request, response);
    }

    private String[] mysql(String user) throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT user_word,user_mean,user_date FROM words.allWords WHERE user_name = '" + user + "'";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" +
                "words?useSSL=true", "root", "993344");
        PreparedStatement pre = con.prepareStatement(sql1,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet res = pre.executeQuery();
        res.last();
        int a = res.getRow();
        System.out.println(a);
        String[] s = new String[a*3];
        int k = 1;
        res.first();
        for (int i = 0; i < s.length; i=i+3) {
            res.absolute(k);
            System.out.println(k);
            k++;
            s[i] = res.getString(1);
            System.out.println(s[i]);
            s[i+1] = res.getString(2);
            s[i+2] = res.getString(3);
        }
        return s;
    }
}
