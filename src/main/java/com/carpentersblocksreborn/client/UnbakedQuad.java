package com.carpentersblocksreborn.client;

import com.carpentersblocksreborn.util.Codecs;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3f;

public class UnbakedQuad {
    public static final Codec<UnbakedQuad> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.VEC3.fieldOf("normal").forGetter(quad -> quad.normal),
            Vertex.CODEC.listOf().fieldOf("vertices").forGetter(quad -> ImmutableList.of(quad.vertex0, quad.vertex1, quad.vertex2, quad.vertex3))
            /* FIXME Is there a better way of boxing the vertices into a DataResult so
                if a crash occurs it'd be easier to identify with a logged output with a DataResult.error?
                We have to assume the list would be of 4 otherwise. */
    ).apply(instance, (normal, vertices) -> new UnbakedQuad(normal, vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3))));

    private final Vector3f normal;
    private final Vertex vertex0, vertex1, vertex2, vertex3;

    public UnbakedQuad(Vector3f normal, Vertex vertex0, Vertex vertex1, Vertex vertex2, Vertex vertex3) {
        this.normal = normal;

        this.vertex0 = vertex0;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public static class Vertex {
        private static final Codec<Vertex> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codecs.VEC3.fieldOf("position").forGetter(vertex -> vertex.position),
                Codecs.VEC2.fieldOf("uv").forGetter(vertex -> vertex.uv)
        ).apply(instance, Vertex::new));

        private final Vector3f position;
        private final Vector2f uv;

        public Vertex(Vector3f position, Vector2f uv) {
            this.position = position;
            this.uv = uv;
        }
    }
}
