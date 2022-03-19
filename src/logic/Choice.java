package logic;

public class Choice {
    public final Command.ColumnType type;
    public final int index;
    public final Color color;

    public Choice(Command.ColumnType type, int index, Color color) {
        this.type = type;
        this.index = index;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "type=" + type +
                ", index=" + index +
                ", color=" + color +
                '}';
    }
}
