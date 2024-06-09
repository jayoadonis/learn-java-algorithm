package jayo.arb.learn_j.learn_java_algorithm_lib.sort.merge;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SuppressWarnings("unchecked")
public class MergeSort<T extends Comparable< ? super T>> implements Comparable<MergeSort<T>> {

    public MergeSort(T[] items) {
        this.items = (T[]) new Comparable[items.length];
        this.spaceComplexItem = (T[]) new Comparable[items.length];

        for( int i = 0; i < items.length; ++i )
            this.items[i] = items[i];
    }

    public MergeSort(MergeSort<T> mergeSort) {
        this.items = mergeSort.items.clone();
        this.spaceComplexItem = mergeSort.spaceComplexItem.clone();
    }

    /**
     *
     * @param otherMergeSort RHS object
     *
     * @return
     *  n = -1 LHS len < RHS len. <br>
     *  n = 1 LHS len > RHS len. <br>
     *  n = 0 LHS len == RHS len or the same state and they have the same content. <br>
     *  n = -2 LHS len == RHS len but they don't have the same content according to there order [strict mode enable]. <br>
     *  n > 1 LHS len == RHS len, they don't have the same content, but they do have a number 'n-1' subset(s). [strict mode disable]. <br>
     *
     * @note
     *      If both LHS and RHS contain either null or an empty state the output n = 0. <br>
     *      If n > 1, LHS and RHS len are equal but do not have the same content, however, a subset is found to
     *      get the said subset we should do this operation (n = n-1) or (n-=1).
     *
     * @link {@code jayo.arb.learn_j.learn_java_algorithm_lib.sort.merge.MergeSort<T>.compareTo( MergeSort<T>, boolean )int }
     */
    //REM: TODO-HERE; handle the boilerplate
    @Override
    public int compareTo( MergeSort<T> otherMergeSort ) {
        return this.compareTo( otherMergeSort, false );
    }

    /**
     *
     * @param otherMergeSort RHS object
     * @param isStrict TRUE if we matter the indices order, otherwise, FALSE it compare regardless its order.
     *
     * @return
     *  n = -1 LHS len < RHS len. <br>
     *  n = 1 LHS len > RHS len. <br>
     *  n = 0 LHS len == RHS len or the same state and they have the same content. <br>
     *  n = -2 LHS len == RHS len but they don't have the same content according to there order [strict mode enable]. <br>
     *  n > 1 LHS len == RHS len, they don't have the same content, but they do have a number 'n-1' subset(s). [strict mode disable]. <br>
     *
     * @note
     *      If both LHS and RHS contain either null or an empty state the output n = 0. <br>
     *      If n > 1, LHS and RHS len are equal but do not have the same content, however, a subset is found to
     *      get the said subset we should do this operation (n = n-1) or (n-=1).
     */
    //REM: TODO-HERE; handle the boilerplate
    public int compareTo( MergeSort<T> otherMergeSort, boolean isStrict ) {
        if( otherMergeSort == null ) {
            if( this.items.length > 0 )
                return Compare.GREATER_THAN.CODE;
            return Compare.EQUAL.CODE; //REM: They both have null or empty array.
        }

        int thisItemLen = this.items.length;
        int otherItemsLen = otherMergeSort.items.length;

        if ( thisItemLen < otherItemsLen )
            return Compare.LESS_THAN.CODE; //REM: !TODO-HERE: LHS is the smallest len
        else if ( thisItemLen > otherItemsLen)
            return Compare.GREATER_THAN.CODE; //REM: !TODO-HERE; LHS is the largest len
        else {
            //REM: !TODO-HERE; Implement a not a strict move
            if( !isStrict ) {
                //REM: !TODO-HERE; deep copy the 'otherMergeSort' ready for immutability...
                final List<T> otherMergeSortDeepCopy = Stream
                        .<T>of(otherMergeSort.items)
                        .collect(Collectors.toList());
                //REM: !TODO-HERE; check the content recursively if same...
                for (T thisItem : this.items) {
                    for (T otherItem : otherMergeSortDeepCopy) {
                        int res = 0; //REM: !TODO-HERE; ignore this it will be handle later on...
                        if ((res = thisItem.toString().compareTo(otherItem.toString())) == 0) {
                            //REM: !TODO-HERE; remove LHS item if found same...
                            otherMergeSortDeepCopy.remove(otherItem);
                            break;
                        }
                    }
                }
                //REM: !TODO-HERE; [#SUBSET ITEM], if result to Zero then they are [Eq]
                thisItemLen -= otherMergeSortDeepCopy.size();
            }
            //REM: !TODO-HERE; Implement strict mode
            else {
                for( int i = 0; i < this.items.length; ++i ) {
                    if( this.items[i].toString().compareTo( otherMergeSort.items[i].toString() ) != 0 )
                        return Compare.NOT_EQUAL.CODE; //REM: !TODO-HERE; [NE], only invoke if isStrict == true.
                }
                return Compare.EQUAL.CODE;
            }
        }

        //REM: !TODO-HERE: either n=0 [EQ] or n>1 [#SUBSET ITEM (n-1)]
        return thisItemLen >= 1 ? thisItemLen + 1 : Compare.EQUAL.CODE;
    }

