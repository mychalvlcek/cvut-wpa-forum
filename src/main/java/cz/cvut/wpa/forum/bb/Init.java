package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.CategoryService;
import cz.cvut.wpa.forum.service.MessageService;
import cz.cvut.wpa.forum.service.PostService;
import cz.cvut.wpa.forum.service.RoleService;
import cz.cvut.wpa.forum.service.RoleServiceImpl;
import cz.cvut.wpa.forum.service.TopicService;
import cz.cvut.wpa.forum.service.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Inicializace testovacích dat do db.
 * @author vlcekmi3
 */
@Component
@Scope(value="singleton")
public class Init {
    
    private boolean isInit;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected TopicService topicService;
    @Autowired
    protected PostService postService;
    @Autowired
    protected MessageService messageService;

    public Init() {
        this.isInit = false;
    }
    
    
    
    public void init() {
        // roles
        roleService.addRole("ROLE_ADMIN");
        roleService.addRole("ROLE_USER");
        // users
        userService.addUser("admin", "admin", "admin@admin.cz", true);
        userService.addUser("vlcekmi3", "vlcekmi3", "vlcekmi3@fel.cvut.cz", false);
        // categories
        categoryService.addCategory("The Newest Category");
        categoryService.addCategory("Title of category #1");
        categoryService.addCategory("Lorem Ipsum");
        categoryService.addCategory("Another Category");
        categoryService.addCategory("WPA category");
        categoryService.addCategory("Older category");
        categoryService.addCategory("Bootstrap starter template");
        categoryService.addCategory("The Oldest Category");
        // topics
        topicService.addTopic("1st Topic of forum", 4L, 5L);
        topicService.addTopic("Topic #[2]", 3L, 5L);
        // posts
        String content = "Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus. Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.";
        String content2 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        postService.addPost("1st Post title", content, 3L, 13L);
        postService.addPost("Heading #2", content2, 4L, 13L);
        postService.addPost("Heading #3", content2, 4L, 13L);
        postService.addPost("Heading #12313", content2, 4L, 14L);
        postService.addPost("Heading [123]", content2, 4L, 13L);
        postService.addPost("Heading 'XX'", content2, 4L, 13L);
        // messages
        messageService.addMessage("Předmět", content, 4L, 3L);
        messageService.addMessage("Předmět #2", content2, 4L, 3L);
        messageService.addMessage("Předmět", "Lorem Ipsum bla", 3L, 4L);
        
        topicService.addTopic("1st Topic of forum", 4L, 6L);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Testovací data nahrána."));
        this.isInit = true;
    }

    public boolean isIsInit() {
        return isInit;
    }

    public void setIsInit(boolean isInit) {
        this.isInit = isInit;
    }
    
}