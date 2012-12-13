package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.CategoryService;
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
        topicService.addTopic("1st Topic of forum", 3L, 5L);
        topicService.addTopic("1st Topic of forum", 3L, 5L);
        // posts
        String content = "Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus. Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.";
        postService.addPost("Post head", content, 3L, 13L);
        postService.addPost("Heading", content, 4L, 13L);
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