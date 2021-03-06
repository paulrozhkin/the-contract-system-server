package com.itmo.goblinslayersystemserver.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер (практически конфигурация) для обеспечения перенаправления Angular на index.html.
 */
@Controller
public class ForwardController {
    @RequestMapping(value = "/**/{[path:[^\\.]*}")
    public String redirect() {
        // Forward to home page so that route is preserved.
        return "forward:/";
    }
}
