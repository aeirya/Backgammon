package util.resource;

public class ResourcePathFinder {
    public String getName(ImageResource type) {
        switch (type) {
            case WHITE_CHECKER:
                return "whiteChecker.png";
            case BLACK_CHECKER:
                return "blackChecker.png";
            case WHITE_CHECKER_VERTICAL:
                return "whiteCheckerVertical.png";
            case BLACK_CHECKER_VERTICAL:
                return "blackCheckerVertical.png";
            case BACKGROUND:
                return "GameBackGround.png";
            default:
                return type.toString();
        }
    }
}
