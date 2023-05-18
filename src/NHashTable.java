
public class NHashTable<T> {
    int primary_size;
    NSquareHashTable<NSquareHashTable<T>> Primary_table;

    NHashTable(int p) {
        this.primary_size = p;
        Primary_table = new NSquareHashTable<NSquareHashTable<T>>(primary_size, 32);
    }

    boolean Big_insert(int key, T value) {
        int P_inx = Primary_table.get_ind(key);
        if (Primary_table.table[P_inx] == null) {//no collision
            Primary_table.table[P_inx] = new Entry<>(1, null);
            Primary_table.table[P_inx].value = new NSquareHashTable<>();
            Primary_table.table[P_inx].key = key;
        }
        Primary_table.table[P_inx].value.insertDynamicaly(key, value);
        return true;
    }

    boolean Big_del(int key, T value) {
        int P_inx = Primary_table.get_ind(key);
        if (Primary_table.table[P_inx] == null) {
            return false;
        }
        return Primary_table.table[P_inx].value.delete(key, value);
    }

    boolean Big_search(int key, T value) {
        int P_inx = Primary_table.get_ind(key);
        if (Primary_table.table[P_inx] == null) {
            return false;
        }
        return Primary_table.table[P_inx].value.search(key, value);
    }
}
