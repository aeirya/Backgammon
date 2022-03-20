package logic;

public abstract class Rule {
    private Game game;
    private Rule next;
    public boolean check(Command command){
        if (!isValid(command))
            return false;
        if (next == null)
            return true;
        return next.check(command);
    }
    public abstract boolean isValid(Command command);
    public Rule(Game game){
        this.game = game;
    }

    public void setNext(Rule next) {
        this.next = next;
    }
}
