import exception.*;

import java.util.*;

public class DeretInteger {
    private int[] deret;
    private int jumlahSuku;

    public DeretInteger(int jumlahSuku) {
        this.jumlahSuku = jumlahSuku;
        deret = new int[jumlahSuku];
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < jumlahSuku; i++) {
            try {
                System.out.print("Masukkan elemen ke-" + (i + 1) + ": ");
                String inputStr = scanner.nextLine();
                /*
                cara kerja regex -?\\d+:
                -? = untuk mengecek apakah inputan berupa bilangan negatif atau tidak
                \\d+ = untuk mengecek apakah inputan berupa bilangan atau bukan
                 */
                if (inputStr.matches("-?\\d+")) {
                    int input = Integer.parseInt(inputStr);
                    if (input < 0) {
                        throw new NegativeNumberException("Bilangan negatif tidak diperbolehkan.");
                    } else if (input == 0) {
                        System.out.println("Input tidak boleh 0, silahkan input ulang");
                        i--;
                    } else {
                        deret[i] = input;
                    }
                } else {
                    throw new InvalidInput("Input harus berupa bilangan & bukan huruf/simbol/spasi. Ulangi inputan");
                }
            } catch (InvalidInput | NegativeNumberException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }
    }


    public void sort() {
        Arrays.sort(deret);
    }

    public void sortAscending() {
        sort();
        System.out.println("Deret setelah diurutkan secara ascending: ");
        for (int i = 0; i < jumlahSuku; i++) {
            System.out.print(deret[i] + " ");
        }
        System.out.println();
    }

    public void sortDescending() {
        sort();
        System.out.println("Deret setelah diurutkan secara descending: ");
        for (int i = jumlahSuku - 1; i >= 0; i--) {
            System.out.print(deret[i] + " ");
        }
        System.out.println();
    }

    public int findMin() {
        int min = deret[0];
        for (int i = 1; i < jumlahSuku; i++) {
            if (deret[i] < min) {
                min = deret[i];
            }
        }
        return min;
    }

    public int findMax() {
        int max = deret[0];
        for (int i = 1; i < jumlahSuku; i++) {
            if (deret[i] > max) {
                max = deret[i];
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int jumlahSuku;
        System.out.print("Masukkan jumlah suku dalam deret: ");
        jumlahSuku = scanner.nextInt();

        DeretInteger deret = new DeretInteger(jumlahSuku);
        deret.input();

        deret.sortAscending();
        deret.sortDescending();
        System.out.println("Nilai minimum dalam deret: " + deret.findMin());
        System.out.println("Nilai maksimum dalam deret: " + deret.findMax());
    }
}
