import logic.MathHelper;
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
    public static final int SCALE = 4;

    Random r = new Random();

    int r1 = r.nextInt(255);
    int g1 = r.nextInt(255);
    int b1 = r.nextInt(255);

    int r2 = r.nextInt(255);
    int g2 = r.nextInt(255);
    int b2 = r.nextInt(255);

    private double count = 0;

    private Thread runThread;
    private boolean running = false;
    private boolean paused = false;

    private Bitmap screenMap = new Bitmap(WIDTH*SCALE, HEIGHT*SCALE);

    public Engine(){
        setSize(WIDTH*SCALE, HEIGHT*SCALE);
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
            try {Thread.sleep(10);} catch (InterruptedException ex) {}
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
        BufferedImage bImage = new BufferedImage(WIDTH*SCALE, HEIGHT*SCALE, BufferedImage.TYPE_INT_RGB);
        screenMap.set(Sprites.rect(64,64, new Bit(r1, g1, b1)), 0, 0);
        screenMap.set(Sprites.rect(64,64, new Bit(r2, g2, b2)), WIDTH-64, 0);
        //screenMap.set(Sprites.rect(r.nextInt(200), r.nextInt(200), new Bit(r.nextInt(200), r.nextInt(255), r.nextInt(255))), r.nextInt(200), r.nextInt(200));
        //screenMap.add(Sprites.rect(r.nextInt(200), r.nextInt(200), new Bit(r.nextInt(200), r.nextInt(255), r.nextInt(255))), r.nextInt(200), r.nextInt(200));
        //screenMap.subtract(Sprites.rect(r.nextInt(200), r.nextInt(200), new Bit(r.nextInt(200), r.nextInt(255), r.nextInt(255))), r.nextInt(200), r.nextInt(200));
        //screenMap.multiply(Sprites.rect(r.nextInt(200), r.nextInt(200), new Bit(r.nextInt(200), r.nextInt(255), r.nextInt(255))), r.nextInt(200), r.nextInt(200));
        //screenMap.divide(Sprites.rect(r.nextInt(200), r.nextInt(200), new Bit(r.nextInt(200), r.nextInt(255), r.nextInt(255))), r.nextInt(200), r.nextInt(200));
        if(count < WIDTH){
            long rVal = MathHelper.map((int)count, 0, WIDTH, r1, r2);
            long gVal = MathHelper.map((int)count, 0, WIDTH, g1, g2);
            long bVal = MathHelper.map((int)count, 0, WIDTH, b1, b2);

            screenMap.set(Sprites.rect(8, 8, new Bit((int)rVal, (int)gVal, (int)bVal)), (int) count, (int) Math.floor(Math.cos(count / 32) * 64) + HEIGHT/2);
        }

        for (int xC = 0; xC < WIDTH; xC++) {
            for (int yC = 0; yC < HEIGHT; yC++) {
                Color c = new Color(screenMap.bitArray[xC][yC].r, screenMap.bitArray[xC][yC].g, screenMap.bitArray[xC][yC].b);
                bImage.setRGB(xC*SCALE, yC*SCALE, c.getRGB());
            }
        }

        count = count + 1;
        g.drawImage(bImage, 0, 0, this);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        Engine engine = new Engine();
        engine.start();
        frame.getContentPane().add(engine);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WIDTH*SCALE, HEIGHT*SCALE);
        frame.setVisible(true);
    }
}
