import java.util.ArrayList;

/**
 * perfectHashTable
 */
public class perfectHashTable<T> {

    int primaryTableSize;
    NSquareHashTable<T>[] primaryTable;
    int[] count;
    int primarydictionarySize;
    int primaryHashMatrixSize;
    int keyBitSize;
    int primaryTableRehashes;
    int secondryTablesRehashes;

    UniversalHasher primaryHasher;

    public perfectHashTable(int primarydictionarySize,int keyBitSize) {
        this.primarydictionarySize = primarydictionarySize;
        this.keyBitSize = keyBitSize;
        this.primaryHashMatrixSize = (int) Math.ceil(Math.log(primaryTableSize) / Math.log(2));
        this.primaryTableSize = (int) Math.pow(2, primaryHashMatrixSize);
        this.primaryTable = new NSquareHashTable[primaryTableSize];
        this.count = new int[primaryTableSize] ;
        this.primaryTableRehashes = 0;
        this.secondryTablesRehashes = 0;
        this.primaryHasher = new UniversalHasher(this.primaryHashMatrixSize, keyBitSize);
    }

    boolean insert(int key,T value){
        int primaryIndex = primaryHasher.getHashedindex(key);
        if(primaryTable[primaryIndex] == null){//cell has no bucket + no collision
            primaryTable[primaryIndex] = new NSquareHashTable<>();
            int oldValue = primaryTable[primaryIndex].rehashings;
            primaryTable[primaryIndex].insertDynamicaly(key, value);
            this.secondryTablesRehashes+= primaryTable[primaryIndex].rehashings-oldValue;
            this.count[primaryIndex] ++;
            // loadFactor+=1;
        }else{//cell has a bucket
            int oldValue = primaryTable[primaryIndex].rehashings;
            if(!primaryTable[primaryIndex].insertDynamicaly(key, value)){//same key is inserted twice
                return false;
            }
            this.secondryTablesRehashes+= primaryTable[primaryIndex].rehashings-oldValue;
            this.count[primaryIndex] ++;
        }
        // should implement the next section in a better way

        int countSum = 0;
        for (int i : count) {
            countSum += i*i;                    
        }
        if(countSum>=2*primaryTableSize){
            //rehash
            while (!primaryRehash()) {
                this.primaryTableRehashes++;
            }
        }
        return true;
    }

    boolean primaryRehash(){
        this.primaryHasher = new UniversalHasher(this.primaryHashMatrixSize, this.keyBitSize);
        ArrayList<Entry<T>> allEntries = getEntries();
        this.primaryTable = new NSquareHashTable[this.primaryTableSize];
        this.count = new int[this.primaryTableSize];
        for (Entry<T> entry : allEntries) {
            int primaryIndex = primaryHasher.getHashedindex(entry.key);
            if(primaryTable[primaryIndex] == null){//cell has no bucket
                primaryTable[primaryIndex] = new NSquareHashTable<>();
            }
            int oldValue = primaryTable[primaryIndex].rehashings;
            primaryTable[primaryIndex].insertDynamicaly(entry);
            this.secondryTablesRehashes+= primaryTable[primaryIndex].rehashings-oldValue;
            this.count[primaryIndex] ++;
        }
        // should implement the next section in a better way rather than O(n)
        int countSum = 0;
        for (int i : count) {
            countSum += i*i;                    
        }
        if(countSum>=2*primaryTableSize){
            return false;
        }
        return true;
    }

    ArrayList<Entry<T>> getEntries(){
        ArrayList<Entry<T>> allEntries = new ArrayList<>();
        for (NSquareHashTable<T> bucket : this.primaryTable) {
            if(bucket==null)
                continue;
            allEntries.addAll(bucket.getEntries());
        }
        return allEntries;
    }

    boolean search(int key, T value){
        int primaryIndex = primaryHasher.getHashedindex(key);
        if(primaryTable[primaryIndex] != null){//bucket available
            return primaryTable[primaryIndex].search(key, value);//search in secomdry level
        }
        return false;
    }
    
    boolean delete(int key, T value){
        int primaryIndex = primaryHasher.getHashedindex(key);
        if(primaryTable[primaryIndex] == null){//bucket available
            return false;
        }
        if(!primaryTable[primaryIndex].delete(key, value)){//delete in secomdry level
            return false;
        }
        this.count[primaryIndex]--;
        return true;
    }
    

}