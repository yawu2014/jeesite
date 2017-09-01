/**
 * 
 */
package com.thinkgem.jeesite.netty;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年8月31日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class TimeServerHandler implements Runnable {
	private Socket socket;
	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		BufferedReader in = null;
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());  
            String clientInputStr = input.readUTF();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException  
            // 处理客户端数据    
            System.out.println("客户端发过来的内容:" + clientInputStr);    
            // 向客户端回复信息    
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
            String currentTime = "QUERY TIME ORDER".equals(clientInputStr)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            out.writeUTF(currentTime);    
              
            out.close();    
            input.close();    
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(this.socket != null) {
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				this.socket = null;
			}
		}
	}

}
