import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[0];
        String[] values = {"vsdhvbjh", "vfdhnihnsdkjo", "aseowjimnci", "ckhbiashion",
                "vsdhvdsdsdbjh", "vfdsdsdsddhnihnsdkjo", "adsdsdseowjimnci", "cksdsdsdhbiashion"};
        int[] keys = new int[values.length];
        int i = 0;
        NHashTable<String> hashtable = new NHashTable<>(10000);
        for (String string : values) {
            int zz = string.hashCode();
            hashtable.Big_insert(zz, string);
        }
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("choose input");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1:
                    System.out.println("delete ?");
                    String st = sc.nextLine();
                    int key = st.hashCode();
                    System.out.println(hashtable.Big_del(key, st));
                    break;
                case 2:
                    flag = false;
                default:
                    flag = false;
                    break;
            }
        }
        while (true) {
            System.out.println("search for??");
            String st = sc.nextLine();
            int key = st.hashCode();
            System.out.println(hashtable.Big_search(key, st));
        }
    }
}