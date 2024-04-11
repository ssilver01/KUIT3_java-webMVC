package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import core.mvc.view.View;
import core.mvc.view.JspView;

public class CreateUserController implements Controller {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        MemoryUserRepository.getInstance().addUser(user);

//        return REDIRECT + "/user/userList";
        return new JspView(REDIRECT+ "/user/userList");
    }
}
