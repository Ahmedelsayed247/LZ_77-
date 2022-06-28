
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pvv
 */
public class lz77 {

    public static String Word = "";

    public static int counter = 0;
    public static int[] poi = new int[10000];
    public static int[] len = new int[10000];
    public static char[] letter = new char[10000];

    public static int checkBuffer(String temp, int windowsize) {
        for (int i = 0; i <= windowsize - temp.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < temp.length() && flag; j++) {
                if (Word.charAt(i + j) != temp.charAt(j)) {
                    flag = false;
                }
            }
            if (flag == true) {
                return i;
            }
        }
        return -1;
    }

    public static void compression() {
        Scanner input = new Scanner(System.in);
        Word = input.nextLine();
        int i = 0;
        int pointer = 0, Length = 0;
        while (i < Word.length()) {
            String temp = "";
            int start = i;
            temp = temp + Word.charAt(i);
            while (checkBuffer(temp, i) != -1) {
                i++;
                if (i >= Word.length()) {
                    break;
                }
                temp += Word.charAt(i);
            }

            if (temp.length() == 1) {

                poi[counter] = 0;
                len[counter] = 0;
                letter[counter] = temp.charAt(0);

                counter++;

            } else {
                int pos = checkBuffer(temp.substring(0, temp.length() - 1), i);

                if (i == Word.length() - 1) {
                    pointer = start - pos - 1;
                } else {
                    pointer = (start - pos);
                }

                Length = (temp.length() - 1);

                poi[counter] = pointer;
                len[counter] = Length;
                letter[counter] = temp.charAt(temp.length() - 1);

                counter++;

            }

            i++;

        }
        for (int j = 0; j < counter; j++) {
            System.out.println("<" + poi[j] + "," + len[j] + "," + "\'"+ letter[j] +"\'" +  ">");
        }

    }

    public static void decompression() {
        String out = "";
        for (int i = 0; i < counter; i++) {
            if (poi[i] == 0) {
                out += letter[i];
            } else {
                int newlen = len[i], newpoi = out.length() - poi[i];
                while (newlen > 0) {
                    out += out.charAt(newpoi);
                    newlen--;
                    newpoi++;
                }
                out += letter[i];
            }

        }
        System.out.println("The decompression : " + out);

    }

    public static void main(String[] args) {
        //ABAABABAABBBBBBBBBBBBA
        compression();
        decompression();

    }
}