    public void sort() {
        this.sort( MergeSort::ascending );
        return;
    }

    public void sort( Predicate<Integer> predicate ) {
        this.mergeSort( 0, this.items.length - 1, predicate );
        return;
    }

    private void mergeSort( int indexBegin, int indexEnd, Predicate<Integer> predicate ) {
        System.out.printf( "mergeSort( %d, %d )\n", indexBegin, indexEnd );
        if( indexBegin >= indexEnd ) {
            System.out.printf( "return mergeSort( %d, %d )\n", indexBegin, indexEnd );
            return;
        }
        if( this.items == null || this.items.length <= 1 )
            return;

        final int indexMiddle = ( indexBegin + indexEnd ) / 2;

//        System.out.printf("1st level mergeSort, mergeSort( %d, %d )\n",
//                indexBegin, indexMiddle);
        System.out.println("1st level");
        mergeSort( indexBegin, indexMiddle, predicate );
//        System.out.printf("2nd level mergeSort, mergeSort( %d, %d )\n",
//                indexMiddle + 1, indexEnd);
        System.out.println("2nd level");
        mergeSort( indexMiddle + 1, indexEnd, predicate );
        merge( indexBegin, indexMiddle, indexEnd, predicate );
        return;
    }

    private void merge(int indexBegin, int indexMiddle, int indexEnd, Predicate<Integer> predicate ) {
        System.out.printf( "merge( %d, %d, %d )\n", indexBegin, indexMiddle, indexEnd );

        //REM: !TODO-HERE; Assign the temp-array with targeted current elements partition from the prime-array
        for( int i = indexBegin; i <= indexEnd; ++i )
            this.spaceComplexItem[i] = this.items[i];

        //REM: !TODO-HERE; Set the current LHS index offset partition
        int indexLeftOffsetBegin = indexBegin;
        //REM: !TODO-HERE; Set the current RHS index offset partition
        int indexRightOffsetBegin = indexMiddle + 1;
        //REM: !TODO-HERE; Set the index offset of the prime-array...
        int indexK = indexBegin;

        while( indexLeftOffsetBegin <= indexMiddle && indexRightOffsetBegin <= indexEnd ) {
            //REM: !TODO-HERE; change it into a higher level function/method or LAMBDA for order-sorting operation
            boolean isAsc;
            final String LHS_VALUE = this.spaceComplexItem[indexLeftOffsetBegin].toString().toLowerCase();
            final String RHS_VALUE = this.spaceComplexItem[indexRightOffsetBegin].toString().toLowerCase();
            try {
                //REM: !TODO-HERE; Initially check whether it can parse it to an Integer wrapper type...
                isAsc = predicate.test(
                        Integer.valueOf(LHS_VALUE).compareTo(Integer.valueOf(RHS_VALUE))
                );
            }
            catch ( Exception e ) {
                //REM: !TODO-HERE; Fall-through accept everything as String...
                isAsc = predicate.test(
                        LHS_VALUE.compareTo(RHS_VALUE)
                );
                assert false : "[INFO] Switching to 'String comparison'.";
            }

            this.items[indexK++] = isAsc
                        ? this.spaceComplexItem[indexLeftOffsetBegin++]
                        : this.spaceComplexItem[indexRightOffsetBegin++];
        }

        //REM: !TODO-HERE; Retrieve LHS partition leftover elements
        while( indexLeftOffsetBegin <= indexMiddle )
            this.items[indexK++] = this.spaceComplexItem[indexLeftOffsetBegin++];

        //REM: !TODO-HERE; Retrieve RHS partition leftover elements
        while( indexRightOffsetBegin <= indexEnd )
            this.items[indexK++] = this.spaceComplexItem[indexRightOffsetBegin++];

        return;
    }

