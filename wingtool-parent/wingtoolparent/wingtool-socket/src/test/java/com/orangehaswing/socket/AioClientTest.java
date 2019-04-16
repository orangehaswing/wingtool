package com.orangehaswing.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import com.orangehaswing.core.lang.Console;
import com.orangehaswing.core.util.StrUtil;
import com.orangehaswing.socket.aio.AioClient;
import com.orangehaswing.socket.aio.AioSession;
import com.orangehaswing.socket.aio.SimpleIoAction;

public class AioClientTest {
	public static void main(String[] args) {
		AioClient client = new AioClient(new InetSocketAddress("localhost", 8899), new SimpleIoAction() {
			
			@Override
			public void doAction(AioSession session, ByteBuffer data) {
				if(data.hasRemaining()) {
					Console.log(StrUtil.utf8Str(data));
					session.read();
				}
				Console.log("OK");
			}
		});
		
		client.write(ByteBuffer.wrap("Hello".getBytes()));
		client.read();
		
		client.close();
	}
}
