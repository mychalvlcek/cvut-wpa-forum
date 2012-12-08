package cz.cvut.wpa.forum.webservices;

import cz.cvut.wpa.forum.dto.UserDto;
import cz.cvut.wpa.forum.service.UserService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author vlcekmi3
 */
@Component
@Path("/a")
public class RestBookService {
    
    @Autowired
    private UserService userService;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello(@DefaultValue("no message") @QueryParam("msg") String msg) {
        return "hello: " + msg;
    }
    
}