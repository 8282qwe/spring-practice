package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    @RequestMapping 메소드 단독 매핑
 */
@Controller
public class BoardController {

    @ResponseBody
    @RequestMapping("/board/write")
    public String write() {
        return "BoardController:write()";
    }

    @ResponseBody
    @RequestMapping("/board/view/{id}")
    public String view(@PathVariable("id") Long number) {
        return "BoardController:view(" + number + ")";
    }
}
