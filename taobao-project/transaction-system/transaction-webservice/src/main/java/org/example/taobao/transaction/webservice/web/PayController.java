package org.example.taobao.transaction.webservice.web;

import com.example.base.rest.Rest;
import org.example.taobao.transaction.webservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pay")
public class PayController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/paySuccess")
    public Rest transaction(Integer orderId){
        transactionService.transaction(orderId);
        return Rest.success();
    }
}
