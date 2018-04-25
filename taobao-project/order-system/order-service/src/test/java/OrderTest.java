import com.example.taobao.OrderServiceApplication;
import com.example.taobao.order.common.dao.OrderMapper;
import com.example.taobao.order.common.entity.OrderDO;
import com.example.taobao.order.common.entity.OrderItemDO;
import com.example.taobao.order.common.util.SerializeUtil;
import com.example.taobao.order.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {OrderServiceApplication.class})
@RunWith(SpringRunner.class)
public class OrderTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SerializeUtil serializeUtil;

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void selectOrderTest() {
        OrderDO orderDO = orderMapper.getOrderById(1);
        orderDO.setDiscountAmount(5d);
        orderMapper.updateOrder(orderDO);
        List<OrderItemDO> orderItemDOList = orderDO.getOrderItems();
        System.out.println(orderItemDOList);
    }

    @Test
    public void redisTemplateTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus((short) 0);
        orderDTO.setOrderId(2);
        orderDTO.setCreateTime(new Date());
        orderDTO.setAggregateAmount(100D);
        byte[] orderByte = serializeUtil.toBytes(orderDTO);
        byte[] keyByte = serializeUtil.toBytes("rr--lihao");
        byte[] val2 = serializeUtil.toBytes("when  can  i see you");
        jedisCluster.sadd(keyByte, orderByte);
        jedisCluster.sadd(keyByte, val2);
        // redisTemplate.opsForValue().set("rrlihao","李浩");//        redisTemplate.opsForSet().add("rr","ddd");
        redisTemplate.opsForHash().put("rrlhlh","name","荣蓉");
        redisTemplate.opsForHash().put("rrlhlh","age","111");
//        redisTemplate.opsForHash().putAll("order:2", BeanMapper.mapBeanToStringMap(orderDTO));
        String name= (String) redisTemplate.opsForHash().get("rrlhlh","name");
        System.out.println("打印出姓名:"+name);


    }
}
