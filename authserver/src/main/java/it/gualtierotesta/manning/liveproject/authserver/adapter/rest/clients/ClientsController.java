package it.gualtierotesta.manning.liveproject.authserver.adapter.rest.clients;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.ClientsServicePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientsController {

    private final ClientsServicePort service;

    @GetMapping("")
    public ResponseEntity<Collection<Client>> listAllUsers() {
        log.debug("list all clients");
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping("")
    public ResponseEntity<Client> addClient(
        @RequestBody final NewClientPayload pPayload) {

        log.debug("create new OAuth client: {}", pPayload);
        return ResponseEntity.ok(service.createNewClient(pPayload.map()));
    }


}
