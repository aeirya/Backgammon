package logic;

import java.util.Random;

public class Die {
    private int number;
    Random generator = new Random();
    public void roll(){
        number = generator.nextInt(6) + 1;
    }
    public Die(){
        roll();
    }

    public int getNumber() {
        return number;
    }
}
