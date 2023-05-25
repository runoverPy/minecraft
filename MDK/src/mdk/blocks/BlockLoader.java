package mdk.blocks;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface BlockLoader {
    BlockLoader register(String registryName, Supplier<? extends BlockBase> toRegister);

    BlockLoader subPackage(String packageName, Consumer<BlockLoader> packageContents);
}
