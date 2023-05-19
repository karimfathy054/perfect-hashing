import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {
    HashTable<String> hashTable;

    boolean initialize(int hashTableType, int hashTableSize){
        switch (hashTableType) {
            case 1:
                this.hashTable = new NSquareHashTable<>(hashTableSize, 32);
                break;
            case 2:
                this.hashTable = new perfectHashTable<>(hashTableSize, 32);
                break;
            default:
                System.out.println("invalid input");
                return false;
        }
        return true;
    }

    boolean insert(String singleString){
        int key = singleString.hashCode();
        return this.hashTable.insert(key, singleString);
    }

    boolean delete(String singleString){
        int key = singleString.hashCode();
        return this.hashTable.insert(key, singleString);
    }

    boolean search(String singleString){
        int key = singleString.hashCode();
        return this.hashTable.insert(key, singleString);
    }

    int batchInsert(String path) throws FileNotFoundException{
        File file = new File(path);
        Scanner sc = new Scanner(file);
        int notInserted,total;
        notInserted=total=0;
        while(sc.hasNextLine()){
            total++;
            String entry = sc.nextLine();
            if(!hashTable.insert(entry.hashCode(), entry)){
                notInserted++;
            }
        }
        sc.close();
        System.out.println(notInserted+" not inserted out of "+total+" total");//could commment it out in case of printing in CLI
        return notInserted;
    }
    int batchDelete(String path) throws FileNotFoundException{
        File file = new File(path);
        Scanner sc = new Scanner(file);
        int notDeleted,total;
        notDeleted=total=0;
        while(sc.hasNextLine()){
            total++;
            String entry = sc.nextLine();
            if(!hashTable.delete(entry.hashCode(), entry)){
                notDeleted++;
            }
        }
        sc.close();
        System.out.println(notDeleted+" not deleted out of "+total+" total");//could commment it out in case of printing in CLI
        return notDeleted;
    }
}
