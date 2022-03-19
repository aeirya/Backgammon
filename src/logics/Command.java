package logics;

public class Command {
    public enum columnType{
        middle, bar, point
    }

    public Choice source, destination;

    public Command(Choice source, Choice destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Command{" +
                "source=" + source +
                ", destination=" + destination +
                '}';
    }
}
