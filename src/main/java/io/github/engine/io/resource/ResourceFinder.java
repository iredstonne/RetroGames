package io.github.engine.io.resource;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jetbrains.annotations.NotNull;

public class ResourceFinder {

    private final ClassLoader classLoader;

    public ResourceFinder() {
        this.classLoader = ClassLoader.getSystemClassLoader();
    }

    public Path findResource(@NotNull ResourceLocation location) {
        return Paths.get(this.classLoader.getResource(location.path()).getPath());
    }

}
