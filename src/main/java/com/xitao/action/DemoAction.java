package com.xitao.action;


import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoAction implements EnvironmentAware {
    private Environment environment;


    @RequestMapping(value={"/index","/"},method = {RequestMethod.GET})
    public String processDemo(Model model)  {

        System.out.println("hello world");
        model.addAttribute("demo","GO GO GO");
        String company = environment.getProperty("java.vendor");


        return "index.jsp";

    }
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

    }
}
