package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * ̹����
 * @author ���´�
 *����̹�˵�һЩ���Լ�����
 */
public class Tank {
	/**
	 * �������
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	/**
	 * �ҷ�̹��ͼƬ����
	 */
	private static Image[] tankImags1 = null; 
	static {
		tankImags1 = new Image[] {
				tk.getImage("myTank.png"),
				tk.getImage("myTank_down.png"),
				tk.getImage("myTank_l.png"),
				tk.getImage("myTank_r.png"),
					
		};
	}
	/**
	 * �з�̹��ͼƬ����
	 */
	private static Image[] tankImags2 = null; 
	static {
		tankImags2 = new Image[] {
						tk.getImage("etank_u.png"),
						tk.getImage("etank_d.png"),
						tk.getImage("Etank_l.png"),
						tk.getImage("etank_r.png"),
						
		};
	}
	/**
	 * �����ҷ�̹�˵�mytflag
	 * 0 Ϊ��һ��̹�� 1Ϊ�ڶ���̹��
	 */
	public int mytflag;
	/**
	 * ̹�˵�ˮƽ�ƶ��ٶ�
	 */
	public static final int XSPEED=10;
	/**
	 * ̹�˵���ֱ�ƶ��ٶ�
	 */
	public static final int YSPEED=10;
	/**
	 * ̹�˵Ŀ�
	 */
	public static final int WIDTH=32;
	/**
	 * ̹�˵ĸ�
	 */
	public static final int HEIGHT=32;
	/**
	 * ̹�˵�����ֵ
	 */
	private int life=5;
	
	/**
	 * ��Ͳ���� Ĭ��Ϊ��
	 */
	private Direction ptDir = Direction.U;
	
	/**
	 * ����TankClient�����ã�����ʹ�����еĳ�Ա����
	 */
	TankClient tc=null;
	/**
	 * �ж��Ƿ������ĸ������ 
	 */
	private boolean bU=false,bD=false,bL=false,bR=false;
	/**
	 * ̹�˵ķ��� ����˸������ƶ�
	 * @author ���´�
	 *
	 */
	enum Direction{U,D,L,R,STOP};
	
	private Direction Dir=Direction.STOP;//Ĭ��̹����ͣ�ŵ�
	/**
	 * ̹�˵�λ������
	 */
	private int x,y;
	/**
	 * ̹�˵���һ��λ��
	 */
	private int oldx,oldy;
	/**
	 * �ж��Ƿ�Ϊ�ҷ�̹��
	 */
	private boolean ismine;
	/**
	 * �ж�̹���Ƿ����
	 */
	private boolean islive=true;
	/**
	 * ��������
	 */
	private static Random r=new Random();
	
	private int flag=r.nextInt(12)+3;//ÿ�θ�ֵ
	/**
	 * ���ҷ�̹�˱����� ��������ֵ
	 */
	public void dislife(){
		life-=1;
	}
	/**
	 * �����ҷ�̹������ֵ
	 * @return�ҷ�̹������ֵ
	 */
	public int getlife(){
		return life;
	}
	/**
	 * ��������
	 * @param live
	 */
	public void setlive(boolean live){
		this.islive=live;
	}
	/**
	 * �ж��Ƿ�Ϊ�ҷ�̹��
	 * @return
	 */
	public boolean mine(){
		return ismine;
	}
	/**
	 * �ж�̹���Ƿ����
	 * @return
	 */
	public boolean live(){
		return islive;
	}
	/**
	 * �õ�̹�˵�x����
	 * @return
	 */
	public int getx(){
		return this.x;
	}
	/**
	 * �õ�̹�˵�y����
	 * @return
	 */
	public int gety(){
		return this.y;
	}
	/**
	 * �����ҷ�̹������ʱ��λ�ü�����
	 * @param x
	 * @param y
	 */
	public void setlocation(int x,int y){
		this.x=x;
		this.y=y;
		this.Dir=this.Dir;
	}
	
	/**
	 * ̹���๹�췽��
	 */
	public Tank(int x, int y, TankClient tc,boolean ismine) { //����ǰTankClirnt�е�tc ����Tank�е�tc����  
		this.x=x;
		this.y=y;
		this.ismine=ismine;
		this.oldx = x;
		this.oldy = y;  
		this.tc = tc;
	}
	
