package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//loaldOnStartup : 값이 0보다 크거나 같으면, 서블릿은 애플리케이션 시작 시점에 로드
@WebServlet(name = "dispathcer", urlPatterns = "/",  loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private RequestMapper requestMapper;
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    public void init() throws ServletException {
        this.requestMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = requestMapper.getController(req);
        try {
            String viewName = controller.execute(req, resp);
            move(viewName, req, resp);
        } catch (Throwable e) {
            throw new ServletException(e.getMessage());
        }
    }

    private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            resp.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }
}