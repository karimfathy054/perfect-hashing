import java.util.ArrayList;
import java.util.Scanner;

public class NSquareHashTable<T> {
    //    class Entry<T> {
//        int key;
//        T value;
//        public Entry(int key, T value) {
//            this.key = key;
//            this.value = value;
//        }
//    }
     int dictionarySize;
     int tableSize;
     Entry<T>[] table;
     int hashMatrixSize;
     int keyBitSize;
     int inputSize;
     int rehashings;
     UniversalHasher hasher;

    public NSquareHashTable() {//constructor for small dynamic hashTable
        this.dictionarySize = 1;
        this.keyBitSize = 32;
        int nSquare = dictionarySize * dictionarySize;
        this.hashMatrixSize = (int) Math.ceil(Math.log(nSquare) / Math.log(2));
        this.tableSize = (int) Math.pow(2, hashMatrixSize);
        this.table = new Entry[this.tableSize];
        this.inputSize = 0;
        this.rehashings = 0;
        this.hasher = new UniversalHasher(hashMatrixSize, keyBitSize);
    }

    public boolean insertDynamicaly(int key, T value) {
        Entry<T> entry = new Entry<>(key, value);
        int hashingIndex = hasher.getHashedindex(key);
        if (table[hashingIndex] != null && table[hashingIndex].key == key) {//same key is inserted twice
            return false;
        }
        if (inputSize == dictionarySize) {//size won't fit
            this.dictionarySize++;
            int nSquare = dictionarySize * dictionarySize;
            this.hashMatrixSize = (int) Math.ceil(Math.log(nSquare) / Math.log(2));
            this.tableSize = (int) Math.pow(2, hashMatrixSize);
            while (!rehashAndInsert(entry)) {
                this.rehashings += 1;
            }
            this.inputSize++;
            return true;
        }
        if (table[hashingIndex] == null) {//no collision
            table[hashingIndex] = entry;
        } else {//collision
            while (!rehashAndInsert(entry)) {
                this.rehashings += 1;
            }
        }

        this.inputSize++;
        return true;
    }

    public boolean insertDynamicaly(Entry<T> entry) {
        int hashingIndex = hasher.getHashedindex(entry.key);
        if (table[hashingIndex] != null && table[hashingIndex].key == entry.key) {//same key is inserted twice
            return false;
        }
        if (inputSize == dictionarySize) {//size won't fit
            this.dictionarySize++;
            int nSquare = dictionarySize * dictionarySize;
            this.hashMatrixSize = (int) Math.ceil(Math.log(nSquare) / Math.log(2));
            this.tableSize = (int) Math.pow(2, hashMatrixSize);
            while (!rehashAndInsert(entry)) {
                this.rehashings += 1;
            }
            this.inputSize++;
            return true;
        }
        if (table[hashingIndex] == null) {//no collision
            table[hashingIndex] = entry;
        } else {//collision
            while (!rehashAndInsert(entry)) {
                this.rehashings += 1;
            }
        }

        this.inputSize++;
        return true;
    }
    public NSquareHashTable(int dectionarySize, int keyBitSize) {
        this.dictionarySize = dectionarySize;
        this.keyBitSize = keyBitSize;
        int nSquare = dectionarySize * dectionarySize;
        this.hashMatrixSize = (int) Math.ceil(Math.log(nSquare) / Math.log(2));
        this.tableSize = (int) Math.pow(2, hashMatrixSize);
        this.table = new Entry[this.tableSize];
        this.inputSize = 0;
        this.rehashings = 0;
        this.hasher = new UniversalHasher(hashMatrixSize, keyBitSize);
    }

    public boolean insert(int key, T value) {
        if (inputSize == dictionarySize)
            return false;
        Entry<T> entry = new Entry<>(key, value);
        int hashingIndex = hasher.getHashedindex(key);
        if (table[hashingIndex] == null) {//no collision
            table[hashingIndex] = entry;
        } else if (table[hashingIndex].key == key) {//same key is inserted twice
            return false;
        } else {//collision
            while (!rehashAndInsert(entry)) {
                this.rehashings += 1;
            }
        }
        this.inputSize++;
        return true;
    }

    private boolean rehashAndInsert(Entry e) {
        Entry[] temp = new Entry[this.tableSize];
        this.hasher = new UniversalHasher(this.hashMatrixSize, this.keyBitSize);
        for (int i = 0; i < table.length; i++) {
            if (this.table[i] == null)
                continue;
            int hashingIndex = hasher.getHashedindex(this.table[i].key);
            if (temp[hashingIndex] == null) {//no collision
                temp[hashingIndex] = this.table[i];
            } else {//collision
                return false;
            }
        }
        int hashingIndex = hasher.getHashedindex(e.key);
        if (temp[hashingIndex] == null) {//no collision
            temp[hashingIndex] = e;
        } else {//collision for the new entry
            return false;
        }
        this.table = temp;
        return true;
    }

    public boolean search(int key, T value) {
        int hashingIndex = hasher.getHashedindex(key);
        if (table[hashingIndex] != null && table[hashingIndex].key == key && table[hashingIndex].value.equals(value)) {//////may inc order
            return true;
        }
        return false;
    }

    public boolean delete(int key, T value) {
        if (search(key, value)) {
            int hashingIndex = hasher.getHashedindex(key);
            table[hashingIndex] = null;
            this.inputSize--;
            return true;
        }
        return false;
    }

    int get_ind(int key) {
        return hasher.getHashedindex(key);
    }

    boolean isEmpty(){
        return inputSize==0;
    }

    ArrayList<Entry<T>> getEntries(){
        ArrayList<Entry<T>> allEntries = new ArrayList<>();
        for (Entry<T> entry : this.table) {
            if(entry == null)
                continue;
            allEntries.add(entry);
        }
        return allEntries;
    }

    public int getDictionarySize() {
        return dictionarySize;
    }

    public int getTableSize() {
        return tableSize;
    }

    public Entry<T>[] getTable() {
        return table;
    }

    public int getHashMatrixSize() {
        return hashMatrixSize;
    }

    public int getKeyBitSize() {
        return keyBitSize;
    }

    public int getInputSize() {
        return inputSize;
    }

    public int getRehashings() {
        return rehashings;
    }

    public UniversalHasher getHasher() {
        return hasher;
    }

    public static void main(String[] args) {
        class x{
            int z;
            int y;
            public x(int z, int y) {
                this.z = z;
                this.y = y;
            }
            
        }
        x[] arr = new x[10];
        arr[5] = new x(0, 0)    ;
        arr[6] = new x(8, 8);
        ArrayList<x> sa = new ArrayList<>();
        for (x x : arr) {
            if(x==null)
                continue;
            sa.add(x);
        }
        System.out.println("kemo");
        int[] arra = new int [10];
        arra[5]=5;
        arra[7]=2;
        for (int x : arra) {
            System.out.println(x);
        }
    }
    
}