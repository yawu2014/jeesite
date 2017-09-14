/**
 * 
 */
package com.thinkgem.jeesite.network;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月11日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class HttpClientTest {
	@Test
	public void testData() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			for(int i=0;i<30;i++) {
				cal.add(Calendar.DATE, -1);
				String date = sdf.format(cal.getTime());
				Random random = new Random();
				int scala = random.nextInt(20);
				int systolic = 100+ scala;
				int diastolic = 60+scala;
				String url = "http://10.1.1.19/app/health/bloodpressure/add?uid=42583"+"&orga_id=1"+"&systolic="+systolic+"&diastolic="+diastolic+"&heart_rate=80&meas_date"+date+"&content=mock";
				
				HttpGet httpGet = new HttpGet(url);
				CloseableHttpResponse response = httpClient.execute(httpGet);
				if(response.getStatusLine().getStatusCode() == 200) {
					System.out.println("success");
				}else {
					System.out.println(url);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
