package org.screen;


import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Myframe extends Frame {
	
	
        //加载窗口
        public void launchFrame() {
            setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);       //设置窗口大小
            setLocation(100, 100);   //设置左上角坐标，开始位置, 也就是窗口开始位置
            setVisible(true);        //设置为可见(默认为不可见)
            
            //启动重画线程
            new PaintThread().start();
            
            //匿名内部类---用来关闭窗口
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            
        }
        
        //双缓冲技术解决屏幕闪烁
        private Image offScreenImage = null;   //利用双缓冲技术消除闪烁
        public void update(Graphics g) {
            if (offScreenImage == null)
                offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
            
            Graphics gOff = offScreenImage.getGraphics();
            
            paint(gOff);
            g.drawImage(offScreenImage, 0, 0, null);
        }
        
        /**
         * 定义一个重画窗口的线程类
         * 是一个内部类(方便访问外部类属性)
         */
        class PaintThread extends Thread {
            public void run() {
                while (true) {
                    repaint();             //重画
                    try {
                        Thread.sleep(40);  //1s = 1000ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }  
                }
            }
        }
        
}