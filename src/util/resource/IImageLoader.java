package util.resource;

import java.awt.Image;

@FunctionalInterface
public interface IImageLoader {
    Image getImage(ImageResource type);
}
