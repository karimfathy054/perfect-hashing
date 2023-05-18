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
    int dectionarySize;
    int tableSize;
    Entry<T>[] table;
    int hashMatrixSize;
    int keyBitSize;
    int inputSize;
    int rehashings;
    UniversalHasher hasher;

    public NSquareHashTable() {//constructor for small dynamic hashTable
        this.dectionarySize = 1;
        this.keyBitSize = 32;
        int nSquare = dectionarySize * dectionarySize;
        this.hashMatrixSize = (int) Math.ceil(Math.log(nSquare) / Math.log(2));
        this.tableSize = (int) Math.pow(2, hashMatrixSize);
        this.table = new Entry[this.tableSize];
        this.inputSize = 0;
        this.rehashings = 0;
        this.hasher = new UniversalHasher(hashMatrixSize, keyBitSize);
    }

    boolean insertDynamicaly(int key, T value) {
        Entry<T> entry = new Entry<>(key, value);
        int hashingIndex = hasher.getHashedindex(key);
        if (table[hashingIndex] != null && table[hashingIndex].key == key) {//same key is inserted twice
            return false;
        }
        if (inputSize == tableSize) {//size won't fit
            this.dectionarySize++;
            int nSquare = dectionarySize * dectionarySize;
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
        this.dectionarySize = dectionarySize;
        this.keyBitSize = keyBitSize;
        int nSquare = dectionarySize * dectionarySize;
        this.hashMatrixSize = (int) Math.ceil(Math.log(nSquare) / Math.log(2));
        this.tableSize = (int) Math.pow(2, hashMatrixSize);
        this.table = new Entry[this.tableSize];
        this.inputSize = 0;
        this.rehashings = 0;
        this.hasher = new UniversalHasher(hashMatrixSize, keyBitSize);
    }

    boolean insert(int key, T value) {
        if (inputSize == tableSize)
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

    boolean rehashAndInsert(Entry e) {
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

    boolean search(int key, T value) {
        int hashingIndex = hasher.getHashedindex(key);
        if (table[hashingIndex] != null && table[hashingIndex].key == key && table[hashingIndex].value.equals(value)) {//////may inc order
            return true;
        }
        return false;
    }

    boolean delete(int key, T value) {
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
}