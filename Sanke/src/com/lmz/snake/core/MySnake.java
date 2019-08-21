package com.lmz.snake.core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import com.lmz.snake.constant.Constant;
import com.lmz.snake.util.GameUtil;
import com.lmz.snake.util.ImageUtil;

public class MySnake extends SnakeObject implements Moveable{

	//定义蛇头部图片(未旋转时候)
	private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake_head");
	private int speed;//移动速度
	private int length;//长度
	private int num;//TODO 不知道是啥
	public static List<Point> bodyPoints = new LinkedList<>();//蛇身的点数集合
	public int score;//分数
	private static BufferedImage newImageSnakeHead;//旋转后的蛇头
	boolean up, down, left, right = true;//四个运行状态 但初始状态向右
	
	public MySnake(int x, int y) {
		this.live = true;
		this.x = x;
		this.y = y;
		this.img = ImageUtil.images.get("snake_body");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 5;
		this.length = 1;
		this.num = width / speed;
		newImageSnakeHead = IMG_SNAKE_HEAD;
	}
	
	/**
	 * 键盘按下事件
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (!down) {	// 蛇向上走时不能点下按钮
				up = true;
				down = false;
				left = false;
				right = false;
				newImageSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);//蛇头旋转90度
			}
			break;
		case KeyEvent.VK_DOWN:
			if (!up) {	
				down = true;
				up = false;
				left = false;
				right = false;
				newImageSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
			}
			break;
		case KeyEvent.VK_LEFT:
			if (!right) {	
				left = true;
				down = false;
				up = false;
				right = false;
				newImageSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (!left) {	
				right = true;
				down = false;
				up = false;
				left = false;
				newImageSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 0);
			}
			break;

		default:
			break;
		}
	}
	
	
	@Override
	public void draw(Graphics g) {
		outOfBounds();
		eatBody();
		bodyPoints.add(new Point(x, y));
		
		//移动的实现(当保存的轨迹点的个数为蛇的长度+1的n倍时)
		if (bodyPoints.size() == (this.length+1)*num) {
			bodyPoints.remove(0);//移除第一个点
		}
		g.drawImage(newImageSnakeHead, x, y, null);//绘制蛇头
		//蛇身
		drawBody(g);
		move();
	}

	@Override
	public void move() {
		if (up) {//若为向上走
			y -= speed;
		}else if (down) {
			y += speed;
		}else if (left) {
			x -= speed;
		}else if (right) {
			x += speed;
		}
		
	}
	
	/**
	 * 绘制蛇身
	 * @param g
	 */
	public void drawBody(Graphics g) {
		int length = bodyPoints.size()-1 - num;//前num个存储的时蛇头的当前轨迹坐标
		for(int i = length; i>=num; i -= num) {
			Point p = bodyPoints.get(i);
			g.drawImage(img, p.x, p.y, null);
		}
	}

	//处理越界
	public void outOfBounds() {
		boolean xOut = (x<=0||x>=(Constant.GAME_WIDTH-width));
		boolean yOut = (y<=40||y>=(Constant.GAME_HEIGHT -height));
		if (xOut || yOut) {
			live = false;
		}
	}
	
	public void eatBody() {
		for(Point p: bodyPoints) {
			for(Point p2: bodyPoints) {
				if (p.equals(p2) && p != p2) {
					this.live = false;
				}
			}
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
