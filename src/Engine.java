import java.awt.*;
import java.awt.image.*;
//import java.util.Random;
import javax.swing.*;

public class Engine extends JPanel{
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public Engine(){
        setSize(WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        Image img = drawPixels();
        g.drawImage(img, 0, 0, this);
    }

    public Image drawPixels() {
        BufferedImage bImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bImage.getGraphics();

        int r = 0;

        for(int xL = 0; xL < WIDTH; xL++){
            for(int yL = 0; yL < HEIGHT; yL++){
                r++;
                int rColor = new Color((int)Math.abs(Math.floor(255*Math.sin(r))), 0, (int)Math.abs(Math.floor(255*Math.cos(r)))).getRGB();
                bImage.setRGB(xL, yL, rColor);
            }
        }

        return bImage;
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Engine());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}
