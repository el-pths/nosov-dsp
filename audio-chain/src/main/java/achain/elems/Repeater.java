package achain.elems;

public class Repeater extends ChainElement {

    public void nextValue(int value) {
        follower.nextValue(value);
    }

}
