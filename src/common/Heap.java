package common;

/**
 * 堆
 */
public class Heap {

    private int size;

    public Heap(int size){
        this.size = size;
    }

    public Heap(int size, int[] values){
        this.size = size;
        init(values);
    }

    private void init(int[] values){
        if (values.length > size){
            throw new IllegalStateException("Init values length more than size.");
        }

    }

    public void put(int value){

    }

}
