import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.abs;

public class CLinterface {
    Dictionary dictionary;
    Scanner sc =new Scanner(System.in);
    void chooseHashType(){
        System.out.println("choose dictionary Hash type:\n" +
                "[1] O(N^2)\n" +
                "[2] O(N)\n");
        int choice=Integer.valueOf(sc.nextLine());
        System.out.println("Enter the Size of Table");
        int n = Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
                dictionary =new Dictionary();
                dictionary.initialize(1 , n);
                break;
            case 2:
                dictionary =new Dictionary();
                dictionary.initialize(2 , n);
                break;
            default:
                System.out.println("choose a valid number");
                chooseHashType();
        }
        return;
    }
    void mainMenu() throws FileNotFoundException {

        System.out.println("choose a command number:\n" +
                "[1] insert one word\n" +
                "[2] search one word\n" +
                "[3] delete one word\n" +
                "[4] batch insert words\n" +
                "[5] batch delete words\n" +
                "[6] exit");
        int choice =Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
                System.out.println("type the word you want to insert:");
                String word= sc.nextLine();
                boolean valid = dictionary.insert(word);
                if(valid) System.out.println("the word is inserted.");
                else System.out.println("the word is already exist.");
                break;
            case 2:
                System.out.println("type the word you want to search:");
                String word1= sc.nextLine();
                boolean v = dictionary.search(word1);
                if(v) System.out.println("the word is found.");
                else System.out.println("the word is not found .");
                break;
            case 3:
                System.out.println("type the word you want to delete:");
                String word2= sc.nextLine();
                boolean v2 = dictionary.delete(word2);
                if(v2) System.out.println("the word is founded and Deleted it.");
                else System.out.println("the word is not deleted .");
                break;
            case 4:
                System.out.println("type the path of the file containing all the words to insert :");
                String path= sc.nextLine();
                int size = dictionary.batchInsert(path);
                System.out.printf("%d new words were added\n",size);
                break;
            case 5:
                System.out.println("type the path of the file containing all the words to delete:");
                String path2= sc.nextLine();
                int s = dictionary.batchDelete(path2);
                System.out.printf(" %d were deleted\n",s);
                break;

            case 6:
                System.exit(0);
            default:
                System.out.println("choose a valid number");
        }
        return;
    }

    public static void main(String[] args) throws FileNotFoundException {
        CLinterface userMenu = new CLinterface();
        userMenu.chooseHashType();
        while (true){
            userMenu.mainMenu();
        }
    }
}
