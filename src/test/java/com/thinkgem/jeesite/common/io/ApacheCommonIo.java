/**
 * 
 */
package com.thinkgem.jeesite.common.io;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * <p>
 * 使用Apache Common-io示例
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年8月29日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class ApacheCommonIo {
	@Test
	public void testCommonIo() {
		File file = new File("E:\\workplace\\晶奇\\address.txt");
		File outFile = new File("E:\\workplace\\晶奇\\addressout.txt");
		try {
			String address = FileUtils.readFileToString(file);
			JSONArray array = (JSONArray)JSONArray.parse(address);
			for(int i=0;i<array.size();i++){
				JSONObject obj = array.getJSONObject(i);
//				PrintStream print = new PrintStream("E:\\workplace\\晶奇\\addressout.txt");
//				System.setOut(print);
				String str = String.format("insert into areacode(sid,sname,ssuperid,ilevel) values('%s','%s','%s','%s')\n",obj.getString("sid"),obj.getString("sname"),obj.getString("ssuperid"),obj.getString("ilevel"));
				FileUtils.write(outFile,str,true);
			}


		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
