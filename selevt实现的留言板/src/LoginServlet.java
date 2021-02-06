import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name =request.getParameter("username");
        String password =request.getParameter("password");
//        System.out.println(name);
//        System.out.println(password);
        try
        {
            FileReader fr = new FileReader("/Users/teleport/Idea_Project/Test/user.dat");
            int cnt;
            String str = "";
            while((cnt = fr.read()) != -1)
            {
                str += (char)cnt;
            }
            fr.close();

            String[] user=str.split(" ");
            int flag = 0;
            for(int i = 0 ; i < user.length ; i ++)
            {
                String[] info = user[i].split(",");
                if(name.equals(info[0]) && password.equals(info[1]))
                    flag = 1;
            }
            if(flag == 0)
                response.sendRedirect("loginError.jsp");
            else
            {
//                System.out.println("!!!");
                request.setAttribute("name", name);
                request.getRequestDispatcher("board.jsp").forward(request, response);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
