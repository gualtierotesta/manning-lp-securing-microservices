package it.gualtierotesta.manning.liveproject.authserver.application.clients;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.ClientsServicePort;
import it.gualtierotesta.manning.liveproject.authserver.application.port.out.ClientsStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientsService implements ClientsServicePort {

    private final ClientsStoragePort storage;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Collection<Client> listAll() {
        return storage.listAll();
    }

    @Override
    public Client createNewClient(final Client pClient) {
        String encryptedSecret = passwordEncoder.encode(pClient.getSecret());
        return storage.create(pClient.withSecret(encryptedSecret));
    }
}
