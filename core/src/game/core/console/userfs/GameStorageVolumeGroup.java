package game.core.console.userfs;

public class GameStorageVolumeGroup {
//    private final int index; // in blocks, absolute, signifying the beginning block of this group. The group is assumed to be contiguous in memory.
//    private final int header_index; // in blocks, relative to index, signifying the block containing the header of this group

    private final int groupSize;

    public GameStorageVolumeGroup(int groupSize) {
        this.groupSize = groupSize;
    }

    public GameStorageVolumeGroup() {
        this(GameStorageVolume.GROUP_SIZE);
    }
}
