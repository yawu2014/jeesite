package com.thinkgem.jeesite.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
/**
 * 测试连接mongoDB数据库
 * 
 * @author liuyujian
 * @Date 2017年7月25日 下午5:17:10
 *
 */
public class MongoDBJDBC {

	public static void main(String[] args) {
		try{
			Document doc = new Document();
			MongoClient mongoClient = new MongoClient("10.1.2.163",27017);
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
			System.out.println("Connect Successfully!!");
		}catch(Exception e){
			e.printStackTrace();
			System.err.print(e.getClass().getName()+":"+e.getMessage());
		}
	}

}
