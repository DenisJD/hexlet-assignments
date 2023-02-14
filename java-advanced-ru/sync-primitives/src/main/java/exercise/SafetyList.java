package exercise;

class SafetyList {
    // BEGIN
    private int[] array = new int[1];

    private int size = 0;

    public synchronized void add(final int num) {
        if (array.length == size) {
            final int[] oldArray = this.array;
            array = new int[size * 2];
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        }
        array[size++] = num;
    }

    public final int get(final int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public final int getSize() {
        return size;
    }
    // END
}
