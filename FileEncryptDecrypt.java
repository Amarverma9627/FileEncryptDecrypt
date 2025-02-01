// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptDecrypt {
   private static final int SHIFT = 4;

   public FileEncryptDecrypt() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      System.out.print("Enter 'E' to encrypt or 'D' to decrypt: ");
      char var2 = var1.next().toUpperCase().charAt(0);
      var1.nextLine();
      System.out.print("Enter the file path: ");
      String var3 = var1.nextLine();
      String var4 = var3 + (var2 == 'E' ? "_encrypted.txt" : "_decrypted.txt");

      try {
         processFile(var3, var4, var2 == 'E');
         System.out.println("Operation successful! Output saved to: " + var4);
      } catch (IOException var6) {
         System.out.println("Error processing the file: " + var6.getMessage());
      }

      var1.close();
   }

   private static void processFile(String var0, String var1, boolean var2) throws IOException {
      BufferedReader var3 = new BufferedReader(new FileReader(var0));

      try {
         BufferedWriter var4 = new BufferedWriter(new FileWriter(var1));

         try {
            int var5 = var2 ? 4 : -4;

            String var6;
            while((var6 = var3.readLine()) != null) {
               var4.write(shiftText(var6, var5));
               var4.newLine();
            }
         } catch (Throwable var9) {
            try {
               var4.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }

            throw var9;
         }

         var4.close();
      } catch (Throwable var10) {
         try {
            var3.close();
         } catch (Throwable var7) {
            var10.addSuppressed(var7);
         }

         throw var10;
      }

      var3.close();
   }

   private static String shiftText(String var0, int var1) {
      StringBuilder var2 = new StringBuilder();
      char[] var3 = var0.toCharArray();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char var6 = var3[var5];
         if (Character.isLetter(var6)) {
            int var7 = Character.isUpperCase(var6) ? 65 : 97;
            var6 = (char)((var6 - var7 + var1 + 26) % 26 + var7);
         } else if (Character.isDigit(var6)) {
            var6 = (char)((var6 - 48 + var1 + 10) % 10 + 48);
         }

         var2.append(var6);
      }

      return var2.toString();
   }
}
