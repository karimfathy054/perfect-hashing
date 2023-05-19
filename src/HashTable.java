public interface HashTable<T> {
    public boolean insert(int key,T value);
    public boolean search(int key,T value);
    public boolean delete(int key,T value);
}
