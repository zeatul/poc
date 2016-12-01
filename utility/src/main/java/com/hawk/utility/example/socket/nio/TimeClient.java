package com.hawk.utility.example.socket.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClient {
	
	private static Logger logger = LoggerFactory.getLogger(TimeClient.class);
	
	public static class TimeClientHandle implements Runnable{
		
		private String host;
		private int port;
		private Selector selector;
		private SocketChannel socketChannel;
		private volatile boolean stop;
		
		public TimeClientHandle(String host,int port){
			this.host = host == null? "127.0.0.1":host;
			this.port = port;
			try {
				selector = Selector.open();
				socketChannel = SocketChannel.open();
				socketChannel.configureBlocking(false);
			} catch (IOException e) {
				logger.error("Failed to iniitiate TimeClientHandle",e);
				System.exit(1);
			}
		}
		

		@Override
		public void run() {
			try {
				doConnect();
			} catch (IOException e) {
				logger.error("Failed to connect to time server",e);
				System.exit(1);
			}
			
			while(!stop){
				try {
					selector.select(1000);
					Set<SelectionKey> selectedKeys = selector.selectedKeys();
					Iterator<SelectionKey> it = selectedKeys.iterator();
					SelectionKey key = null;
					while(it.hasNext()){
						key = it.next();
						it.remove();
						
						try {
							handleInput(key);
						} catch (Exception e) {
							if (key!=null){
								key.cancel();
								if (key.channel()!=null)
									key.channel().close();
							}
						}
						
					}
				} catch (IOException e) {
					logger.error("meet error",e);
					System.exit(1);
				}
				
			}
			
			/**
			 * 多路复用器关闭，所有相关资源自动关闭
			 */
			if (selector != null){
				try {
					selector.close();
				} catch (IOException e) {
					logger.error("meet error",e);
				}
			}
			
		}
		
		 private void doConnect() throws IOException{
			 if (socketChannel.connect(new InetSocketAddress(host, port))){
				 socketChannel.register(selector, SelectionKey.OP_READ);
				 doWrite(socketChannel);
			 }else{
				 socketChannel.register(selector, SelectionKey.OP_CONNECT);
			 }
		 }
		 
		 private void doWrite(SocketChannel sc) throws IOException {
			 try {
				byte[] req = "QUERY TIME ORDER".getBytes("UTF-8");
				ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
				writeBuffer.put(req);
				writeBuffer.flip();
				sc.write(writeBuffer);
				if (!writeBuffer.hasRemaining()){
					logger.info("Succeed to send order 2 server");
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("meet error",e);
			}
		 }
		 
		 private void handleInput(SelectionKey key) throws IOException{
			 if (!key.isValid())
				 return;
			 SocketChannel sc = (SocketChannel)key.channel();
			 if (key.isConnectable()){
				 if (sc.finishConnect()){
					 sc.register(selector, SelectionKey.OP_READ);
					 doWrite(sc);
				 }else{
					 logger.error("connect failed");
					 System.exit(1);
				 }
			 }
			 
			 if (key.isReadable()){
				 ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				 int readBytes = sc.read(readBuffer);
				 if (readBytes > 0){
					 readBuffer.flip();
					 byte[] bytes = new byte[readBuffer.remaining()];
					 readBuffer.get(bytes);
					 String body = new String(bytes,"utf-8");
					 logger.info("Receive message : {}",body);
					 this.stop=true;
				 }else if (readBytes<0){
					 /**
					  * 对端链路关闭
					  */
					 key.cancel();
					 sc.close();
				 }else{
					 ;
				 }
			 }
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
		
		new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001")
		.start();
	}
}
