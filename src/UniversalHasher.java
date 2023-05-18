import java.util.Random;

public class UniversalHasher {
    final int INT_SIZE =32;
    int m; //Hash matrix size ,in the power of 2
    int u; //key bit length

    boolean[][] hashMatrix;

    public UniversalHasher(int hashMatrixSize, int keyBitLength) {
        this.m = hashMatrixSize;
        this.u = keyBitLength;
        this.hashMatrix = generateRandomSparseMatrix(hashMatrixSize,keyBitLength);
    }

    public boolean[][] generateRandomSparseMatrix(int hashMatrixSize, int keyBitLength) {
        boolean [][] res = new boolean[hashMatrixSize][keyBitLength];
        Random random = new Random();
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = random.nextBoolean();
            }
        }
        return res;
    }

    public boolean[] convertIntKeyToVector(int key){
        boolean[] res = new boolean[INT_SIZE];
        int temp = key;
        for (int i = 0; i < INT_SIZE; i++) {
            res[i] = (temp & 1) == 1;
            temp= temp>>1;
        }
        return res;
    }

    public int convertVectorToIntIndex(boolean[] vector){
        int res=0;
        int base =1;
        for (int i = 0; i < vector.length; i++) {
            if(vector[i])
                res += base;
            base = base <<1;
        }
        return res;
    }

    public boolean[] applyUniversalHash(boolean[][] hashMatrix,boolean[] keyVector){
        boolean[] res = new boolean[hashMatrix.length];
        for (int i = 0; i < hashMatrix.length; i++) {
            boolean temp = false ;
            for (int j = 0; j < keyVector.length; j++) {
                temp = temp ^ (hashMatrix[i][j] & keyVector[j]);
            }
            res[i] = temp;
        }
        return res;
    }
    public int getHashedindex(int key){
        boolean[] keyVector = convertIntKeyToVector(key);
        return convertVectorToIntIndex(applyUniversalHash(this.hashMatrix,keyVector));
    }


}
