package hellospring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "/WEB-INF/views/hello.jsp";
    }

    @RequestMapping("/hello2")
    public String hello(@RequestParam("name") String name) {
        System.out.println("name : " + name);
        return "/WEB-INF/views/hello.jsp";
    }

    @RequestMapping("/hello3")
    public ModelAndView hello3(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", name);
        modelAndView.setViewName("/WEB-INF/views/hello3.jsp");
        return modelAndView;
    }

    @RequestMapping("/hello4")
    public String hello4(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "/WEB-INF/views/hello3.jsp";
    }

    @ResponseBody
    @RequestMapping("/hello5")
    public String hello5() {
        return "<h1>Hello Spring!</h1>";
    }

    @RequestMapping("/hello6")
    public String hello6() {
        return "redirect:/hello";
    }

    @RequestMapping("/hello7")
    public void hello7(HttpServletResponse resp, HttpServletRequest res) throws IOException {
        String name = res.getParameter("name");
        System.out.println("name : " + name);

        resp.getWriter().println("<h1>Hello World!</h1>");
    }
}
