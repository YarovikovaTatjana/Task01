import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static String readString(){
        try{
            return bufferedReader.readLine();
        }
        catch (IOException e){
            System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            return readString();
        }
    }

    static double readDouble(){
        try{
            return Double.parseDouble(readString());
        }
        catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readDouble();
        }
    }

    static double readDouble(int min){
        try{
            double readDouble =readDouble();
            if (readDouble>(double) min) return readDouble;
            else {
                System.out.println("Введенное значение должно быть больше " + min);
                return readDouble(min);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readDouble(min);
        }
    }

    static int readInt(){
        try{
            return Integer.parseInt(readString());
        }
        catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readInt();
        }
    }

    static int readInt(int min){
        try{
            int readInt = readInt();
            if (readInt>min) return readInt;
            else {
                System.out.println("Введенное значение должно быть больше " + min);
                return readInt(min);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readInt(min);
        }
    }

    static int readInt(int min, int max){
        try{
            int readInt = readInt();
            if (readInt>min && readInt<max) return readInt;
            else {
                System.out.println("Введенное значение должно быть в диапазоне от " + min + " до " + max);
                return readInt(min, max);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return  readInt(min, max);
        }
    }

}
