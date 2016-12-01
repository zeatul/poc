package com.hawk.utility.example.socket.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClient {
	
	
	public static class AsyncTimeClientHandler implements Runnable,CompletionHandler<Void,AsyncTimeClientHandler>{
		
		private Logger logger = LoggerFactory.getLogger(TimeClient.class);
		
		private AsynchronousSocketChannel client;
		private String host;
		private int port;
		private CountDownLatch latch;
		
		public AsyncTimeClientHandler(String host, int port){
			this.host = host;
			this.port = port;
			
			try {
				client = AsynchronousSocketChannel.open();
			} catch (IOException e) {
				logger.error("meet error",e);
			}
		}

		@Override
		public void run() {
			latch = new CountDownLatch(1);
			
			client.connect(new InetSocketAddress(host, port), this,this);
			
			try{
				latch.await();
			}catch (InterruptedException e){
				logger.error("meet error",e);
			}
			
		}

		@Override
		public void completed(Void result, AsyncTimeClientHandler attachment) {
			byte[] req = "QUERY TIME ORDER".getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
			writeBuffer.put(req);
			writeBuffer.flip();
			client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer,ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer buffer) {
					if (buffer.hasRemaining()){
						client.write(buffer, buffer, this);
					}else{
						ByteBuffer readBuffer = ByteBuffer.allocate(1024);
						client.read(readBuffer,readBuffer,new CompletionHandler<Integer,ByteBuffer>() {

							@Override
							public void completed(Integer result, ByteBuffer buffer) {
								buffer.flip();
								byte[] bytes = new byte[buffer.remaining()];
								buffer.get(bytes);
								try {
									String body = new String(bytes,"UTF-8");
									logger.info("Receive message : {}",body);
									latch.countDown();
								} catch (UnsupportedEncodingException e) {
									logger.error("meet error",e);
								}
							}

							@Override
							public void failed(Throwable exc, ByteBuffer attachment) {
								try {
									client.close();
								} catch (IOException e) {
									logger.error("meet error",e);
								}finally{
									latch.countDown();
								}
								
							}
						});
					}
					
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					// TODO Auto-generated method stub
					
				}
			});
		}

		@Override
		public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}

		}
		
		new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-001").start();
	}

}
