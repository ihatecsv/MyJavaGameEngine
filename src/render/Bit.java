package render;

public class Bit {
    public int r;
    public int g;
    public int b;

    public Bit(){
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public Bit(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Bit add(Bit bit){
        Bit rBit = new Bit();
        rBit.r = (r+bit.r<=255) ? r+bit.r : 255;
        rBit.g = (g+bit.g<=255) ? g+bit.g : 255;
        rBit.b = (b+bit.b<=255) ? b+bit.b : 255;
        return rBit;
    }

    public Bit subtract(Bit bit){
        Bit rBit = new Bit();
        rBit.r = (r-bit.r>=0) ? r-bit.r : 0;
        rBit.g = (g-bit.g>=0) ? g-bit.g : 0;
        rBit.b = (b-bit.b>=0) ? b-bit.b : 0;
        return rBit;
    }

    public Bit multiply(Bit bit){
        Bit rBit = new Bit();
        rBit.r = (r*bit.r<=255) ? r*bit.r : 255;
        rBit.g = (g*bit.g<=255) ? g*bit.g : 255;
        rBit.b = (b*bit.b<=255) ? b*bit.b : 255;
        return rBit;
    }

    public Bit divide(Bit bit){
        Bit rBit = new Bit();
        if(bit.r != 0){rBit.r = (r/bit.r>=0) ? r/bit.r : 0;}
        if(bit.g != 0){rBit.g = (g/bit.g>=0) ? g/bit.g : 0;}
        if(bit.b != 0){rBit.b = (b/bit.b>=0) ? b/bit.b : 0;}
        return rBit;
    }
}
