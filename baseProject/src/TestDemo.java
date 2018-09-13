import cn.itheima.service.UserService;
import cn.itheima.service.userServiceImp.UserServiceImp;
import org.junit.Test;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-07 11:38
 **/
public class TestDemo {
    @Test
    public void test(){
        UserService service=new UserServiceImp();
        service.delete("李富贵");
    }
}
