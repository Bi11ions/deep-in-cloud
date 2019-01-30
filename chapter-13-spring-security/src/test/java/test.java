import com.security.Application;
import com.security.bean.User;
import com.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author wangsen@qgutech.com
 * @since 2019/1/30 11:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class test {
    @Resource
    private UserService userService;

    @Test
    public void addUser() {
        User forezp = new User();
        forezp.setUsername("forezp");
        forezp.setPassword("123456");

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("123456");

        userService.insert(forezp);
        userService.insert(admin);
    }
}
