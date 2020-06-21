package servlet;

import com.google.gson.Gson;
import model.User;
import service.UserService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println(PageGenerator.getInstance().getPage("registerPage.html"));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService.getInstance().addUser(new User(email, password));
        // UserService.getInstance().showdataBase();
        // UserService.getInstance().showauthMap();
        resp.setStatus(HttpServletResponse.SC_OK);
       //  Gson gson = new Gson();
       //  String json = email + "  "+ password;
        resp.getWriter().println(PageGenerator.getInstance().getPage("registerPage.html"));
       // resp.getWriter().println(json);

        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
