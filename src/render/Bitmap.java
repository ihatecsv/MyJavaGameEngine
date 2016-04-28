package render;

import exception.InvalidBitmapException;

public class Bitmap{
    public Bit[][] bitArray;
    public int width;
    public int height;

    public Bitmap(int width, int height){
        this.bitArray = new Bit[width][height];
        for(int xC = 0; xC < width; xC++){
            for(int yC = 0; yC < height; yC++) {
                bitArray[xC][yC] = new Bit();
            }
        }
        this.width = width;
        this.height = height;
    }

    public Bitmap(Bit[][] bitArray) throws exception.InvalidBitmapException {
        this.bitArray = bitArray;
        this.width = bitArray.length;
        this.height = bitArray[0].length;
    }

    public void add(Bitmap bitmap, int x, int y){
        for(int xC = 0; xC < bitmap.bitArray.length; xC++){
            for(int yC = 0; yC < bitmap.bitArray[0].length; yC++) {
                bitArray[x+xC][y+yC] = bitArray[x+xC][y+yC].add(bitmap.bitArray[xC][yC]);
            }
        }
    }

    public void subtract(Bitmap bitmap, int x, int y){
        for(int xC = 0; xC < bitmap.bitArray.length; xC++){
            for(int yC = 0; yC < bitmap.bitArray[0].length; yC++) {
                bitArray[x+xC][y+yC] = bitArray[x+xC][y+yC].subtract(bitmap.bitArray[xC][yC]);
            }
        }
    }

    public void set(Bitmap bitmap, int x, int y){
        for(int xC = 0; xC < bitmap.bitArray.length; xC++){
            for(int yC = 0; yC < bitmap.bitArray[0].length; yC++) {
                bitArray[x+xC][y+yC] = bitmap.bitArray[xC][yC];
            }
        }
    }
}
