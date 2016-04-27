package render;

import exception.InvalidBitmapException;

public class Bitmap{
    public Bit[] bitArray;
    public int width;
    public int height;

    public Bitmap(int width, int height){
        this.bitArray = new Bit[width*height];
        this.width = width;
        this.height = height;
    }

    public Bitmap(Bit[] bitArray, int width, int height) throws exception.InvalidBitmapException{
        if(bitArray.length != width*height) throw new InvalidBitmapException();
        this.bitArray = bitArray;
        this.width = width;
        this.height = height;
    }

    public void add(Bitmap bitmap, int x, int y){

    }

    public void subtract(Bitmap bitmap, int x, int y){

    }
}
