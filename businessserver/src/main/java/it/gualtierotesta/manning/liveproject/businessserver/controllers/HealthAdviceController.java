package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.controllers.dto.HealthAdvice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/advice")
public class HealthAdviceController {

    private static Logger logger = Logger.getLogger(HealthAdviceController.class.getName());

    @PostMapping
    public void provideHealthAdviceCallback(@RequestBody List<HealthAdvice> healthAdvice) {
        healthAdvice.forEach(h -> logger.info("Advice for: " + h.getUsername() +
                " Advice text: " + h.getAdvice()));
    }
}
