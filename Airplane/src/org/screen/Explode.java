package org.screen;


import java.awt.Graphics;
import java.awt.Image;

import org.screen.GameUtil;

/**
 * 爆炸类
 * @author admin
 */
public class Explode {
    double x, y;

    //一堆图片，直接用static，只加载一遍，所有爆炸对象共享
    static Image[] imgs = new Image[10];
    int count;             //imgs数量
    
    //加载图片
    static {               //利用static方法改写static对象
        for (int i = 0; i < 10; i++) {
            imgs[i] = GameUtil.getImage("airbackground.jpg");
            imgs[i].getWidth(null);  //可真实的将image加载进来
        }
    }
    
    //爆炸行为  就是  数组图片的切换
    public void draw(Graphics g) {
        if (count < 10) {
            g.drawImage(imgs[count], (int)x, (int)y, null);
            count++;
        }
    }
    
    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
}