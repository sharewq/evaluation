

import com.chinapopin.evaluate.bean.XtFwpjxxTemp;
import com.chinapopin.evaluate.dao.XtFwpjxxTempDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Think on 2017/7/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.chinapopin.evaluate.app.Application.class)
public class TestJdbc {
    @Resource
    XtFwpjxxTempDao xtFwpjxxTempDao;


    @Test
    public void TestJdbc() {
        System.out.println("start");
        List<XtFwpjxxTemp> list = xtFwpjxxTempDao.selectAll();
        System.out.println("end");
    }


}
