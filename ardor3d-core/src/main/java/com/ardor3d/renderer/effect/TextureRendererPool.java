/**
 * Copyright (c) 2008-2012 Bird Dog Games, Inc..
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <http://www.ardor3d.com/LICENSE>.
 */

package com.ardor3d.renderer.effect;

import java.util.Iterator;
import java.util.List;

import com.ardor3d.renderer.Renderer;
import com.ardor3d.renderer.texture.TextureRenderer;
import com.google.common.collect.Lists;

public enum TextureRendererPool {
    INSTANCE;

    private final List<TextureRenderer> renderers = Lists.newLinkedList();

    public static TextureRenderer fetch(final int width, final int height, final Renderer renderer) {
        for (final Iterator<TextureRenderer> it = INSTANCE.renderers.iterator(); it.hasNext();) {
            final TextureRenderer texRend = it.next();
            if (texRend.getWidth() == width && texRend.getHeight() == height) {
                it.remove();
                return texRend;
            }
        }

        // none found, make one
        return renderer.createTextureRenderer(width, height, 0, 0);
    }

    public static void release(final TextureRenderer texRend) {
        INSTANCE.renderers.add(texRend);
    }
}
