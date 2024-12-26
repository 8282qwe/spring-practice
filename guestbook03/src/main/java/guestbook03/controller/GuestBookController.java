package guestbook03.controller;

import guestbook03.repository.GuestbookRepository;
import guestbook03.vo.GuestbookVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestBookController {
    private final GuestbookRepository guestbookRepository;

    public GuestBookController(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("guestbooks", guestbookRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(GuestbookVo vo) {
        guestbookRepository.insert(vo);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String del(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "delete";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, @RequestParam("password") String password) {
        guestbookRepository.deleteByIdAndPassword(id,password);
        return "redirect:/";
    }
}
