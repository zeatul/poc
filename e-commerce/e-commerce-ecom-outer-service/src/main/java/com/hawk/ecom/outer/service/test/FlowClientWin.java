package com.flow.client.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.flow.client.OrderClient;
import com.flow.client.OrderResponse;
import com.flow.client.StringUtil;
import java.awt.Font;

public class FlowClientWin {

	private JFrame frame;
	private JTextField txtChannelCode;
	private JTextField txtProdCode;
	private JTextField txtOrderNo;
	private JTextField txtAccKey;
	private JTextArea txtInfo = new JTextArea();
	private JCheckBox chkMock = new JCheckBox("模拟测试");
	private JComboBox listUrl = new JComboBox();
	private JTextField txtPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlowClientWin window = new FlowClientWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FlowClientWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {

		}
		frame = new JFrame();
		frame.setTitle("测试客户端");
		frame.setBounds(100, 100, 712, 627);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("服务地址");
		label.setBounds(52, 57, 54, 15);
		frame.getContentPane().add(label);

		listUrl.setModel(new DefaultComboBoxModel(
				new String[] { "localhost:8080", "120.76.98.114:8080", "120.24.175.91:8080" }));
		listUrl.setBounds(134, 54, 116, 21);
		frame.getContentPane().add(listUrl);

		JLabel label_1 = new JLabel("渠道代码");
		label_1.setBounds(52, 94, 54, 15);
		frame.getContentPane().add(label_1);

		txtChannelCode = new JTextField();
		txtChannelCode.setText("tb");
		txtChannelCode.setBounds(133, 91, 110, 21);
		frame.getContentPane().add(txtChannelCode);
		txtChannelCode.setColumns(10);

		JLabel label_2 = new JLabel("产品代码");
		label_2.setBounds(52, 122, 54, 15);
		frame.getContentPane().add(label_2);

		txtProdCode = new JTextField();
		txtProdCode.setText("10001");
		txtProdCode.setColumns(10);
		txtProdCode.setBounds(133, 119, 110, 21);
		frame.getContentPane().add(txtProdCode);

		JLabel label_3 = new JLabel("订单号");
		label_3.setBounds(52, 151, 54, 15);
		frame.getContentPane().add(label_3);

		txtOrderNo = new JTextField();
		txtOrderNo.setColumns(10);
		txtOrderNo.setBounds(133, 148, 236, 21);
		frame.getContentPane().add(txtOrderNo);

		JLabel lblNewLabel = new JLabel("访问Key");
		lblNewLabel.setBounds(291, 94, 54, 15);
		frame.getContentPane().add(lblNewLabel);

		txtAccKey = new JTextField();
		txtAccKey.setText("hello");
		txtAccKey.setColumns(10);
		txtAccKey.setBounds(362, 91, 110, 21);
		frame.getContentPane().add(txtAccKey);

		JButton btnMakeOrderNo = new JButton("生成随机");
		btnMakeOrderNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtOrderNo.setText(UUID.randomUUID().toString());
			}
		});
		btnMakeOrderNo.setBounds(379, 147, 93, 23);
		frame.getContentPane().add(btnMakeOrderNo);

		chkMock.setBounds(134, 175, 103, 23);
		frame.getContentPane().add(chkMock);

		JButton btnOrder = new JButton("购买");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String productCode = txtProdCode.getText().trim();
				String channelCode = txtChannelCode.getText().trim();
				String accKey = txtAccKey.getText().trim();
				String phone = txtPhone.getText().trim();
				boolean isMock = chkMock.isSelected();
				String orderNo = txtOrderNo.getText().trim();

				if (StringUtil.isNullOrEmpty(phone)) {
					txtPhone.requestFocus();
					return;
				}
				if (phone.length() != 11) {
					txtPhone.requestFocus();
					return;
				}

				try {
					Long.parseLong(phone);
				} catch (Exception ex) {
					txtPhone.requestFocus();
					txtPhone.selectAll();
					return;
				}

				if (StringUtil.isNullOrEmpty(channelCode)) {
					txtChannelCode.requestFocus();
					return;
				}
				if (StringUtil.isNullOrEmpty(accKey)) {
					txtAccKey.requestFocus();
					return;
				}
				if (StringUtil.isNullOrEmpty(productCode)) {
					txtProdCode.requestFocus();
					return;
				}

				if (StringUtil.isNullOrEmpty(orderNo)) {
					txtOrderNo.requestFocus();
					return;
				}

				String host = StringUtil.substringBefore(listUrl.getSelectedItem().toString(), ":");
				int port = Integer.valueOf(StringUtil.substringAfter(listUrl.getSelectedItem().toString(), ":"));

				OrderClient client = new OrderClient(host, port);
				try {
					OrderResponse resp = client.order(channelCode, accKey, phone, productCode, orderNo, null, isMock);
					appendInfo("购买 状态码：" + resp.getResultCode() + ",客户订单号：" + resp.getClientOrderNo() + ",平台订单号："
							+ resp.getOrderNo());
				} catch (Exception ex) {
					appendInfo("ERROR-" + ex.getMessage());
				}
			}
		});
		btnOrder.setBounds(134, 214, 93, 23);
		frame.getContentPane().add(btnOrder);

		JButton btnQuery = new JButton("查询");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String channelCode = txtChannelCode.getText().trim();
				String accKey = txtAccKey.getText().trim();

				String orderNo = txtOrderNo.getText().trim();

				if (StringUtil.isNullOrEmpty(channelCode)) {
					txtChannelCode.requestFocus();
					return;
				}
				if (StringUtil.isNullOrEmpty(accKey)) {
					txtAccKey.requestFocus();
					return;
				}

				if (StringUtil.isNullOrEmpty(orderNo)) {
					txtOrderNo.requestFocus();
					return;
				}

				String host = StringUtil.substringBefore(listUrl.getSelectedItem().toString(), ":");
				int port = Integer.valueOf(StringUtil.substringAfter(listUrl.getSelectedItem().toString(), ":"));

				OrderClient client = new OrderClient(host, port);
				try {
					OrderResponse resp = client.queryOrder(channelCode, accKey, orderNo, null);
					appendInfo("查询 状态码：" + resp.getResultCode() + ",客户订单号：" + resp.getClientOrderNo());
				} catch (Exception ex) {
					appendInfo("ERROR-" + ex.getMessage());
				}
			}
		});
		btnQuery.setBounds(249, 214, 93, 23);
		frame.getContentPane().add(btnQuery);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 330, 676, 218);
		frame.getContentPane().add(scrollPane);
		txtInfo.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtInfo.setEditable(false);

		txtInfo.setRows(10);
		scrollPane.setViewportView(txtInfo);

		JButton btnClear = new JButton("清除");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInfo.setText("");
			}
		});
		btnClear.setBounds(548, 295, 93, 23);
		frame.getContentPane().add(btnClear);

		JLabel label_4 = new JLabel("电话号码");
		label_4.setBounds(291, 57, 54, 15);
		frame.getContentPane().add(label_4);

		txtPhone = new JTextField();
		txtPhone.setBounds(362, 54, 110, 21);
		frame.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
	}

	private void appendInfo(String info) {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		String ds = df.format(now);
		if (txtInfo.getText().length() == 0) {
			txtInfo.setText(ds + ":" + info);
		} else {
			txtInfo.append("\n" + ds + ":" + info);
		}
	}
}
