package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
/**
 * �ӵ���
 * @author ���´�
 */
public class Bullet {
	/**
	 * �������
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	/**
	 * �ҷ�̹���ӵ�ͼƬ����
	 */
	private static Image[] bulletImg1 = null; 
	static {
		bulletImg1 = new Image[] {
				tk.getImage("bullet.png"),
				tk.getImage("bullet_d.png"),
				tk.getImage("bullet_l.png"),
				tk.getImage("bullet_r.png"),
								
		};
	}
	/**
	 * �з�̹���ӵ�ͼƬ����
	 */
	private static Image[] bulletImg2 = null; 
	static {
		bulletImg2 = new Image[] {
				tk.getImage("bulletU.gif"),
				tk.getImage("bulletD.gif"),
				tk.getImage("bulletL.gif"),
				tk.getImage("bulletR.gif"),		
		};
	}
	
	/**
	 * �ӵ���ˮƽ�ƶ��ٶ�
	 */
	public static  int  XSPEED=20; // final private �ɷ�ֹ�޸�����  Ҳ����дһ���������ٶ��޸�
	/**
	 * �ӵ�����ֱ�ٶ�
	 */
	public static  int YSPEED=20;
	/**
	 * �ӵ��Ŀ��
	 */
	public static  int WIDTH=10;//�ӵ��ĳߴ�ɱ� 
	/**
	 * �ӵ��ĸ߶�
	 */
	public static  int HEIGHT=10;
	/**
	 * �ӵ��ĺ�����
	 */
	private int x;
	/**
	 * �ӵ���������
	 */
	private int y;
	/**
	 * �ӵ��Ƿ����
	 */
	private boolean islive=true;
	/**
	 * �ж���˭���ӵ�
	 */
	private boolean ismine=true;
	/**
	 * ����TankClient�����ã�����ʹ�����еĳ�Ա����
	 */
	private TankClient tc=null;
	/**
	 * �ӵ��ķ���  ��̹�˵ĳ���һ��
	 */
	private Tank.Direction Dir;
	/**
	 * �ж��ӵ�������
	 * @return�����ӵ�������
	 */
	public boolean live(){
		return islive;
	}
	
	public  Bullet(int x,int y,Tank.Direction Dir,TankClient tc){
		this.x=x;
		this.y=y;
		this.Dir=Dir;
		this.tc=tc;
	}
	/**
	 * �ӵ���Ĺ��캯��
	 * @param x
	 * @param y
	 * @param Dir
	 * @param tc
	 * @param ismine
	 */
	public  Bullet(int x,int y,Tank.Direction Dir,TankClient tc,boolean ismine){
		this.x=x;
		this.y=y;
		this.Dir=Dir;
		this.tc=tc;
		this.ismine=ismine;
	}
	/**
	 * �ӵ���Ļ���
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live()){ //�ӵ����˾ʹ��������Ƴ�
			tc.bullets.remove(this);
			return;
		}
		Color c=g.getColor();
		if(islive){
			if(!this.ismine){
				switch (this.Dir) {  //���ݷ���ѡ���ӵ���ͼƬ
				case U:
					g.drawImage(bulletImg2[0], x-2, y-5, null);
					break;
				case D:
					g.drawImage(bulletImg2[1], x-4, y+8, null);
					break;
				case L:
					g.drawImage(bulletImg2[2], x-2, y, null);
					break;
				case R:
					g.drawImage(bulletImg2[3], x, y+2, null);
					break;	
				}
			}
			else {
				g.setColor(Color.white);
				
				switch (this.Dir) {  //���ݷ���ѡ���ӵ���ͼƬ
				case U:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;
				case D:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;
				case L:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;
				case R:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;	
					
				}
				}
			}
		else
			g.dispose();
		
			g.setColor(c);
		move();
	}
	
	/**
	 * �õ��ӵ���Χ�����ɵľ���
	 * @return �ӵ���Χ�����ɵľ���
	 */
	public Rectangle getrect(){
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	/**
	 * ������̹��
	 * @param tanks
	 * @return
	 */
	
	public boolean hitTanks(ArrayList<Tank> tanks){
		for(int i=0;i<tanks.size();i++){
			if(hitTank(tanks.get(i)))
				return true;
		}
		return false;
	}
	
	/**
	 * ���һ��̹��
	 * @param t
	 * @return
	 */
	public boolean hitTank(Tank t){
		if(this.islive&&this.getrect().intersects(t.getrect())&&t.live()&&this.ismine!=t.mine()){
			Boom n=new Boom(t.getx(),t.gety(),tc);
			tc.booms.add(n);
			if(t.mine()){
				t.dislife();//����ҷ�̹�˱����� ÿ���ҷ�̹��������һ
				if(t.getlife()<=0){
					t.setlive(false);
				}
			}
			else {
				t.setlive(false);
				tc.score+=10;//������10
				tc.count+=1;//ɱ������1
				tc.tanks.remove(t);
			}
			this.islive=false;
		//	System.out.println(666);
			return true;
		}
			return false;
	}
	
	
	/**
	 * ����ӵ���ǽ��ʧ
	 * @param wһ��ǽ
	 * @return true false(���Բ�Ҫ����ֵ)
	 */
	public boolean hitWall(Wall w) {
		if(this.islive && this.getrect().intersects(w.getRect())) {
			this.islive = false;
			this.tc.Wall1.remove(w); // �ӵ��򵽹���ǽ��ʱ���Ƴ������е�ǽ
			return true;
		}
		return false;
	}
	
	public boolean hitWall(Wall2 w) { // �ӵ��򵽽���ǽ��
		if (this.islive && this.getrect().intersects(w.getRect())) {
			this.islive = false;//�ӵ����ܴ�Խ����ǽ
			//this.tc.Wall2.remove(w); 
			return true;
		}
		return false;
	}
	//�ӵ��򵽼�
	public boolean hitHome() { // ���ӵ��򵽼�ʱ
		if (this.islive && this.getrect().intersects(tc.home.getRect())) {
			this.islive = false;
			this.tc.home.setLive(false); // ���ҽ���һǹʱ������
			tc.flag=0;
			return true;
		}
		return false;
	}


	
	/**
	 * �ӵ����ƶ�����
	 */
	public void move(){
		/*
		 * ����ӵ�Խ�� ������ʧ
		 */
		if (x<0||y<0||x>TankClient.GAME_WIDTH||y>TankClient.GAME_HEIGHT) 
				this.islive=false;
				switch(Dir) {   //����
				case L:   { x-=XSPEED; break; }  
				case U:   { y-=YSPEED; break;} 
				case R:   { x+=XSPEED; break;}  
				case D:   { y+=YSPEED; break;}    
				case STOP:  break;
					}
			}

	
}
