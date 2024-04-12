package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;

public abstract class AbstractController implements Controller {
    public ModelAndView jspView(String url) {
        return new ModelAndView(new JspView(url));
    }

    public ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}