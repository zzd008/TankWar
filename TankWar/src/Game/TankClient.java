package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 *  ��������
 * @author ���´�
 * ��Ϸ������˼·�ǣ�ͨ���̲߳����ػ���Ϸ����  ������Ϸ��ʵ�ָ���Ԫ������߼���ϵ 
 */
public class TankClient extends Frame{
	/**
	 * �ڼ���
	 */
	public int gamecount=1;
	/**
	 * ֻ�ܲ�����һ���ҷ�̹�˵ı�־
	 */
	public int mytankflag=1;
	public static int flag=1;//��Ϸ��������̹���ٲ���!
	/**
	 * �´γ���̹�˵�����
	 */
	
	/**
	 * ͨ�صı�־
	 */
	public static int flag1=1;
	public static int tankcount=6;
	/**
	 * ��Ϸ����Ŀ��
	 */
	public static final int GAME_WIDTH=800;
	/**
	 * ��Ϸ����ĸ߶� 
	 */
	public static final int GAME_HEIGHT=600;
	/**
	 * ��Ϸ����
	 */
	public static int score=0;//��̬ �ҷ�����̹�˹�ͬ����!
	/**
	 * ɱ����
	 */
	public static int count=0;
	
	Image offScreenImage = null;  //����һ������ͼƬ 
	/*
	 * ��������ǽ
	 */
	ArrayList<Wall> Wall1 = new ArrayList<Wall>();
	/**
	 * ����ǽ����
	 */
	ArrayList<Wall2> Wall2 = new ArrayList<Wall2>();
	
	Tank mytank=new Tank(200, 580,this,true,0);//�����ҷ�̹��
	Tank mytank1=new Tank(560,580, this, true,1);//�ҷ�̹�˶���
	/**
	 * �ҷ�̹�˼���
	 */
	ArrayList<Tank> mytanks = new ArrayList<Tank>();
	
	Bullet bullet=null;//�����ӵ�
	Home home=new Home(368, 545, this);//��
	Blood blood = new Blood(); // ʵ��������
	
