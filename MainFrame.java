package cn.peixuan.jisuanqi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainFrame extends JFrame {
	
	/**
	 * 文本框
	 */
	private TextField[] texts = new TextField[5];
	
	/**
	 * 标签
	 */
	private JLabel[] labels = new JLabel[5];
	
	/**
	 * 标签值
	 */
	private String[] labelTexts = {"存入本金:","年利率(%):","存入年限:","年复利次数:","复利终值:"};
	
	/**
	 * 确定按钮
	 */
	private JButton jbtOk = new JButton("确定");
	
	/**
	 * 退出按钮
	 */
	private JButton jbtExit = new JButton("退出");
	
	/**
	 * 利率获取时间的整数倍
	 */
	private double N;
	
	/**
	 * 复利终值
	 */
	private double F;
	
	/**
	 * 存入年限
	 */
	private double Y;
	
	/**
	 * 本金
	 */
	private double P;
	
	/**
	 * 利率
	 */
	private double R;
	
	/**
	 * 本金计算方式
	 */
	private boolean pFlag;
	
	/**
	 * 复利计算方式
	 */
	private boolean fFlag;
	
	/**
	 * 主窗体
	 */
	public MainFrame() {
		this.setTitle("复利计算器");
		this.setBoundAtCenter();
		this.setResizable(true);
		//this.setLayout(new VFlowLayout());
		this.add(this.createTextPanel(), BorderLayout.CENTER);
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * 居中窗体
	 */
	private void setBoundAtCenter() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		this.setSize(245, 350);
		int width = dim.width - this.getWidth() >> 1;
		int hight = (dim.height - this.getHeight() >> 1) - 32;
		this.setLocation(width, hight);
	}
	
	/**
	 * 初始化文本框与标签位置以及标签值
	 */
	private void initTextField() {
		int x = 90;
		int y = 50;
		int w = 130;
		int h = 20;
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new TextField(x, y, w, h);
			labels[i] = new JLabel(labelTexts[i]);
			labels[i].setBounds(x-80, y, w, h);
			y += 28;
		}
	}
	
	/**
	 * 创建面板存放TextField和label
	 */
	private JPanel createTextPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.initTextField();
		for (int i = 0; i < texts.length; i++) {
			panel.add(texts[i]);
			panel.add(labels[i]);
		}
		return panel;
	}
	
	/**
	 * 设置按钮以及监听
	 */
	private JPanel createButtonPanel(){
		
		JPanel btnPanel = new JPanel(new VFlowLayout());
		JPanel btnPanel1 = new JPanel(new FlowLayout());
		
		
		//----------设置jbtOk的监听------------------
		this.jbtOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!pFlag) {
					texts[0].setText(String.valueOf(calculatePrincipal()));
				}else {
					texts[4].setText(String.valueOf(calculateCInterest()));
				}
			}
			
		});
		
		btnPanel1.add(this.jbtOk);
		
		//----------设置jbtExit的监听------------------
		this.jbtExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(EXIT_ON_CLOSE);
				
			}
		});
		
		btnPanel1.add(this.jbtExit);
		//先添加ButtonGroup
		btnPanel.add(this.createButtonGroup());
		btnPanel.add(btnPanel1);
		return btnPanel;
	}
	
	/**
	 * 设置jradio按钮以及监听
	 */
	private JPanel createButtonGroup() {
		JPanel grpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton jrb1 = new JRadioButton("本金");
		//----------设置jrb1的监听------------------
		jrb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pFlag = false;
				fFlag = true;
				changeType();
			}
		});
		btnGrp.add(jrb1);
		JRadioButton jrb2 = new JRadioButton("复利");
		//----------设置jrb2的监听------------------
		jrb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pFlag = true;
				fFlag = false;
				changeType();
			}
		});
		btnGrp.add(jrb2);
		grpPanel.add(jrb1);
		grpPanel.add(jrb2);
		return grpPanel;
	}
	
	private void changeType() {
		this.texts[0].setEnabled(pFlag);
		this.texts[4].setEnabled(fFlag);
		if(pFlag) {
			this.texts[4].setText("由此得出数值");
			this.texts[0].setText(null);
		} else {
			this.texts[0].setText("由此得出数值");
			this.texts[4].setText(null);
		}
	}

	/**
	 * F=P*(1+i)N(次方)
	 * F：复利终值
	 * P：本金
	 * R：利率
	 * N：利率获取时间的整数倍
	 * Y: 存入年限
	 * 
	 * 计算本金
	 */
	private double calculatePrincipal(){
		this.R = Double.parseDouble(this.texts[1].getText().trim());
		this.Y = Double.parseDouble(this.texts[2].getText().trim());
		this.N = Double.parseDouble(this.texts[3].getText().trim());
		this.F = Double.parseDouble(this.texts[4].getText().trim());
		return F/Math.pow((1+R),N);
		
		
	}
	
	/**
	 * 计算复利
	 */
	private double calculateCInterest(){
		this.P = Double.parseDouble(this.texts[0].getText().trim());
		this.R = Double.parseDouble(this.texts[1].getText().trim());
		this.Y = Double.parseDouble(this.texts[2].getText().trim());
		this.N = Double.parseDouble(this.texts[3].getText().trim());
		return P*Math.pow((1+R),N);
	}
	
	
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
