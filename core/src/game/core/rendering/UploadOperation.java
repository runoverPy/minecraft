package game.core.rendering;

class UploadOperation {
    private Runnable operation;

    public synchronized void setOperation(Runnable runnable) {
        this.operation = runnable;
    }

    public synchronized void runIfPossible() {
        if (operation != null) {
            operation.run();
            operation = null;
        }
    }
}
