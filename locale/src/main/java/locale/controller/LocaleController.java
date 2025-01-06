package locale.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LocaleController {
    private final LocaleResolver localeResolver;

    public LocaleController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @RequestMapping({"","/","/index"})
    public String index(HttpServletRequest request, Model model) {
        String lang = localeResolver.resolveLocale(request).getLanguage();
        System.out.println("Language Code: " + lang);

        model.addAttribute("lang", lang);
        return "index";
    }
}