	/**
	 * ̹���๹�췽������
	 */
	public Tank(int x, int y, TankClient tc,boolean ismine,int flag) { //����ǰTankClirnt�е�tc ����Tank�е�tc����  
		this.x=x;
		this.y=y;
		this.ismine=ismine;
		this.oldx = x;
		this.oldy = y;  
		this.tc = tc;
		this.mytflag=flag;
	}
	
	/**
	 * ̹�˻���
	 * @param g
	 */
	public void draw(Graphics g){
		if(live()){ //���ж�̹���Ƿ����
			oldx=x;oldy=y;
			Color c=g.getColor();
			if(this.ismine) //�з����ҷ�̹����ɫ�ֿ�����
				switch (ptDir) {  //���ݷ���ѡ��̹�˵�ͼƬ
					case U:
						g.drawImage(tankImags2[0], x, y, null);
						break;
					case D:
						g.drawImage(tankImags2[1], x, y, null);
						break;
					case L:
						g.drawImage(tankImags2[2], x, y, null);
						break;
					case R:
						g.drawImage(tankImags2[3], x, y, null);
						break;	
					}
			else{
				switch (ptDir) {  //���ݷ���ѡ��̹�˵�ͼƬ
				case U:
					g.drawImage(tankImags1[0], x, y, null);
					break;
				case D:
					g.drawImage(tankImags1[1], x, y, null);
					break;
				case L:
					g.drawImage(tankImags1[2], x, y, null);
					break;
				case R:
					g.drawImage(tankImags1[3], x, y, null);
					break;	
				}
		}
		g.setColor(c);
		move();
		}
	}
		
	
	/**
	 * �õ�̹�������ɵľ���
	 * @return̹�������ɵľ���
	 */
	public Rectangle getrect(){
		return new Rectangle(x-5,y-5,WIDTH,HEIGHT);
	}
	
