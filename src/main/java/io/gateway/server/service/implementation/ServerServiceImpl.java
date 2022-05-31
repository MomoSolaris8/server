package io.gateway.server.service.implementation;

import io.gateway.server.enumeration.Status;
import io.gateway.server.model.Server;
import io.gateway.server.repo.ServerRepo;
import io.gateway.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import static io.gateway.server.enumeration.Status.SERVER_DOWN;
import static io.gateway.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

/**
 * @author momo
 * when rhe application is complex it becomes really hard to use gpa because
 * we want to do lot more complex opations in the data using sql because we`re
 * trying to do these oprtions then we're kind of licke forced to use sql queries and
 * then pass them into java and then we habe to application execute those queries instead
 * but for now this is gonna do  is gonna do jpa is actually very powerful
 */


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepo serverRepo;

    public Server create(Server server) {
        log.info("Saving new Server:{}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging  Server IP:{}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        // since we edit the status of the server then we have to save the server in a database with
        // the new status , and the second method is using java.net package they have really nice features
        serverRepo.save(server);
        return null;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all  Servers ");
        // I want to just limit the number of servers that i get back when i try to
        //we transform this into a list by just adding that to list to it
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {} ", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating  Server:{}", server.getName());
        // not update the Image only the Servername
        // server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting Server by UD:{}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        return null;
    }
}
