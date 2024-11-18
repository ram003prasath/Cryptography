import java.util.Scanner;

class HillCipher {

    static void getKeyMatrix(String key, int keyMatrix[][]) {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = (key.charAt(k)) % 65;
                k++;
            }
        }
    }

    static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {
        int x, i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 1; j++) {
                cipherMatrix[i][j] = 0;
                for (x = 0; x < 3; x++) {
                    cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
                }
                cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
            }
        }
    }

    static void inverseKeyMatrix(int keyMatrix[][], int inverseKeyMatrix[][]) {
        int determinant = 0;
        for (int i = 0; i < 3; i++)
            determinant = determinant + (keyMatrix[0][i] * (keyMatrix[1][(i + 1) % 3] * keyMatrix[2][(i + 2) % 3]
                    - keyMatrix[1][(i + 2) % 3] * keyMatrix[2][(i + 1) % 3]));
        determinant = determinant % 26;
        if (determinant < 0)
            determinant += 26;
        int inverseDeterminant = -1;
        for (int i = 1; i < 26; i++) {
            if ((determinant * i) % 26 == 1) {
                inverseDeterminant = i;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverseKeyMatrix[i][j] = (((keyMatrix[(j + 1) % 3][(i + 1) % 3] * keyMatrix[(j + 2) % 3][(i + 2) % 3])
                        - (keyMatrix[(j + 1) % 3][(i + 2) % 3] * keyMatrix[(j + 2) % 3][(i + 1) % 3])) % 26);
                if (inverseKeyMatrix[i][j] < 0)
                    inverseKeyMatrix[i][j] += 26;
                inverseKeyMatrix[i][j] = (inverseKeyMatrix[i][j] * inverseDeterminant) % 26;
            }
        }
    }

    static void decrypt(int decipherMatrix[][], int inverseKeyMatrix[][], int cipherMatrix[][]) {
        int x, i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 1; j++) {
                decipherMatrix[i][j] = 0;
                for (x = 0; x < 3; x++) {
                    decipherMatrix[i][j] += inverseKeyMatrix[i][x] * cipherMatrix[x][j];
                }
                decipherMatrix[i][j] = decipherMatrix[i][j] % 26;
            }
        }
    }

    static void HillCipher(String message, String key) {
        int[][] keyMatrix = new int[3][3];
        getKeyMatrix(key, keyMatrix);
        int[][] messageVector = new int[3][1];
        for (int i = 0; i < 3; i++)
            messageVector[i][0] = (message.charAt(i)) % 65;
        int[][] cipherMatrix = new int[3][1];
        encrypt(cipherMatrix, keyMatrix, messageVector);
        String cipherText = "";
        for (int i = 0; i < 3; i++)
            cipherText += (char) (cipherMatrix[i][0] + 65);
        System.out.println("Ciphertext: " + cipherText);
        int[][] inverseKeyMatrix = new int[3][3];
        inverseKeyMatrix(keyMatrix, inverseKeyMatrix);
        int[][] decipherMatrix = new int[3][1];
        decrypt(decipherMatrix, inverseKeyMatrix, cipherMatrix);
        String plainText = "";
        for (int i = 0; i < 3; i++)
            plainText += (char) (decipherMatrix[i][0] + 65);
        System.out.println("Decrypted Plaintext: " + plainText);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the plaintext (3 characters): ");
        String message = scanner.next().toUpperCase();
        String key = "GYBNQKURP";
        HillCipher(message, key);
        scanner.close();
    }
}
