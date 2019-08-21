package com.lmz.snake.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class SnakeObject implements Drawable{

	int x;
	int y;
	Image img;
	int width;
	int height;	
	public boolean live;// 死亡还是存活
	
	
	
	
	
	@Override
	public abstract void draw(Graphics g);

	/**
	 * 获取图片对应的矩形
	 * @return
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
