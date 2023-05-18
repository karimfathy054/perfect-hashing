
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

    int rehashings;

    UniversalHasher hasher;

    public NSquareHashTable(int dectionarySize, int keyBitSize) {
        this.dectionarySize = dectionarySize;
        this.keyBitSize = keyBitSize;
        int nSquare = dectionarySize*dectionarySize;
        this.hashMatrixSize = (int)Math.ceil(Math.log(nSquare)/Math.log(2));
        this.tableSize = (int)Math.pow(2,hashMatrixSize);
        this.table = new Entry[tableSize];
        this.rehashings = 0;
        this.hasher = new UniversalHasher(hashMatrixSize,keyBitSize);
    }
    boolean insert(int key , T value){
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
                rehashings +=1;
            }
        }
        return true;
    }
    boolean rehashAndInsert(Entry e){
        Entry[] temp = new Entry[this.tableSize];
        this.hasher = new UniversalHasher(this.hashMatrixSize,this.keyBitSize);
        for (int i = 0; i < this.tableSize; i++) {
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
            return true;
        }
        return false;
    }

    // public static void main(String[] args) {
    //     String[] values = {"vsdhvbjh","vfdhnihnsdkjo","aseowjimnci","ckhbiashion"};
    //     int[] keys = new int[values.length];
    //     int i =  0;
    //     for (String string : values) {
    //         keys[i++]= string.hashCode();
    //     }
    //     NSquareSolution<String> hashtable = new NSquareSolution<>(2, 32);
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
