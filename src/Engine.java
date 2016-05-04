import logic.MathHelper;
import logic.PhysicsDomain;
import logic.PhysicsObject;
import logic.Physics;
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
    public static final int SCALE = 1;
    public static final int TICKTIME = 10;

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

    private static Bitmap screenMap = new Bitmap(WIDTH*SCALE, HEIGHT*SCALE);
    private static PhysicsDomain screenDomain = new PhysicsDomain();

    public Engine(){
        setSize(WIDTH*SCALE, HEIGHT*SCALE);
    }

    public void start() {
        running = true;
        paused = false;
        if(runThread == null || !runThread.isAlive()) {
            runThread = new Thread(this);
        }else if(runThread.isAlive()) {
            throw new IllegalStateException("Thread already started.");
        }
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
            try {Thread.sleep(TICKTIME);} catch (InterruptedException ex) {}
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
        //screenMap.set(Sprites.rect(64,64, new Bit(r1, g1, b1)), 0, 0);

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
        PhysicsObject newPhys = new PhysicsObject(50, 50, 0, 0 , "box");
        screenDomain.add(newPhys);
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
