import render.Bit;
import render.Bitmap;
import render.Sprites;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import javax.swing.*;

public class Engine extends JPanel implements Runnable{
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private int count = 0;

    private Thread runThread;
    private boolean running = false;
    private boolean paused = false;

    private Bitmap screenMap = new Bitmap(WIDTH, HEIGHT);

    public Engine(){
        setSize(WIDTH, HEIGHT);
    }

    public void start() {
        running = true;
        paused = false;
        if(runThread == null || !runThread.isAlive())
            runThread = new Thread(this);
        else if(runThread.isAlive())
            throw new IllegalStateException("Thread already started.");
        runThread.start();
    }

    public void stop() {
        if(runThread == null)
            throw new IllegalStateException("Thread not started.");
        synchronized (runThread) {
            try {
                running = false;
                runThread.notify();
                runThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        if(runThread == null)
            throw new IllegalStateException("Thread not started.");
        synchronized (runThread) {
            paused = false;
            runThread.notify();
        }
    }

    public void run() {
        while(running) {
            repaint();
            try {Thread.sleep(100);} catch (InterruptedException ex) {}
            synchronized (runThread) {
                if(paused) {
                    try {
                        runThread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        paused = false;
    }

    public void paintComponent(Graphics g) {
        Random r = new Random();
        BufferedImage bImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        screenMap.set(Sprites.rect(r.nextInt(200), r.nextInt(200), new Bit(r.nextInt(200), r.nextInt(255), r.nextInt(255))), r.nextInt(200), r.nextInt(200));
        screenMap.subtract(Sprites.rect(r.nextInt(200), r.nextInt(200), new Bit(r.nextInt(200), r.nextInt(255), r.nextInt(255))), r.nextInt(200), r.nextInt(200));
        for(int xC = 0; xC < WIDTH; xC++){
            for(int yC = 0; yC < HEIGHT; yC++){
                Color c = new Color(screenMap.bitArray[xC][yC].r, screenMap.bitArray[xC][yC].g, screenMap.bitArray[xC][yC].b);
                bImage.setRGB(xC, yC, c.getRGB());
            }
        }
        count++;
        g.drawImage(bImage, 0, 0, this);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        Engine engine = new Engine();
        engine.start();
        frame.getContentPane().add(engine);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}
