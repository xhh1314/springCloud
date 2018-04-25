import com.example.taobao.OrderServiceApplication;
import com.example.taobao.order.common.dao.ScoreMapper;
import com.example.taobao.order.common.entity.ScoreDO;
import com.example.taobao.order.common.manage.ScoreMange;
import com.example.taobao.order.webservice.threadPool.OrderThreadPoolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {OrderServiceApplication.class})
@RunWith(SpringRunner.class)
public class ScoreTest {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private ScoreMange scoreMange;

    @Autowired
    private OrderThreadPoolService threadPoolService;

    @Test
    public void insertTest() throws InterruptedException {
        Random random = new Random();

        AtomicInteger atomicInteger = new AtomicInteger();
        CountDownLatch countDownLatch=new CountDownLatch(10);
        int n = 0;
        while (n++ < 10) {
            threadPoolService.executeTask(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    while (i++ < 100000) {
                        ScoreDO scoreDO = new ScoreDO();
                        scoreDO.setScore(random.nextInt(10000));
                        scoreDO.setStudentId(random.nextInt(1000000));
                        scoreMange.insertScore(scoreDO);
                        System.out.println("线程" + Thread.currentThread().getName() + "开始插入第:" + atomicInteger.addAndGet(1) + "条数据！");
                    }
                    countDownLatch.countDown();
                }
            });
        }

        System.out.println("----------------等待任务执行！------------------------------");
        countDownLatch.await();
        System.out.println("----------------任务执行完成！------------------------------");

    }
}
