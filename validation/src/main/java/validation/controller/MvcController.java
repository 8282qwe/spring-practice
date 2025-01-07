package validation.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import validation.domain.User;

@Controller
public class MvcController {

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/ex01")
    public String ex1() {
        return "form/ex01";
    }

    @PostMapping("/ex01")
    public String ex1(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
//            for (ObjectError error : result.getAllErrors()) {
//                System.out.println(error);
//            }

            // 이 형식을 지켜야 jsp 단에서 태그로 파싱할 수 있음
//            Map<String, Object> map = result.getModel();
//
//            Set<String> keys = map.keySet();
//            for (String key : keys) {
//                System.out.println(key);
//                model.addAttribute(key, map.get(key));
//            }
            model.addAllAttributes(result.getModel());
            return "form/ex01";
        }

        return "redirect:/result";
    }

    @GetMapping("/ex02")
    public String ex2() {
        return "form/ex02";
    }

    @PostMapping("/ex02")
    public String ex2(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
//            for (ObjectError error : result.getAllErrors()) {
//                System.out.println(error);
//            }

            // 이 형식을 지켜야 jsp 단에서 태그로 파싱할 수 있음
//            Map<String, Object> map = result.getModel();
//
//            Set<String> keys = map.keySet();
//            for (String key : keys) {
//                System.out.println(key);
//                model.addAttribute(key, map.get(key));
//            }
            model.addAllAttributes(result.getModel());
            return "form/ex02";
        }

        return "redirect:/result";
    }

    @GetMapping("/ex03")
    public String ex3(@ModelAttribute User user) {
        return "form/ex03";
    }

    @PostMapping("/ex03")
    public String ex3(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
//            for (ObjectError error : result.getAllErrors()) {
//                System.out.println(error);
//            }

            // 이 형식을 지켜야 jsp 단에서 태그로 파싱할 수 있음
//            Map<String, Object> map = result.getModel();
//
//            Set<String> keys = map.keySet();
//            for (String key : keys) {
//                System.out.println(key);
//                model.addAttribute(key, map.get(key));
//            }
            model.addAllAttributes(result.getModel());
            return "form/ex03";
        }

        return "redirect:/result";
    }
}
