import com.bes.common.logging.annotation.MethodLog;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * 模块测试
 * @author longquanxiao
 * @date 2019/8/12
 */
public class ModuleTest {

    class A {
        @MethodLog
        public String hello(String name){
            System.out.println("name");
            return "hello "+name;
        }
    }
    @Test
    public void testMethodLog(){
        A a = new A();
        a.hello("world");
    }
}
