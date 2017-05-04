package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * ��ը��
 * @author ���´�
 
 */
public class Boom {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	/**
	 * ��ը��ͼ
	 */
	private static Image[] imgs = { // �洢��ըͼƬ ��С����ı�ըЧ��ͼ
		tk.getImage("0.gif"),
		tk.getImage("1.gif"),
		tk.getImage("2.gif"),
		tk.getImage("3.gif"),
		tk.getImage("4.gif"),
		tk.getImage("5.gif"),
		tk.getImage("6.gif"),
		tk.getImage("7.gif"),
		tk.getImage("8.gif"),
		};
	
	int step = 0;//��ը�Ĵ���
	
	/**
	 * ��ը��λ������
	 */
	private int x,y;
	/**
	 * ��ը�Ƿ����
	 */
	private boolean islive=true;
	/**
	 * ����TankClient�����ã�����ʹ�����еĳ�Ա����
	 */
	TankClient tc=null;
	/**
	 * �жϱ�ը�Ƿ����
	 * @return���ر�ը�������
	 */
	private boolean live() {
		return islive;
	}

	public Boom(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * ��ը�๹�캯��
	 */
	public Boom(int x,int y,TankClient tc){
		this(x,y);
		this.tc=tc;
	}
	/**
	 * ��ը�໭��
	 */
	public void draw(Graphics g){
		if(!live()){ 	//��ըû�˾ʹ��������Ƴ�
			tc.booms.remove(this);
			return;
		}
		
		if (step == imgs.length) {
			islive = false;
			step = 0;
			return;
		}

		g.drawImage(imgs[step], x, y, null);
		step++;
	}
	
}
