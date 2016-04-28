package render;

public class Sprites {
    public static Bitmap rect(int width, int height, Bit bit){
        Bitmap bitmap = new Bitmap(width, height);
        for(int xC = 0; xC < width; xC++){
            for(int yC = 0; yC < height; yC++){
                bitmap.bitArray[xC][yC] = bit;
            }
        }
        return bitmap;
    }
}
