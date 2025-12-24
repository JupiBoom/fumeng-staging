package cn.xilikeli.staging;

import cn.xilikeli.staging.service.ReturnExchangeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * <p>
 * 退换货测试类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReturnExchangeTest {

    @Autowired
    private ReturnExchangeService returnExchangeService;

    @Test
    public void testGetReturnReasonStatistics() {
        Map<String, Long> statistics = returnExchangeService.getReturnReasonStatistics();
        System.out.println("退货原因统计分析结果:");
        for (Map.Entry<String, Long> entry : statistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}