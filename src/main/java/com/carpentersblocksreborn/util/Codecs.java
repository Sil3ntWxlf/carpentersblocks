package com.carpentersblocksreborn.util;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3f;

import java.util.List;

public class Codecs {
    public static final Codec<Vector2f> VEC2 = Codec.FLOAT.listOf().comapFlatMap(Codecs::parseVec2, vector3f -> ImmutableList.of(vector3f.x, vector3f.y));
    public static final Codec<Vector3f> VEC3 = Codec.FLOAT.listOf().comapFlatMap(Codecs::parseVec3, vector3f -> ImmutableList.of(vector3f.getX(), vector3f.getY(), vector3f.getZ()));

    private static DataResult<Vector2f> parseVec2(List<Float> list) {
        try {
            if (list.size() > 1) {
                return DataResult.success(new Vector2f(list.get(0), list.get(1)));
            } else {
                return DataResult.error("Array of floats was too small (less than 2) for deserializing");
            }
        } catch (Throwable t) {
            return DataResult.error("Exception while deserializing to Vector2f: " + t.getMessage());
        }
    }

    private static DataResult<Vector3f> parseVec3(List<Float> list) {
        try {
            if (list.size() > 2) {
                return DataResult.success(new Vector3f(list.get(0), list.get(1), list.get(2)));
            } else {
                return DataResult.error("Array of floats was too small (less than 3) for deserializing");
            }
        } catch (Throwable t) {
            return DataResult.error("Exception while deserializing to Vector3f: " + t.getMessage());
        }
    }
}
