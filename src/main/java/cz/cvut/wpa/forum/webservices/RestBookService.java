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

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserDto getUserById(@PathParam("id") long id) {
        return userService.getUserById(id);
    }
    /*
    @GET
    @Path("/{minAge}/{maxAge}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Book> getBooksByOwnerAge(@PathParam("minAge") int min, @PathParam("maxAge") int max) {
        return bookService.getBooksByOwnerAge(min, max);
    }
    
    @GET
    @Path("/search/{title}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Book> getBooksByTitle(@PathParam("title") String title) {
        return bookService.getBooksByTitle(title);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addBook(@FormParam("title") String title, @FormParam("ownerUserName") String ownerUserName, @FormParam("ownerAge") int ownerAge) {
        User user = new User(9, ownerUserName, ownerAge);
        Book book = new Book(99, title, user);
        bookService.addBook(book);
        return Response.created(URI.create("/books/"+book.getId())).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addBook(Book book) {
        bookService.addBook(book);
        return Response.created(URI.create("/books/"+book.getId())).build();
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") long id) {
        bookService.removeBook(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateBook(@PathParam("id") long id, Book book) {
        bookService.updateBook(id, book);
        return Response.ok().build();
    }
    */
    
}