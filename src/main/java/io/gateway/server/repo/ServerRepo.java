package io.gateway.server.repo;

import io.gateway.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author momo
 * @version 1.0
 * @since 5/29/2022
 */

public interface ServerRepo extends JpaRepository<Server,Long> {

     Server findByIpAddress(String ipAddress);

     //Server findByName(String name);
}
