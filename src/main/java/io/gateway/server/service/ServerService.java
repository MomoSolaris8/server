package io.gateway.server.service;

import io.gateway.server.model.Server;

import java.io.IOException;
import java.util.Collection;

/**
 * @author momo
 * @version 1.0
 * @since 5/28/2022
 */
public interface ServerService {
    /**
     * Interface class that has the following methods.
     */
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);

}
