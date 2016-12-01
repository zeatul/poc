package com.hawk.utility.example.socket.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeServer {

	private static Logger logger = LoggerFactory.getLogger(TimeServer.class);

	public static class MultiplexerTimeServer implements Runnable {

		private Selector selector;
		private ServerSocketChannel servChannel;
		private volatile boolean stop = false;

		public void stop() {
			this.stop = true;
		}

		public MultiplexerTimeServer(int port) {
			try {
				servChannel = ServerSocketChannel.open();
				servChannel.configureBlocking(false);
				servChannel.socket().bind(new InetSocketAddress(port), 1024);
				selector = Selector.open();
				servChannel.register(selector, SelectionKey.OP_ACCEPT);
				logger.info("The time server is listening on port {}", port);
			} catch (IOException e) {
				logger.error("failed to start time server", e);
				System.exit(1);
			}
		}

		@Override
		public void run() {
			while (!stop) {
				try {
					selector.select(1000);
					Set<SelectionKey> selectionKeys = selector.selectedKeys();
					Iterator<SelectionKey> it = selectionKeys.iterator();
					SelectionKey key = null;
					while (it.hasNext()) {
						key = it.next();
						it.remove();
						try {
							handleInput(key);
						} catch (Throwable ex) {
							// logger.error("meet error", ex);
							if (key != null) {
								key.cancel();
								if (key.channel() != null)
									key.channel().close();
							}
						}
					}
				} catch (IOException e) {
					logger.error("meet error", e);
				}
			}

			/**
			 * 多路复用器关闭，所有相关资源自动关闭
			 */
			if (selector != null) {
				try {
					selector.close();
				} catch (IOException e) {
					logger.error("close selector meet error", e);
				}
			}
		}

		private void handleInput(SelectionKey key) throws IOException {
			if (!key.isValid())
				return;

			if (key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}

			if (key.isReadable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if (readBytes > 0) {
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					logger.info("The time server receive order : {}", body);
					String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
					doWrite(sc, currentTime);
				} else if (readBytes < 0) {
					//对端链路关闭
					key.cancel();
					sc.close();
				} else {
					;
				}
			}
		}

		private void doWrite(SocketChannel channel, String response) throws IOException {
			if (response != null && response.trim().length() > 0) {
				try {
					byte[] bytes = response.getBytes("UTF-8");
					ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
					writeBuffer.put(bytes);
					writeBuffer.flip();
					channel.write(writeBuffer);
				} catch (UnsupportedEncodingException e) {
					logger.error("", e);
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

		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
	}

}
