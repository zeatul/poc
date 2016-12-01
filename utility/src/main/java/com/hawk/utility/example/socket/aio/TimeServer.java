package com.hawk.utility.example.socket.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeServer {
	
	private final static Logger logger = LoggerFactory.getLogger(TimeServer.class);
	
	public static class AsyncTimeServerHandler implements Runnable{
		
		private int port;
		CountDownLatch latch;
		AsynchronousServerSocketChannel asynchronousServerSocketChannel;
		
		public AsyncTimeServerHandler(int port){
			this.port = port;
			try {
				asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
				asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
				logger.info("The time server is listening in port :{}",this.port);
			} catch (IOException e) {
				logger.error("Meet error",e);
				System.exit(1);
			}
		}

		@Override
		public void run() {
			latch = new CountDownLatch(1);
			doAccept();
			try{
				latch.await();
			}catch (InterruptedException e) {
			    logger.error("meet error",e);
			}
		}
		
		public void doAccept(){
			asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
		}
		
	}
	
	public static class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncTimeServerHandler >{

		@Override
		public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
			attachment.asynchronousServerSocketChannel.accept(attachment, this);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			result.read(buffer, buffer, new ReadCompletionHandler(result) );
		}

		@Override
		public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
			logger.error("AcceptCompletionHandler meet error",exc);
			attachment.latch.countDown();
		}

	
		
	}
	
	public static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
		
		private AsynchronousSocketChannel channel;
		
		public ReadCompletionHandler(AsynchronousSocketChannel channel){
			if (this.channel == null){
				this.channel = channel;
			}
		}

		@Override
		public void completed(Integer result, ByteBuffer attachment) {
			attachment.flip();
			byte[] body = new byte[attachment.remaining()];
			attachment.get(body);
			
			try {
				String req = new String(body,"UTF-8");
				logger.info("The time server receive order : {}",req);
				String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
				doWrite(currentTime);
			} catch (UnsupportedEncodingException e) {
				logger.error("meet error",e);
			}
			
		}

		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			// TODO Auto-generated method stub
			
		}
		
		private void doWrite(String currentTime){
			if (currentTime == null || currentTime.trim().length() == 0)
				return;
			
			try {
				byte[] bytes = currentTime.getBytes("UTF-8");
				ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
				writeBuffer.put(bytes);
				writeBuffer.flip();
				channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

					@Override
					public void completed(Integer result, ByteBuffer buffer) {
						if (buffer.hasRemaining()){
							channel.write(buffer, buffer, this);
						}
						
					}

					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
						logger.error("meet error",exc);
						try {
							channel.close();
						} catch (IOException e) {
							
						}
					}
				});
			} catch (UnsupportedEncodingException e) {
				logger.error("meet error",e);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		int port = 8080;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}
		}
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
	}
	
	
   

}
