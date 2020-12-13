package it.gualtierotesta.manning.liveproject.authserver.adapter.rest.users;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.UsersServicePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersServicePort usersService;

    @GetMapping("")
    public ResponseEntity<Collection<User>> listAllUsers() {
        log.debug("listAllUsers");
        return ResponseEntity.ok(usersService.listAll());
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(
        @RequestBody final NewUserPayload pPayload ) {

        log.debug("create new user: {}",pPayload);
        return ResponseEntity.ok(usersService.createNewUser(pPayload.map()));
    }


}
