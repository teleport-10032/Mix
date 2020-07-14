import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SubmitServlet",urlPatterns="/SubmitServlet")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("username");
        String text = request.getParameter("text");

        String str = "/Users/teleport/Idea_Project/Test/board.dat";
        File file = new File(str);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    System.out.println("file not exists");
                }
                fos = new FileOutputStream(file);
            } else {
                System.out.println("file exists");
                fos = new FileOutputStream(file, true);
            }
            osw = new OutputStreamWriter(fos, "utf-8");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");//设置日期格式
            osw.write(text+"--------"+name+ ","+new String(df.format(new Date()))+" ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("name", name);
        request.getRequestDispatcher("board.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
