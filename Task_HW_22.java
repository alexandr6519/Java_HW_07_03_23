// Дана строка (получение через обычный текстовый файл!!!)
// "фамилия":"Иванов","оценка":"5","предмет":"Математика"
// "фамилия":"Петрова","оценка":"4","предмет":"Информатика"
// Написать метод(ы), который распарсит строку и, используя StringBuilder, создаст строки вида:
// Студент [фамилия] получил [оценка] по предмету [предмет].
// Пример вывода в консоль:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.
package Java_HW_07_03_23;

import java.io.*;

public class Task_HW_22 {
    static String linesArray(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
        String str;
        StringBuilder stb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            stb.append(str).append(";");
        }
        br.close();
        return stb.toString();
    }

    static String[] getArrayLinesFromString(String lines) {
        return lines.split(";");
    }

    static String[][][] getArrayData(String[] linesArray) {
        int size = linesArray.length;
        String[][] dataArray2 = new String[size][3];
        String[][][] dataArray3 = new String[size][3][2];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 3; j++) {
                dataArray2[i][j] = linesArray[i].split(",")[j];
                for (int k = 0; k < 2; k++) {
                    dataArray3[i][j][k] = dataArray2[i][j].split(":")[k];
                }
            }
        }
        return dataArray3;
    }

    static void printResult(String[][][] dataArray, int linesCount) {
        String studentName = "";
        String studentGrade = "";
        String subject = "";
        System.out.print(" ");
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    studentName = dataArray[i][0][1];
                    studentGrade = dataArray[i][1][1];
                    subject = dataArray[i][2][1];
                }
            }
            System.out.printf("Студент %s %s %s по предмету %s. \n ", studentName, getRightWord(studentName),
                    studentGrade, subject);
        }
    }

    static String getRightWord(String str) {
        if (str.charAt(str.length() - 1) == 'а') {
            return "получила";
        }
        return "получил";
    }
    public static void main(String[] args) throws IOException {
        String str = linesArray("file_4.txt");
        str = str.replaceAll("\"", "");
        String[] linesArray = getArrayLinesFromString(str);
        String[][][] dataArray = getArrayData(linesArray);

        printResult(dataArray, linesArray.length);
    }
}
