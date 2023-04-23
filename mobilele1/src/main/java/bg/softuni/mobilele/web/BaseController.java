package bg.softuni.mobilele.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.yaml.snakeyaml.constructor.BaseConstructor;

public abstract class BaseController {
    public ModelAndView view(String viewName, ModelAndView modelAndView){
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    public ModelAndView view(String viewName){

        return this.view(viewName, new ModelAndView());
    }

    public  ModelAndView redirect(String url){
        return this.view("redirect" + url);
    }
}
