import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
  @Test
  // natural insert one word o(n^2)
    public void TestInsertN2 (){
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10);
    boolean valid = dic.insert("word");
      assertEquals(true , valid);
  }
  @Test
  // natural insert one word o(n)
  public void TestInsertN (){
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 10);
    boolean valid = dic.insert("word2");
    assertEquals(true , valid);
  }
  @Test
  // natural Delete one word o(n^2) from empty
  public void TestDeleteN2 (){
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10);
    boolean v = dic.delete("word2");
    assertEquals(false , v);
  }
  @Test
  // natural Delete one word o(n) from empty
  public void TestDeleteN (){
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 10);
    boolean v = dic.delete("word2");
    assertEquals(false , v);
  }
  @Test
  // natural Search one word o(n^2) from empty
  public void TestSearchN2 (){
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10);
    boolean v = dic.search("word2");
    assertEquals(false , v);
  }
  @Test
  // natural Search one word o(n) from empty
  public void TestSearchN (){
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 10);
    boolean v = dic.search("word2");
    assertEquals(false , v);
  }
  // batch Delete o(N^2)
  // file 7 words
  @Test
  public void BatchInsert10() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10.txt");
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N^2)
  // file 100 words
  @Test
  public void BatchInsert100() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 100);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\100.txt");
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N^2)
  // file 1000 words
  @Test
  public void BatchInsert1000() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 1000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\1000.txt");
    System.out.println("Number of rehash = "+dic.noOfRehash());
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N^2)
  // file 10000 words
  @Test
  public void BatchInsert10000() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10000.txt");
    System.out.println("Number of rehash = "+dic.noOfRehash());
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N)
  // file 10 words
  @Test
  public void BatchInsertN10() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 10);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10.txt");
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N)
  // file 100 words
  @Test
  public void BatchInsertN100() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 100);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\100.txt");
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N^2)
  // file 1000 words
  @Test
  public void BatchInsertN1000() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 1000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\1000.txt");
    System.out.println("Number of rehash = "+dic.noOfRehash());
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N)
  // file 10000 words
  @Test
  public void BatchInsertN10000() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 10000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10000.txt");
    System.out.println("Number of rehash = "+dic.noOfRehash());
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N)
  // file 50000 words
  @Test
  public void BatchInsertN50000() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 50000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\50000.txt");
    System.out.println("Number of rehash = "+dic.noOfRehash());
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N)
  // file 500000 words
  @Test
  public void BatchInsertN500000() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 500000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\500000.txt");
    System.out.println("Number of rehash = "+dic.noOfRehash());
    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch insert o(N)
  // file 1000000 words
  @Test
  public void BatchInsertN1000000() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 1000000);
    long start= (long) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\1000000.txt");
    System.out.println("Number of rehash = "+dic.noOfRehash());
    long end=(long)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N^2)
  // file 7 words
  @Test
  public void BatchDelete7() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\7.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N^2)
  // file 15 words
  @Test
  public void BatchDelete15() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 100);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\100.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\15.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N^2)
  // file 356 words
  @Test
  public void BatchDelete356() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 1000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\1000.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\356.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N^2)
  // file 286 words
  @Test
  public void BatchDelete286() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10000.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\286.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N)
  // file 7 words
  @Test
  public void BatchDeleteN7() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 10);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\7.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N)
  // file 15 words
  @Test
  public void BatchDeleteN15() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 100);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\100.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\15.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N)
  // file 286 words
  @Test
  public void BatchDeleteN286() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 1000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\1000.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\286.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // batch Delete o(N)
  // file 356 words
  @Test
  public void BatchDeleteN356() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 10000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10000.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\356.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }

  // batch Delete o(N)
  // file 7247 words
  @Test
  public void BatchDeleteN7427() throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 50000);
    int start= (int) System.nanoTime();
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\50000.txt");
    dic.batchDelete("F:\\year2\\term2\\data\\lab3\\7247.txt");

    int end=(int)System.nanoTime();
    System.out.println((end-start)/1000);
  }
  // search in batch insert 10000 O(n^2)
  @Test
  public void TestSearchN2Batch () throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(1 , 10000);
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\10000.txt");
    boolean v = dic.search("aomabhlmfohot");
    assertEquals(true , v);
  }
  // search in batch insert 50000 O(n)
  @Test
  public void TestSearchNBatch () throws FileNotFoundException {
    Dictionary dic = new Dictionary();
    dic.initialize(2 , 50000);
    dic.batchInsert("F:\\year2\\term2\\data\\lab3\\50000.txt");
    boolean v = dic.search("fikuxwhtjhidcbujen");
    assertEquals(true , v);
  }
}