package jayo.arb.learn_j.learn_java_algorithm.lib.sort.merge;

import java.util.function.BiPredicate;

public class Strict<I extends Comparable<? super I>> {
    public Strict(boolean isStrict, BiPredicate<I,I> but ) {
        this.IS_STRICT = isStrict;
        this.BUT = but;
    }

    public final boolean IS_STRICT;
    public final BiPredicate<I,I> BUT;
}
