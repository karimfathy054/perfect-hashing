
public class NSquareHashTable<T> {
    class Entry<T>{
        int key;
        T value;
        public Entry(int key, T value) {
            this.key = key;
            this.value = value;
        }
    }
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
        int nSquare = dectionarySize*dectionarySize;
        this.hashMatrixSize = (int)Math.ceil(Math.log(nSquare)/Math.log(2));
        this.tableSize = (int)Math.pow(2,hashMatrixSize);
        this.table = new Entry[this.tableSize];
        this.inputSize = 0;
        this.rehashings = 0;
        this.hasher = new UniversalHasher(hashMatrixSize,keyBitSize);
    }

    boolean insertDynamicaly(int key,T value){
        Entry<T> entry = new Entry<>(key, value);
        int hashingIndex = hasher.getHashedindex(key);
        if(table[hashingIndex] !=null && table[hashingIndex].key==key){//same key is inserted twice
            return false;
        }
        if(inputSize==tableSize){//size won't fit
            this.dectionarySize++;
            int nSquare = dectionarySize*dectionarySize;
            this.hashMatrixSize = (int)Math.ceil(Math.log(nSquare)/Math.log(2));
            this.tableSize = (int)Math.pow(2,hashMatrixSize);
            while(!rehashAndInsert(entry)){
                this.rehashings +=1;
            }
            this.inputSize++;
            return true;
        }        
        if(table[hashingIndex]==null){//no collision
            table[hashingIndex]= entry;
        }
        else{//collision
            while(!rehashAndInsert(entry)){
                this.rehashings +=1;
            }
        }
        
        this.inputSize++;
        return true;
    }
    public NSquareHashTable(int dectionarySize, int keyBitSize) {
        this.dectionarySize = dectionarySize;
        this.keyBitSize = keyBitSize;
        int nSquare = dectionarySize*dectionarySize;
        this.hashMatrixSize = (int)Math.ceil(Math.log(nSquare)/Math.log(2));
        this.tableSize = (int)Math.pow(2,hashMatrixSize);
        this.table = new Entry[this.tableSize];
        this.inputSize = 0;
        this.rehashings = 0;
        this.hasher = new UniversalHasher(hashMatrixSize,keyBitSize);
    }
    boolean insert(int key , T value){
        if(inputSize==tableSize)
            return false;
        Entry<T> entry = new Entry<>(key, value);
        int hashingIndex = hasher.getHashedindex(key);
        if(table[hashingIndex]==null){//no collision
            table[hashingIndex]= entry;
        }
        else if(table[hashingIndex].key==key){//same key is inserted twice
            return false;
        }
        else{//collision
            while(!rehashAndInsert(entry)){
                this.rehashings +=1;
            }
        }
        this.inputSize++;
        return true;
    }
    boolean rehashAndInsert(Entry e){
        Entry[] temp = new Entry[this.tableSize];
        this.hasher = new UniversalHasher(this.hashMatrixSize,this.keyBitSize);
        for (int i = 0; i < table.length; i++) {
            if(this.table[i]==null)
                continue;
            int hashingIndex = hasher.getHashedindex(this.table[i].key);
            if(temp[hashingIndex]==null){//no collision
                temp[hashingIndex]=this.table[i];
            }
            else{//collision
                return false;
            }
        }
        int hashingIndex = hasher.getHashedindex(e.key);
        if(temp[hashingIndex]==null){//no collision
            temp[hashingIndex]= e;
        }
        else{//collision for the new entry
            return false;
        }
        this.table= temp;
        return true;
    }
    boolean search(int key,T value){
        int hashingIndex = hasher.getHashedindex(key);
        if(table[hashingIndex]!= null && table[hashingIndex].key==key && table[hashingIndex].value.equals(value)){
            return true;
        }
        return false;
    }

    boolean delete(int key,T value){
        if(search(key, value)){
            int hashingIndex = hasher.getHashedindex(key);
            table[hashingIndex]= null;
            this.inputSize--;
            return true;
        }
        return false;
    }

    // public static void main(String[] args) {
    //     int [] arr = new int[0];
    //     String[] values = {"vsdhvbjh","vfdhnihnsdkjo","aseowjimnci","ckhbiashion"};
    //     int[] keys = new int[values.length];
    //     int i =  0;
    //     for (String string : values) {
    //         keys[i++]= string.hashCode();
    //     }
    //     NSquareHashTable<String> hashtable = new NSquareHashTable<>(1, 32);
    //     System.out.println(hashtable.insert(keys[0], values[0]));
    //     System.out.println(hashtable.insertDynamicaly(keys[1], values[1]));
    //     System.out.println(hashtable.insertDynamicaly(keys[2], values[2]));
    //     System.out.println(hashtable.insertDynamicaly(keys[3], values[3]));


    //     for (int j = 0; j < keys.length; j++) {
    //         hashtable.insert(keys[j],values[j]);
    //     }
    //     Scanner sc = new Scanner(System.in);
    //     boolean flag = true;
    //     while (flag){
    //         System.out.println("choose input");
    //         int input = sc.nextInt();
    //         sc.nextLine();
    //         switch (input) {
    //             case 1:
    //             System.out.println("delete ?");
    //             String st = sc.nextLine();
    //             int key =  st.hashCode(); 
    //             System.out.println(hashtable.delete(key, st));
    //                 break;
    //         case 2:
    //             flag = false;
    //             default:
    //             flag = false;
    //                 break;
    //         }
    //     }
    //     while(true){
    //         System.out.println("search for??");
    //         String st = sc.nextLine();
    //         int key =  st.hashCode();
    //         System.out.println(hashtable.search(key, st));
    //     }
    //     // System.out.println("kimo");
    // }
}
