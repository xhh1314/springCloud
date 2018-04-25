import com.example.taobao.InventoryApplicationService;
import com.example.taobao.inventory.common.entity.LogisticsDO;
import com.example.taobao.inventory.webservice.service.LogisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { InventoryApplicationService.class })
@RunWith(SpringRunner.class)
public class LogisticsTest {

	@Autowired
	private LogisticsService logisticsService;

	@Test
	public void insertTest() {
		LogisticsDO logisticsDO = new LogisticsDO();
		logisticsDO.setProductId(1);
		logisticsDO.setOrderId(1);
		logisticsDO.setCreateTime(new Date());
		logisticsService.saveLogistics(logisticsDO);
	}
}
