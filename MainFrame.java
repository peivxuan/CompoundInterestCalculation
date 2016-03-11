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
	 * �ı���
	 */
	private TextField[] texts = new TextField[5];
	
	/**
	 * ��ǩ
	 */
	private JLabel[] labels = new JLabel[5];
	
	/**
	 * ��ǩֵ
	 */
	private String[] labelTexts = {"���뱾��:","������(%):","��������:","�긴������:","������ֵ:"};
	
	/**
	 * ȷ����ť
	 */
	private JButton jbtOk = new JButton("ȷ��");
	
	/**
	 * �˳���ť
	 */
	private JButton jbtExit = new JButton("�˳�");
	
	/**
	 * ���ʻ�ȡʱ���������
	 */
	private double N;
	
	/**
	 * ������ֵ
	 */
	private double F;
	
	/**
	 * ��������
	 */
	private double Y;
	
	/**
	 * ����
	 */
	private double P;
	
	/**
	 * ����
	 */
	private double R;
	
	/**
	 * ������㷽ʽ
	 */
	private boolean pFlag;
	
	/**
	 * �������㷽ʽ
	 */
	private boolean fFlag;
	
	/**
	 * ������
	 */
	public MainFrame() {
		this.setTitle("����������");
		this.setBoundAtCenter();
		this.setResizable(true);
		//this.setLayout(new VFlowLayout());
		this.add(this.createTextPanel(), BorderLayout.CENTER);
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * ���д���
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
	 * ��ʼ���ı������ǩλ���Լ���ǩֵ
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
	 * ���������TextField��label
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
	 * ���ð�ť�Լ�����
	 */
	private JPanel createButtonPanel(){
		
		JPanel btnPanel = new JPanel(new VFlowLayout());
		JPanel btnPanel1 = new JPanel(new FlowLayout());
		
		
		//----------����jbtOk�ļ���------------------
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
		
		//----------����jbtExit�ļ���------------------
		this.jbtExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(EXIT_ON_CLOSE);
				
			}
		});
		
		btnPanel1.add(this.jbtExit);
		//�����ButtonGroup
		btnPanel.add(this.createButtonGroup());
		btnPanel.add(btnPanel1);
		return btnPanel;
	}
	
	/**
	 * ����jradio��ť�Լ�����
	 */
	private JPanel createButtonGroup() {
		JPanel grpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton jrb1 = new JRadioButton("����");
		//----------����jrb1�ļ���------------------
		jrb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pFlag = false;
				fFlag = true;
				changeType();
			}
		});
		btnGrp.add(jrb1);
		JRadioButton jrb2 = new JRadioButton("����");
		//----------����jrb2�ļ���------------------
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
			this.texts[4].setText("�ɴ˵ó���ֵ");
			this.texts[0].setText(null);
		} else {
			this.texts[0].setText("�ɴ˵ó���ֵ");
			this.texts[4].setText(null);
		}
	}

	/**
	 * F=P*(1+i)N(�η�)
	 * F��������ֵ
	 * P������
	 * R������
	 * N�����ʻ�ȡʱ���������
	 * Y: ��������
	 * 
	 * ���㱾��
	 */
	private double calculatePrincipal(){
		this.R = Double.parseDouble(this.texts[1].getText().trim());
		this.Y = Double.parseDouble(this.texts[2].getText().trim());
		this.N = Double.parseDouble(this.texts[3].getText().trim());
		this.F = Double.parseDouble(this.texts[4].getText().trim());
		return F/Math.pow((1+R),N);
		
		
	}
	
	/**
	 * ���㸴��
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
