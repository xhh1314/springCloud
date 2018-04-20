import com.example.taobao.OrderServiceApplication;
import com.example.taobao.order.common.dao.OrderMapper;
import com.example.taobao.order.common.entity.OrderDO;
import com.example.taobao.order.common.entity.OrderItemDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK,classes = {OrderServiceApplication.class})
@RunWith(SpringRunner.class)
public class OrderTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void selectOrderTest(){
        OrderDO orderDO=orderMapper.getOrderById(1);
        orderDO.setDiscountAmount(5d);
        orderMapper.updateOrder(orderDO);
        List<OrderItemDO> orderItemDOList=orderDO.getOrderItems();
        System.out.println(orderItemDOList);
    }
}
