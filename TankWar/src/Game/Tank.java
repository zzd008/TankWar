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
 * 坦克类
 * @author 周致达
 *包含坦克的一些属性及方法
 */
public class Tank {
	/**
	 * 控制面板
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	/**
	 * 我方坦克图片数组
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
	 * 敌方坦克图片数组
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
	 * 区别我方坦克的mytflag
	 * 0 为第一个坦克 1为第二个坦克
	 */
	public int mytflag;
	/**
	 * 坦克的水平移动速度
	 */
	public static final int XSPEED=10;
	/**
	 * 坦克的竖直移动速度
	 */
	public static final int YSPEED=10;
	/**
	 * 坦克的宽
	 */
	public static final int WIDTH=32;
	/**
	 * 坦克的高
	 */
	public static final int HEIGHT=32;
	/**
	 * 坦克的生命值
	 */
	private int life=5;
	
	/**
	 * 炮筒方向 默认为左
	 */
	private Direction ptDir = Direction.U;
	
	/**
	 * 保留TankClient的引用，方便使用其中的成员变量
	 */
	TankClient tc=null;
	/**
	 * 判断是否按下了四个方向键 
	 */
	private boolean bU=false,bD=false,bL=false,bR=false;
	/**
	 * 坦克的方向 可向八个方向移动
	 * @author 周致达
	 *
	 */
	enum Direction{U,D,L,R,STOP};
	
	private Direction Dir=Direction.STOP;//默认坦克是停着的
	/**
	 * 坦克的位置坐标
	 */
	private int x,y;
	/**
	 * 坦克的上一步位置
	 */
	private int oldx,oldy;
	/**
	 * 判断是否为我方坦克
	 */
	private boolean ismine;
	/**
	 * 判断坦克是否活着
	 */
	private boolean islive=true;
	/**
	 * 随机类对象
	 */
	private static Random r=new Random();
	
	private int flag=r.nextInt(12)+3;//每次赋值
	/**
	 * 当我方坦克被打中 减少生命值
	 */
	public void dislife(){
		life-=1;
	}
	/**
	 * 返回我方坦克生命值
	 * @return我方坦克生命值
	 */
	public int getlife(){
		return life;
	}
	/**
	 * 设置重生
	 * @param live
	 */
	public void setlive(boolean live){
		this.islive=live;
	}
	/**
	 * 判断是否为我方坦克
	 * @return
	 */
	public boolean mine(){
		return ismine;
	}
	/**
	 * 判断坦克是否活着
	 * @return
	 */
	public boolean live(){
		return islive;
	}
	/**
	 * 得到坦克的x坐标
	 * @return
	 */
	public int getx(){
		return this.x;
	}
	/**
	 * 得到坦克的y坐标
	 * @return
	 */
	public int gety(){
		return this.y;
	}
	/**
	 * 设置我方坦克重生时的位置及方向
	 * @param x
	 * @param y
	 */
	public void setlocation(int x,int y){
		this.x=x;
		this.y=y;
		this.Dir=this.Dir;
	}
	
	/**
	 * 坦克类构造方法
	 */
	public Tank(int x, int y, TankClient tc,boolean ismine) { //将当前TankClirnt中的tc 传给Tank中的tc引用  
		this.x=x;
		this.y=y;
		this.ismine=ismine;
		this.oldx = x;
		this.oldy = y;  
		this.tc = tc;
	}
	
	/**
	 * 坦克类构造方法重载
	 */
	public Tank(int x, int y, TankClient tc,boolean ismine,int flag) { //将当前TankClirnt中的tc 传给Tank中的tc引用  
		this.x=x;
		this.y=y;
		this.ismine=ismine;
		this.oldx = x;
		this.oldy = y;  
		this.tc = tc;
		this.mytflag=flag;
	}
	
