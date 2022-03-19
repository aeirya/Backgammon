package util.resource;

import java.awt.Image;
import java.awt.Toolkit;

public class ResourceManager {
    
    private static final String RESOURCES_DIR = "resources/";

    private static ResourceManager instance = new ResourceManager();
   
    private final ResourcePathFinder finder;
    private final ResourceCache cache;

    private ResourceManager() {
        finder = new ResourcePathFinder();
        cache = new ResourceCache();
    }

    public static Image get(ImageResource type) {
        return getInstance().getImage(type);
    }
    
    private static ResourceManager getInstance() {
        return instance;
    }

    private Image getImage(ImageResource type) {
        return cache.tryGet(type,
            t -> Toolkit.getDefaultToolkit().getImage(getResourcePath(t))
        );
    }

    private String getResourcePath(ImageResource type) {
        return RESOURCES_DIR + finder.getName(type);
    }
}