	 /**
	  * �ӵ�����
	  */
	ArrayList<Bullet> bullets=new ArrayList<Bullet>(); 
	/**
	 * ��ը����
	 */
	ArrayList<Boom> booms=new ArrayList<Boom>(); 
	/**
	 * �з�̹������
	 */
	ArrayList<Tank> tanks=new ArrayList<Tank>();
	/**
	 * ������
	 */
	ArrayList<Tree> trees = new ArrayList<Tree>();
	
	
	/**
	 * ���ô��������
	 * @param ���������
	 */
	public TankClient(String string) {
		this.setTitle(string);
	}
	/**
	 * paint����(�������������Ԫ��(���ӵ� ̹�� ǽ ��)) ͨ���߳�һֱ�����ý��л������
	 */
	public void paint(Graphics g){
		/**
		 * �����ҷ�ÿ��̹��
		 */
		if(this.mytankflag==0){
			for(int i=0;i<mytanks.size();i++){
				Tank myt=mytanks.get(i);
				myt.draw(g);
				myt.eat(blood);// ��Ѫ
				//̹�˲���ײ�ϼ�
				myt.throghhome(this.home);
				//�ҷ�̹�˲�����ײǽ
				for(int i1=0; i1<Wall1.size(); i1++) {
					Wall t = Wall1.get(i1);
					myt.throghWall(t);
				}
				for(int i2=0; i2<Wall2.size(); i2++) {
					Wall2 t = Wall2.get(i2);
					myt.throghWall(t);
				}
				//��ײ�з�̹��
				myt.HitTanks(tanks);
			}
		}
			else {
				for(int i=0;i<1;i++){
					Tank myt=mytanks.get(i);
					myt.draw(g);
					myt.eat(blood);// ��Ѫ
					//̹�˲���ײ�ϼ�
					myt.throghhome(this.home);
					//�ҷ�̹�˲�����ײǽ
					for(int i1=0; i1<Wall1.size(); i1++) {
						Wall t = Wall1.get(i1);
						myt.throghWall(t);
					}
					for(int i2=0; i2<Wall2.size(); i2++) {
						Wall2 t = Wall2.get(i2);
						myt.throghWall(t);
					}
					//��ײ�з�̹��
					myt.HitTanks(tanks);
				}
			}
		
		//�ҷ�̹�˲�����ײ
		if(this.mytankflag==0){
			mytank.Hitmytank(mytanks.get(1));
			mytanks.get(1).Hitmytank(mytank);
		}
		//mytank.draw(g);//�����ҷ�̹��
		//mytank.eat(blood);// ����Ѫ--����ֵ
		//��Ѫ
		blood.draw(g);
		//��������ǽ
		for(int i=0; i<Wall1.size(); i++) {
			Wall t = Wall1.get(i);
			t.draw(g);
		}
		//��������ǽ
		for(int i=0; i<Wall2.size(); i++) {
			Wall2 t = Wall2.get(i);
			t.draw(g);
		}
		//����
		for(int i=0; i<trees.size(); i++) {
			Tree t= trees.get(i);
			t.draw(g);
		}
		//���ϼ�
		
		home.draw(g);
		
		g.setColor(Color.red);
		//Font f=g.getFont();
		//g.setFont(new Font(" ", Font.PLAIN, 20));
		//g.drawString("��    ��:"+score,20,40);//�ڴ�������ʾ����
		//g.drawString("ɱ����:"+count,20,80);//�ڴ�������ʾɱ����
		//g.drawString("ʣ��̹����:"+tanks.size(),200,40);//�ڴ�������ʾʣ��̹������
		if(this.home.isLive()){
			g.drawString("��"+gamecount+"��",372 ,320 );
			g.drawString("̹��1������ֵ:"+mytank.getlife(),3,320);//�ҷ�̹�˵�����
			if(this.mytankflag==0){
				g.drawString("̹��2������ֵ:"+mytanks.get(1).getlife(),705,320);//�ҷ�̹��1������	
			}
		}
		//g.setFont(f);
		//���̹��ȫ�������� ���»��з�̹�� �����Լ�ָ��
		/*if(tanks.size() <= 0){
			for(int i=0;i<=tankcount;i++){
				tanks.add(new Tank(150+50*i,200,this,false));
			}
			}
			*/	
		if(tanks.size()<=0&&flag!=0&&tankcount!=10){
			tanks.add(new Tank(5,20,this,false));
			tanks.add(new Tank(200,20,this,false));
			//tanks.add(new Tank(400,20,this,false));		
			tanks.add(new Tank(555,20,this,false));
			tanks.add(new Tank(765,20,this,false));
			if(tankcount==6){
				tanks.add(new Tank(5,340,this,false));
				tanks.add(new Tank(770,340,this,false));
			}
			if(tankcount==8){
				tanks.add(new Tank(5,340,this,false));
				tanks.add(new Tank(770,340,this,false));
				tanks.add(new Tank(5,580,this,false));
				tanks.add(new Tank(770,580,this,false));
			}
			tankcount+=2;
			gamecount++;
			if(tankcount==10){
				flag1=0;
			}
			//System.out.println(tankcount);
			//System.out.println(tanks.size());
		}
		
		
		//�з�̹�˲�ײ�ϼ�
		for(int i=0;i<tanks.size();i++){
			Tank t=tanks.get(i);
			t.throghhome(this.home);
		}
		
		
		//�з�̹�˲�����ײǽ/ײ��/ײѪ..
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			for(int j=0; j<Wall1.size(); j++) {
				Wall t1 = Wall1.get(j);
				t.throghWall(t1);
			}
			for(int j=0; j<Wall2.size(); j++) {
				Wall2 t2 = Wall2.get(j);
				t.throghWall(t2);
			}
			for(int k=0;k<trees.size();k++){
				Tree p=trees.get(k);
				t.Hittree(p);
			}
			t.HitTanks(tanks);
			t.throghhome(home);
			for(int k1=0;k1<mytanks.size();k1++){
				Tank myt=mytanks.get(k1);
				t.Hitmytank(myt);
			}
			t.Hitblood(blood);
		}
		
