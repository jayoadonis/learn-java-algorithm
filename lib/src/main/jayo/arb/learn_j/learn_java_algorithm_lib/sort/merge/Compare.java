package jayo.arb.learn_j.learn_java_algorithm_lib.sort.merge;

public enum Compare {
    NOT_EQUAL(-2),
    LESS_THAN(-1),
    EQUAL( 0 ),
    GREATER_THAN( 1 );

    public final int CODE;

    private Compare( int code ) {
        this.CODE = code;
    }
};
