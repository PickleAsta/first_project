import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);
            FileWriter outputFile = new FileWriter("output.txt");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(" ");

                if (parts.length == 0) continue;

                String command = parts[0].toLowerCase();
                int limit = 0;

                if (command.equals("exit")) {
                    outputFile.write("Program sonlandırıldı.\n");
                    break;
                }

                if (parts.length < 2 || !isNumeric(parts[1])) {
                    outputFile.write("Geçersiz giriş: " + line + "\n");
                    continue;
                }

                limit = Integer.parseInt(parts[1]);

                switch (command) {
                    case "emirp":
                        outputFile.write("Emirp Sayıları: ");
                        List<Integer> emirpList = new ArrayList<>();
                        for (int i = 2; i <= limit; i++) {
                            if (isEmirp(i)) {
                                emirpList.add(i);
                            }
                        }
                        outputFile.write(formatList(emirpList) + "\n");
                        break;

                    case "armstrong":
                        outputFile.write("Armstrong Sayıları: ");
                        List<Integer> armstrongList = new ArrayList<>();
                        for (int i = 1; i <= limit; i++) {
                            if (isArmstrong(i)) {
                                armstrongList.add(i);
                            }
                        }
                        outputFile.write(formatList(armstrongList) + "\n");
                        break;

                    default:
                        outputFile.write("Geçersiz komut: " + command + "\n");
                }
            }

            scanner.close();
            outputFile.close();
            System.out.println("İşlem tamamlandı. Sonuçlar output.txt dosyasına yazıldı.");

        } catch (IOException e) {
            System.out.println("Dosya okuma/yazma hatası: " + e.getMessage());
        }
    }

    // Sayının asal olup olmadığını kontrol eden fonksiyon
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Sayının tersini döndüren fonksiyon
    public static int reverseNumber(int num) {
        int reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }

    // Emirp sayısı olup olmadığını kontrol eden fonksiyon
    public static boolean isEmirp(int num) {
        int reversedNum = reverseNumber(num);
        return num != reversedNum && isPrime(num) && isPrime(reversedNum);
    }

    // Armstrong sayısı olup olmadığını kontrol eden fonksiyon
    public static boolean isArmstrong(int num) {
        int sum = 0, temp = num, digits = String.valueOf(num).length();
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        return sum == num;
    }

    // Sayının geçerli olup olmadığını kontrol eden fonksiyon
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Listeyi "-" ile ayırarak yazdıran fonksiyon
    public static String formatList(List<Integer> list) {
        return String.join(" - ", list.stream().map(String::valueOf).toArray(String[]::new));
    }
}
