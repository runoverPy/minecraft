package mdk.blocks;

import java.util.function.Supplier;

public interface BlockLoader {
    BlockLoader register(String registryName, Supplier<? extends BlockBase> toRegister);
}
