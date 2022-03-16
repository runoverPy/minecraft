package game.mechanics.inventory;

public class Hotbar {
    private final ItemStack<?>[] holding;
    private int selectedSlot;



    public Hotbar() {
        this.holding = new ItemStack[9];
        
    }

    public ItemStack<?> getSelectedStack() {
        return holding[selectedSlot];
    }

    public void insertStack(ItemStack<?> stack, int index) {
        checkSlot(index);
        holding[index] = stack;
    }

    public ItemStack<?> replaceStack(ItemStack<?> stack, int index) {
        checkSlot(index);
        ItemStack<?> tmp = holding[index];
        holding[index] = stack;
        return tmp;
    }

    public void select(int slot) {
        checkSlot(slot);
        selectedSlot = slot;
    }

    public void incSelect() {
        if (selectedSlot == 8) selectedSlot = 0;
        else selectedSlot++;
    }

    public void decSelect() {
        if (selectedSlot == 0) selectedSlot = 8;
        else selectedSlot--;
    }

    private void checkSlot(int slot) throws RuntimeException {
        if (0 > slot || slot > 8) throw new RuntimeException("Invalid value " + slot + ": only values between 0 and 8 allowed");
    }

    public void draw() {}
}
