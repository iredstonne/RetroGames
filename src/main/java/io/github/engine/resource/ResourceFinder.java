package io.github.engine.resource;

import java.net.URL;

public class ResourceFinder {

    private URL url;
    public ResourceLocation resourceLocation;

    public ResourceFinder(String resourcePath) {
        this.url = ClassLoader.getSystemClassLoader().getResource(resourcePath);
        this.resourceLocation = new ResourceLocation(this.url.toString());
    }

    public String getPath() {
        return this.resourceLocation.path;
    }

}