		//���з�̹��
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.draw(g);
		}
		
		//�����ӵ�
		for(int i=0;i<bullets.size();i++){
			Bullet m=bullets.get(i);
			m.hitTanks(tanks);
			m.hitTank(mytank);
			if(this.mytankflag==0){
				m.hitTank(mytanks.get(1));
			}
			m.hitHome();
			for(int j=0; j<Wall1.size(); j++) {
				Wall t1 = Wall1.get(j);
				m.hitWall(t1);
			}
			for(int j=0; j<Wall2.size(); j++) {
				Wall2 t1 = Wall2.get(j);
				m.hitWall(t1);
			}
			m.draw(g);
		}
		
		//������ը
		for(int i=0;i<booms.size();i++){
			Boom m=booms.get(i);
			m.draw(g);
		}
		
	}
	
	/**
	 * �÷������ڽ��������˸����
	 */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.black);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	/**
	 *�÷���������Ϸ�����һЩ��������
	 */
	public void launchFrame(){
		
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setLocation(800, 300);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("tk2.png"));//���������ͼ��
		this.setResizable(false); //���ô˴��岻������a��������С ȫ�������⣡
		this.setBackground(Color.black);//���ô��屳����ɫ
		this.setVisible(true);
		addWindowListener(new WindowAdapter() { //��Ӽ����� �رմ���
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//���õз�̹�˳��ֵ�λ��
		tanks.add(new Tank(5,20,this,false));
		tanks.add(new Tank(200,20,this,false));		
		tanks.add(new Tank(555,20,this,false));
		tanks.add(new Tank(765,20,this,false));
		
		/**
		 * �ҷ�̹�˼���
		 */
		mytanks.add(mytank);
		mytanks.add(mytank1);
		
		//����ǽ
		//����Χ��ǽ
		for(int i=0;i<4;i++){
			Wall1.add(new Wall(345,580-20*i,this));
		}
		for(int i=0;i<4;i++){
			Wall1.add(new Wall(415,580-20*i,this));
		}
		Wall1.add(new Wall(355,520,this));
		Wall1.add(new Wall(375,520,this));
		Wall1.add(new Wall(395,520,this));
		//����ǽ
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(45,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(65,70+20*i,this));
		}
		
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(145,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(165,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(245,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(265,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(725,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(705,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(625,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(605,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(525,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(505,70+20*i,this));
		}
		
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(340,100+20*i,this));
		}
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(420,100+20*i,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,100,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,180,this));
		}
		
		//�·�
		
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(45,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(65,530-20*i,this));
		}
		
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(145,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(165,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(245,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(265,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(725,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(705,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(625,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(605,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(525,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(505,530-20*i,this));
		}
		
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(340,480-20*i,this));
		}
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(420,480-20*i,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,400,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,480,this));
		}
		
		
		//����ǽ
		for(int i=0;i<4;i++){
			Wall2.add(new Wall2(20*i, 300,this));
		}
		for(int i=0;i<4;i++){
			Wall2.add(new Wall2(760-20*i, 300,this));
		}
		for(int i=0;i<4;i++){
			Wall2.add(new Wall2(340+20*i, 300,this));
		}
		for(int i=0;i<1;i++){
			Wall2.add(new Wall2(372+20*i, 132,this));
		}
		for(int i=0;i<1;i++){
			Wall2.add(new Wall2(372+20*i, 432,this));
		}
		
		
		//��
		for (int i = 0; i < 4; i++) { 
				trees.add(new Tree( 150+30 * i, 300, this));
		}
		for (int i = 0; i < 4; i++) { 
			trees.add(new Tree( 520+30 * i, 300, this));
		}
		
		//��Ӽ��̼����� ��̹�˶�����
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				 mytank.keyPressed(e);
				}
			public void keyReleased(KeyEvent e){
				mytank.keyReleased(e);
			}
		});
		new Thread(new PaintThread()) .start();	//�����߳� ��̹�˶�����
	}
	
	/**
	 * ������
	 * @param args
	 */
	public static void main(String[] args) {
		TankClient tc=new TankClient("̹�˴�ս     Author: zzd");
		tc.launchFrame();
	}
	
	/**
	 * �߳� ����paint()���� ���л������
	 * @author���´�
	 *
	 */
	private class PaintThread implements Runnable{
		public void run(){
			while(true){
				repaint();
				try {
					Thread.sleep(60);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}