    public static boolean ascending( int x ) {
        return x <= 0;
    }

    public static boolean descending( int x ) {
        return x >= 0;
    }

    //REM: TODO-HERE; Do we really need to sync?
    //REM: TODO-HERE; Ignore the "throws InterruptedException"
    //REM: TODO-HERE; ~ it is for the future arch-design...
    public T[] getItems() throws InterruptedException {
        synchronized(this.items) { //REM: Do we really need to sync?
            final T[] newItems = (T[]) new Comparable[this.items.length];

            int i = 0;
            for (T targetItem : this.items)
                newItems[i++] = targetItem;

            return newItems;
        }
    }

    /**
     * Check equality by its content and order/indices
     *
     * @param obj {@code java.lang.Object}
     * @return boolean
     */
    @Override
    public boolean equals(final Object obj) {
        //REM: TODO-HERE; At param {@code isStrict} do such this
        //REM: TODO-HERE; ~ new Strict<boolean, Comparable<String>, boolean>
        //REM: TODO-HERE; ~ ( true,
        //REM: TODO-HERE; ~   (x, y) -> x.toString().toLowerCase()
        //REM: TODO-HERE; ~     .compareTo( y.toString().toLowerCase() ) <= 0
        //REM: TODO-HERE; ~ )
        return this.equals(obj, false);
    }

    /**
     * Check equality by its content either by order/indices (not Strict)
     * or by its content recursively, case-sensitivity automatically kick-in if
     * it is an instance of CharSequence (strictly).
     *
     * @param obj      {@code java.lang.Object} It is the RHS to be compare with LHS
     * @param isStrict {@code boolean} TRUE want to check equality by content recursively, otherwise FALSE
     *                 if checking equality by its content and order/indices.
     * @return boolean {@code TRUE} equality meet, otherwise, {@code FALSE}
     */
    //REM: TODO-HERE; The param {@code isStrict} change it to atomic properties such as
    //REM: TODO-HERE; ~ {@code Strict<T,I,O>.setIsStrict(T)V} and {@code Strict<T,I,O>.setBut(BiPredicate<I,I>)O}
    public boolean equals(final Object obj, boolean isStrict) {
//        if (obj == null) //REM: We can just remove this conditional-statement
//            return false; //REM: ~ we can just use/replace it with the 'instanceof' operator
        if (this == obj)
            return true;
        if (!(obj instanceof MergeSort))
            return false;

        final MergeSort<T> otherMergeSort = (MergeSort<T>) obj;

        return this.compareTo(otherMergeSort, isStrict) == 0;
    }

    //REM: TODO-HERE; should we continue this atomic 2nd param?
    public boolean equals(final Object obj, Strict<T> strict) {
//        if (obj == null) //REM: We can just remove this conditional-statement
//            return false; //REM: ~ we can just use/replace it with the 'instanceof' operator
        if (this == obj)
            return true;
        if (!(obj instanceof MergeSort))
            return false;

        final MergeSort<T> otherMergeSort = (MergeSort<T>) obj;

        //REM: TODO-HERE; Should we implement another 3rd param (atomic/wrapper class) on this specific method
        return this.compareTo(otherMergeSort, strict.IS_STRICT ) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = (int) ((hash + Arrays.toString(this.items).hashCode()) * 31L);
        return hash;
    }

    @Override
    public String toString() {
        return String.format(
                "%s[size=%d]",
                super.toString().replace("@", String.format("<%s>", this.items.getClass().getTypeName())),
                this.items.length
        );
    }

//    public static enum Sort {
//        DESCENDING,
//        ASCENDING
//    }

    private final T[] items;
    private final T[] spaceComplexItem;

    private boolean cacheSortingState;
    private boolean primeSortingState;
}
