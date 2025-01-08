package guestbook.controller;

import guestbook.service.GuestbookService;
import guestbook.vo.GuestbookVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestBookController {
    private final GuestbookService guestbookService;

    public GuestBookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @RequestMapping("/")
    public String index(Model model
//            , HttpServletRequest request
    ) {
//        ServletContext sc = request.getServletContext();
//        Enumeration<String> en = sc.getAttributeNames();
//
//        while (en.hasMoreElements()) {
//            String key = en.nextElement();
//            System.out.println(key);
//        }
//        ApplicationContext ac1 = (ApplicationContext) sc.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
//        ApplicationContext ac2 = (ApplicationContext) sc.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring");
//        GuestbookRepository repo1 = ac1.getBean(GuestbookRepository.class);
//        System.out.println(repo1);
//        GuestBookController repo2 = ac2.getBean(GuestBookController.class);
//        System.out.println(repo2);

        model.addAttribute("guestbooks", guestbookService.getContentsList());
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(GuestbookVo vo) {
//        guestbookRepository.insert(vo);
        guestbookService.addContents(vo);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String del(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "delete";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, @RequestParam("password") String password) {
//        guestbookRepository.deleteByIdAndPassword(id,password);
        guestbookService.deleteContents(id, password);
        return "redirect:/";
    }
}
