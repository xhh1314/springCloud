package com.example.taobao.order.api;

import com.example.taobao.order.dto.OrderDTO;

/**
 * 测试有两个接口的情况下，dubbo 实例化情况
* @date 2018/7/21
* @author lh
*
*/
public interface OrderFindServiceTest {

    OrderDTO getOrderDetail(Long id);
}
