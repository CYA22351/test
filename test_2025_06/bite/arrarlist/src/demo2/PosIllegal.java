package demo2;

public class PosIllegal extends RuntimeException{
    public PosIllegal(){

    }
    public PosIllegal(String s){
        super(s);
    }
}
