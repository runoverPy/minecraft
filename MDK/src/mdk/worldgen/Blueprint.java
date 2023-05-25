package mdk.worldgen;

public interface Blueprint {
    void addField(String fieldName, GeneratorSetting<?> setting);
}
