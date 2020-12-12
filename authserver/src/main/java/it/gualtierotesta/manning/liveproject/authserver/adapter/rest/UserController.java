package it.gualtierotesta.manning.liveproject.authserver.adapter.rest;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.UsersServicePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersServicePort usersService;

    @GetMapping("")
    public Collection<AppUser> listAllUsers() {
        return usersService.listAll();
    }
}
