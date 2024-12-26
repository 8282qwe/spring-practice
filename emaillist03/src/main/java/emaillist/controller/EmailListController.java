package emaillist.controller;

import emaillist.repository.EmailListRepository;
import emaillist.vo.EmailListVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmailListController {
    // @Autowired
    private final EmailListRepository emailListRepository;

    EmailListController(EmailListRepository emailListRepository) {
        this.emailListRepository = emailListRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<EmailListVo> list = emailListRepository.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/add")
    public String add(EmailListVo vo) {
        emailListRepository.insert(vo);
        return "redirect:/";
    }
}