	/**
	 * ̹���ƶ����� ���ݷ�����ȷ��(ԭ��:���ݰ���ȷ��̹�˳��� ͨ������������̹�˵��ƶ�)
	 */
	public void move(){
		oldx=x;
		oldy=y;
		/*
		 * ��Ͳ��̹�˷���һ��   
		 */
		if(this.Dir!=Direction.STOP) {   
			this.ptDir=this.Dir;   
			}   
		/**
		 * �ж�̹��ײ�߽�(д�����鷳)
		 */
		
		switch (Dir) {  //ѡ���ƶ�����
		case L:
			x -= XSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		if (x < 0)
			x = 0;
		if (y < 25)      //��ֹ�߳��涨����
			y = 25;
		if (x + Tank.WIDTH > TankClient.GAME_WIDTH)  //����������ָ����߽�
			x = TankClient.GAME_WIDTH - Tank.WIDTH;
		if (y + Tank.HEIGHT> TankClient.GAME_HEIGHT)
			y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
		
		/**
		 * �з�̹������ƶ�
		 */
		if(!ismine){
			//��ö����ת��Ϊ����(����������!)
			Direction[] Dirs=Direction.values(); 
			if(flag==0) {      //ͨ��flag������ ������̹��һֱ�� Ҫ���ӳ�
				flag=r.nextInt(20)+3;     
				int p=r.nextInt(Dirs.length);
				Dir=Dirs[p];  
				}     
			flag--;        
			if(r.nextInt(20)>18) this.fire(); 
		}
	}
	
	
	/**
	 * ͨ���������ж�̹�˵ĳ���
	 */
	public void LocateDircetion(){
			if(bU&&!bD&&!bL&&!bR) 		Dir=Direction.U;
			else if(!bU&&bD&&!bL&&!bR)  Dir=Direction.D;
			else if(!bU&&!bD&&bL&&!bR)  Dir=Direction.L;
			else if(!bU&&!bD&&!bL&&bR)  Dir=Direction.R;
			else if(!bU&&!bD&&!bL&&!bR) Dir=Direction.STOP;
	}
	/**
	 * ���̼�����(����)
	 */
	public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			switch(key){
			case KeyEvent.VK_F1 :
				if(!this.islive&&tc.home.isLive()) {
					tc.mytank.islive=true;
					tc.mytank.setlocation(200, 580);
					tc.mytank.life=5;
					tc.mytank.ptDir=Direction.U;
				}
				if(tc.mytankflag==0){
					if(!tc.mytanks.get(1).islive&&tc.home.isLive()) {
						tc.mytanks.get(1).islive=true;
						tc.mytanks.get(1).setlocation(560, 580);
						tc.mytanks.get(1).life=5;
						tc.mytanks.get(1).ptDir=Direction.U;
					}
				}
				break;
			case KeyEvent.VK_SPACE :
				if(this.mine()){
					tc.bullet.WIDTH+=10;//�����ӵ���С���ٶ�
					tc.bullet.HEIGHT+=10;
					tc.bullet.XSPEED+=10;
					tc.bullet.YSPEED+=10;
					break;
				}
			case KeyEvent.VK_K://��С�ӵ���С���ٶ�
				if(this.mine()){
					if(tc.bullet.WIDTH>=25&&tc.bullet.HEIGHT>=25&&tc.bullet.XSPEED>15&&tc.bullet.YSPEED>15){
						tc.bullet.WIDTH-=10;
						tc.bullet.HEIGHT-=10;
						tc.bullet.XSPEED-=10;
						tc.bullet.YSPEED-=10;
					}
					break;
				}
			case KeyEvent.VK_ENTER://�ӵ���ԭ
				if(this.mine()){
					tc.bullet.WIDTH=10;
					tc.bullet.HEIGHT=10;
					tc.bullet.XSPEED=15;
					tc.bullet.YSPEED=15;
					break;
				}
			case KeyEvent.VK_UP:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bU=true;
					break;
				}
				else break;
			case KeyEvent.VK_W:
				bU=true;break;
			case KeyEvent.VK_DOWN:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bD=true;
					break;
				}
				else break;
			case KeyEvent.VK_S:
				bD=true;break;
			case KeyEvent.VK_LEFT:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bL=true;
					break;
				}
				else break;
			case KeyEvent.VK_A:
				bL=true;break;
			case KeyEvent.VK_RIGHT:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bR=true;
					break;
				}
				else break;
			case KeyEvent.VK_D:
				bR=true;break;
			}
			this.LocateDircetion();
			tc.mytanks.get(1).LocateDircetion();
		}
	
	/**
	 * ��̧�������
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
			switch(key){
			case KeyEvent.VK_F2:  //������F2ʱ�����¿�ʼ��Ϸ 
				tc.flag=1;
				tc.flag1=1;
				this.life=5;
				tc.tankcount=6;
				tc.score=0;
				tc.count=0;
				tc.gamecount=1;
				tc.mytankflag=1;
				if (tc.tanks.size() == 0) {   //����������û��̹��ʱ���ͳ���̹��      
					tc.tanks.add(new Tank(5,20,tc,false));
					tc.tanks.add(new Tank(200,20,tc,false));
					tc.tanks.add(new Tank(400,20,tc,false));		
					tc.tanks.add(new Tank(555,20,tc,false));
					tc.tanks.add(new Tank(765,20,tc,false));
				}
				
				tc.mytank = new Tank(200, 580, tc,true);//�����ҷ����ֵ�λ��
				
				if (!tc.home.isLive())  //��home��������
					tc.home.setLive(true);
				TankClient tc1=new TankClient("̹�˴�ս     Author: zzd"); //���´������
				tc1.launchFrame();
				tc.dispose();
				tc=tc1;
				tc.count=0;//ɱ��������Ϊ0
				break;
			case KeyEvent.VK_F3://�����ڶ����ҷ�̹��
				if(tc.mytankflag!=0){
					//Tank mytank1=new Tank(560,580, tc, true,1); //�Ȱ�̹�˶��ż��뼯�� ������flag ��������Խ��
					tc.mytankflag=0;
					//tc.mytanks.add(mytank1);
					break;
				}
			case KeyEvent.VK_UP:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bU=false;
					break;
				}
				else break;
			case KeyEvent.VK_W:
				bU=false;break;
			case KeyEvent.VK_DOWN:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bD=false;
					break;
				}
				else break;
			case KeyEvent.VK_S:
				bD=false;break;
			case KeyEvent.VK_LEFT:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bL=false;
					break;
				}
				else break;
			case KeyEvent.VK_A:
				bL=false;break;
			case KeyEvent.VK_RIGHT:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).bR=false;
					break;
				}
				else break;
			case KeyEvent.VK_D:
				bR=false;break;
			case KeyEvent.VK_J://����� J ctrl �����ٷ��ӵ�
				fire();
				break;
			case KeyEvent.VK_CONTROL:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).fire();
					break;
				}
				else break;
			case KeyEvent.VK_R ://�෽�����ӵ�
				superFire();
				break;
			case KeyEvent.VK_P ://�෽�����ӵ�
				if(tc.mytankflag==0){
					tc.mytanks.get(1).superFire();
					break;
				}
			case KeyEvent.VK_1:
				tc.tankcount=5;
				break;
			case KeyEvent.VK_2:
				tc.tankcount=7;
				break;
			case KeyEvent.VK_3:
				tc.tankcount=10;
				break;
			case KeyEvent.VK_4:
				tc.tankcount=15;
				break;
			}
			this.LocateDircetion();
			tc.mytanks.get(1).LocateDircetion();
		}

	
	/**
	 * ������
	 * @return����һ���ӵ�
	 */
	public Bullet fire() {   
		if(!islive) return null; 
		//�ӵ���Tank���м䷢��   
		int x = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;   
		int y = this.y+Tank.HEIGHT/2-Bullet.WIDTH/2;   
		//�ӵ��ĳ�ʼ������Ͳ����
		Bullet m = new Bullet(x, y,ptDir,this.tc,ismine);   
		tc.bullets.add(m);
		return m;  
		} 
	/**
	 * ���ݷ���������
	 * @param Dir̹�˳���
	 * @returnһ���ӵ�
	 */
	public Bullet fire(Direction Dir) {
		if(!islive) return null;
		int x = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		Bullet m = new Bullet(x,y,Dir,this.tc,ismine);
		tc.bullets.add(m);
		return m;
	}
	
	/**
	 * �෽�����ӵ� boom!
	 */
	private void superFire() {
		Direction[] Dirs = Direction.values();
		for(int i=0; i<5; i++) {
			fire(Dirs[i]);
		}
	}
	
	/**
	 * ���Խ����ǽ����
	 * @param w һ��ǽ
	 * @return���ײǽ����true ���򷵻�false(��ʵ������ ����ֵ����Ϊ��)
	 */
	public boolean throghWall(Wall w) {
		if(this.islive && this.getrect().intersects(w.getRect())) {
			this.x=oldx;
			this.y=oldy;
			return true;
		}
		return false;
	}
	/**
	 * ײ����ǽ
	 */
	public boolean throghWall(Wall2 w) {
		if(this.islive && this.getrect().intersects(w.getRect())) {
			this.x=oldx;
			this.y=oldy;
			return true;
		}
		return false;
	}
	//ײ����
	public boolean throghhome(Home home) {
		if(this.islive && this.getrect().intersects(home.getRect())) {
			this.x=oldx;
			this.y=oldy;
			return true;
		}
		return false;
	}
	//ײ������ʱ��
	public boolean Hittree(Tree r) {    
		if (this.islive && this.getrect().intersects(r.getRect())) {
			x=oldx;
			y=oldy;
			return true;
		}
		return false;
	}
	
	/**
	 * ̹�˲���ײ
	 * @param tanks
	 * @return
	 */
	
	public boolean HitTanks(java.util.ArrayList<Tank> tanks) {
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			if(this != t) {
				if(this.islive && t.live() && this.getrect().intersects(t.getrect())) {
					this.x=oldx;
					this.y=oldy;
					x=oldx;
					y=oldy;
					return true;
				}
			}
		}
		return false;
	}
	//�з�����ײ�ҷ�̹��
	public boolean Hitmytank(Tank t) {
			if(this.islive && t.live() && this.getrect().intersects(t.getrect())) {
				this.x=oldx;
				this.y=oldy;
				return true;
			}
		return false;
	}
	public void Hitblood(Blood b){
		if(this.islive&&this.getrect().intersects(b.getRect())){
			this.x=oldx;
			this.y=oldy;
		}
		
	}
	
	//��Ѫ
	public boolean eat(Blood b) {
		if (this.islive && b.isLive() && this.getrect().intersects(b.getRect())) {
			if(this.life<5)
			this.life = this.life+1;      //ÿ��һ��������1������
			b.setLive(false);
			return true;
		}
		return false;
	}
	
} 
	
	

