package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.controllers.dto.HealthAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advice")
@Slf4j
public class HealthAdviceController {

    @PostMapping
    public void provideHealthAdviceCallback(@RequestBody final List<HealthAdvice> healthAdvice) {
        healthAdvice.forEach(h -> log.info("Advice for: " + h.getUsername() +
                " Advice text: " + h.getAdvice()));
    }
}
