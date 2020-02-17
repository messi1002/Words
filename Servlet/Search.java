package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @Auther: wjy
 * @Date: 2018/9/27 01:34
 * @Description:
 */
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getSession().getAttribute("user");
        String word = request.getParameter("word");
        System.out.println(user);
        String[] s = mysql(user);
        request.setAttribute("number",s[0]);
        request.setAttribute("primary",s[1]);
        request.setAttribute("middle",s[2]);
        request.setAttribute("advanced",s[3]);
        request.setAttribute("day",s[4]);
        try {
            String result = search(word);
            request.setAttribute("result",result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("../search.jsp").forward(request,response);
    }

    private String[] mysql(String user){
        String[] s = new String[5];
        String sql1 = "SELECT COUNT(user_id) FROM words.allwords WHERE user_name = '"+user+"'";
        String sql2 = "SELECT COUNT(user_word) FROM words.allwords WHERE LENGTH(user_word) < 6 AND user_name = '"+user+"'";
        String sql3 = "SELECT COUNT(user_word) FROM words.allwords WHERE LENGTH(user_word) BETWEEN 6 AND 9 AND user_name = '"+user+"'";
        String sql4 = "SELECT COUNT(user_word) FROM words.allwords WHERE LENGTH(user_word) > 10 AND user_name = '"+user+"'";
        String sql5 = "SELECT COUNT(user_date) FROM words.allwords WHERE user_name = '"+user+"' GROUP BY (user_date)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" +
                    "words?useSSL=true","root","993344");
            Statement sta1 = con.createStatement();
            ResultSet Number = sta1.executeQuery(sql1);
            Number.next();
            s[0] = String.valueOf(Number.getInt(1));
            Statement sta2 = con.createStatement();
            ResultSet Primary = sta2.executeQuery(sql2);
            Primary.next();
            s[1] = String.valueOf(Primary.getInt(1));
            Statement sta3 = con.createStatement();
            ResultSet middle = sta3.executeQuery(sql3);
            middle.next();
            s[2] = String.valueOf(middle.getInt(1));
            Statement sta4 = con.createStatement();
            ResultSet Advanced = sta4.executeQuery(sql4);
            Advanced.next();
            s[3] = String.valueOf(Advanced.getInt(1));
            Statement sta5 = con.createStatement();
            ResultSet day = sta5.executeQuery(sql5);
            day.last();
            s[4] = String.valueOf(day.getRow());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }

    private String search(String word) throws ClassNotFoundException, SQLException {
        String result = " ";
        String sql = "SELECT user_word,user_mean,user_date FROM words.allWords WHERE user_word = '"+word+"'";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" +
                "words?useSSL=true","root","993344");
        Statement sta1 = con.createStatement();
        ResultSet res = sta1.executeQuery(sql);
        res.first();
        if(!res.next()) {
            result = res.getString(1) + "------" + res.getString(2)
                    + "------" + res.getString(3);
            return result;
        }
        else {
            result = null;
            return result;
        }
    }
}
