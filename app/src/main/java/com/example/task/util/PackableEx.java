package com.example.task.util;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