	/**
	 * 坦克画笔
	 * @param g
	 */
	public void draw(Graphics g){
		if(live()){ //先判断坦克是否活着
			oldx=x;oldy=y;
			Color c=g.getColor();
			if(this.ismine) //敌方和我方坦克颜色分开设置
				switch (ptDir) {  //根据方向选择坦克的图片
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
				switch (ptDir) {  //根据方向选择坦克的图片
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
	 * 得到坦克所构成的矩形
	 * @return坦克所构成的矩形
	 */
	public Rectangle getrect(){
		return new Rectangle(x-5,y-5,WIDTH,HEIGHT);
	}
	
	/**
	 * 坦克移动函数 根据方向来确定(原理:根据按键确定坦克朝向 通过朝向来进行坦克的移动)
	 */
	public void move(){
		oldx=x;
		oldy=y;
		/*
		 * 炮筒与坦克方向一致   
		 */
		if(this.Dir!=Direction.STOP) {   
			this.ptDir=this.Dir;   
			}   
		/**
		 * 判断坦克撞边界(写的略麻烦)
		 */
		
		switch (Dir) {  //选择移动方向
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
		if (y < 25)      //防止走出规定区域
			y = 25;
		if (x + Tank.WIDTH > TankClient.GAME_WIDTH)  //超过区域则恢复到边界
			x = TankClient.GAME_WIDTH - Tank.WIDTH;
		if (y + Tank.HEIGHT> TankClient.GAME_HEIGHT)
			y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
		
		/**
		 * 敌方坦克随机移动
		 */
		if(!ismine){
			//将枚举型转化为数组(方法很巧妙!)
			Direction[] Dirs=Direction.values(); 
			if(flag==0) {      //通过flag来调和 不能让坦克一直动 要有延迟
				flag=r.nextInt(20)+3;     
				int p=r.nextInt(Dirs.length);
				Dir=Dirs[p];  
				}     
			flag--;        
			if(r.nextInt(20)>18) this.fire(); 
		}
	}
	
	
	/**
	 * 通过按键来判断坦克的朝向
	 */
	public void LocateDircetion(){
			if(bU&&!bD&&!bL&&!bR) 		Dir=Direction.U;
			else if(!bU&&bD&&!bL&&!bR)  Dir=Direction.D;
			else if(!bU&&!bD&&bL&&!bR)  Dir=Direction.L;
			else if(!bU&&!bD&&!bL&&bR)  Dir=Direction.R;
			else if(!bU&&!bD&&!bL&&!bR) Dir=Direction.STOP;
	}
	/**
	 * 键盘监听器(按键)
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
					tc.bullet.WIDTH+=10;//增加子弹大小和速度
					tc.bullet.HEIGHT+=10;
					tc.bullet.XSPEED+=10;
					tc.bullet.YSPEED+=10;
					break;
				}
			case KeyEvent.VK_K://减小子弹大小和速度
				if(this.mine()){
					if(tc.bullet.WIDTH>=25&&tc.bullet.HEIGHT>=25&&tc.bullet.XSPEED>15&&tc.bullet.YSPEED>15){
						tc.bullet.WIDTH-=10;
						tc.bullet.HEIGHT-=10;
						tc.bullet.XSPEED-=10;
						tc.bullet.YSPEED-=10;
					}
					break;
				}
			case KeyEvent.VK_ENTER://子弹复原
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
	 * 键抬起监听器
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
			switch(key){
			case KeyEvent.VK_F2:  //当按下F2时，重新开始游戏 
				tc.flag=1;
				tc.flag1=1;
				this.life=5;
				tc.tankcount=6;
				tc.score=0;
				tc.count=0;
				tc.gamecount=1;
				tc.mytankflag=1;
				if (tc.tanks.size() == 0) {   //当在区域中没有坦克时，就出来坦克      
					tc.tanks.add(new Tank(5,20,tc,false));
					tc.tanks.add(new Tank(200,20,tc,false));
					tc.tanks.add(new Tank(400,20,tc,false));		
					tc.tanks.add(new Tank(555,20,tc,false));
					tc.tanks.add(new Tank(765,20,tc,false));
				}
				
				tc.mytank = new Tank(200, 580, tc,true);//设置我方出现的位置
				
				if (!tc.home.isLive())  //将home重置生命
					tc.home.setLive(true);
				TankClient tc1=new TankClient("坦克大战     Author: zzd"); //重新创建面板
				tc1.launchFrame();
				tc.dispose();
				tc=tc1;
				tc.count=0;//杀敌数重置为0
				break;
			case KeyEvent.VK_F3://产生第二个我方坦克
				if(tc.mytankflag!=0){
					//Tank mytank1=new Tank(560,580, tc, true,1); //先把坦克二号加入集合 再立个flag 否则数组越界
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
			case KeyEvent.VK_J://开火键 J ctrl 按下再发子弹
				fire();
				break;
			case KeyEvent.VK_CONTROL:
				if(tc.mytankflag==0){
					tc.mytanks.get(1).fire();
					break;
				}
				else break;
			case KeyEvent.VK_R ://多方向发射子弹
				superFire();
				break;
			case KeyEvent.VK_P ://多方向发射子弹
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
	 * 开火函数
	 * @return返回一颗子弹
	 */
	public Bullet fire() {   
		if(!islive) return null; 
		//子弹从Tank的中间发射   
		int x = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;   
		int y = this.y+Tank.HEIGHT/2-Bullet.WIDTH/2;   
		//子弹的初始化由炮筒决定
		Bullet m = new Bullet(x, y,ptDir,this.tc,ismine);   
		tc.bullets.add(m);
		return m;  
		} 
	/**
	 * 根据方向来开火
	 * @param Dir坦克朝向
	 * @return一个子弹
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
	 * 多方向发射子弹 boom!
	 */
	private void superFire() {
		Direction[] Dirs = Direction.values();
		for(int i=0; i<5; i++) {
			fire(Dirs[i]);
		}
	}
	
	/**
	 * 解决越公共墙问题
	 * @param w 一个墙
	 * @return如果撞墙返回true 否则返回false(无实际意义 返回值可以为空)
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
	 * 撞金属墙
	 */
	public boolean throghWall(Wall2 w) {
		if(this.islive && this.getrect().intersects(w.getRect())) {
			this.x=oldx;
			this.y=oldy;
			return true;
		}
		return false;
	}
	//撞到家
	public boolean throghhome(Home home) {
		if(this.islive && this.getrect().intersects(home.getRect())) {
			this.x=oldx;
			this.y=oldy;
			return true;
		}
		return false;
	}
	//撞到树的时候
	public boolean Hittree(Tree r) {    
		if (this.islive && this.getrect().intersects(r.getRect())) {
			x=oldx;
			y=oldy;
			return true;
		}
		return false;
	}
	
	/**
	 * 坦克不相撞
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
	//敌方不能撞我方坦克
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
	
	//吃血
	public boolean eat(Blood b) {
		if (this.islive && b.isLive() && this.getrect().intersects(b.getRect())) {
			if(this.life<5)
			this.life = this.life+1;      //每吃一个，增加1生命点
			b.setLive(false);
			return true;
		}
		return false;
	}
	
} 
	
	

