package cn.peixuan.jisuanqi;

import javax.swing.JTextField;

/**
 * @author Administrator
 * ÎÄ±¾ÊäÈë¿ò
 */
public class TextField extends JTextField {
	private int textValue;
	
	public TextField(int x, int y, int w, int h) {
		this.setBounds(x, y, w, h);
	}

	public int getTextValue() {
		return textValue;
	}

	public void setTextValue(int textValue) {
		this.textValue = textValue;
	}
	
}
