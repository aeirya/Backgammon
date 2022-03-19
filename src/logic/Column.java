package logic;

public class Column {
    private int numberOfCheckers;
    private Color color;
    public boolean addChecker(Color color){
        if (this.color == color){
            numberOfCheckers++;
            return true;
        }
        if (numberOfCheckers <= 1){
            this.color = color;
            numberOfCheckers = 1;
            return true;
        }
        return false;
    }

    public int getNumberOfCheckers() {
        return numberOfCheckers;
    }

    public Color getColor() {
        return color;
    }

    public Column(Color color, int numberOfCheckers) {
        this.color = color;
        this.numberOfCheckers = numberOfCheckers;
    }
}
