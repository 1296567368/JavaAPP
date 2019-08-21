package com.lmz.snake.core;

import java.awt.Graphics;

import com.lmz.snake.constant.Constant;
import com.lmz.snake.util.ImageUtil;

public class Food extends SnakeObject{

	
	public Food() {
		this.live = true;
		this.img = ImageUtil.images.get("food");
		this.width = img.getWidth(null);
		this.height= img.getHeight(null);
		this.x = (int) (Math.random()*(Constant.GAME_WIDTH-width+10));
		this.y = (int) (Math.random()*(Constant.GAME_HEIGHT-40-height)+40);
	}
	
	/**
	 * 食物被吃
	 */
	public void eaten(MySnake mySnake) {
		if (mySnake.getRectangle().intersects(this.getRectangle())&&live&&mySnake.live) {
			this.live = false;
			mySnake.setLength(mySnake.getLength()+1);//长度增1
			mySnake.score += 10*mySnake.getLength();//加分
		}
	}
	
	
	/**
	 * 绘制食物
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	
}
