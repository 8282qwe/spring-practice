package hellospring.controller;

/*
    @RequestMapping 클래스 + 메소드 단독 매핑
 */

import hellospring.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinform() {
        return "/WEB-INF/views/join.jsp";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserVo userVo) {
        System.out.println(userVo);
        return "redirect:/";
    }


    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(@RequestParam("n") Long no) {
        /*
            만일,   n이라는 파라미터  이름이 없거나 값이 올바르지 못하면
            400 bad request Error 발생
         */
        return "UserController: update(" + no + ")";

    }

    @ResponseBody
    @RequestMapping(value = "/update2")
    public String update2(@RequestParam(value = "n", required = true, defaultValue = "") Long no) {
        return "UserController: update(" + no + ")";
    }

    @ResponseBody
    @RequestMapping("/list")
    public String list(@RequestParam(value = "p", required = true, defaultValue = "")Integer pageNo) {
        return "UserController: list(" + pageNo + ")";
    }
}
