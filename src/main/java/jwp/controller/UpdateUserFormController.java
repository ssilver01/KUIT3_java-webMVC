package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import core.mvc.view.View;
import core.mvc.view.JspView;

public class UpdateUserFormController implements Controller {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {

        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if (user != null) {
            req.setAttribute("user", user);
//            return "/user/updateForm.jsp";
              return new JspView("/user/updateForm.jsp");
        }
//        return REDIRECT + "/";
        return new JspView(REDIRECT+"/");
    }

}
