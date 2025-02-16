import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class cft {


    static void err(String err){
        System.out.println(err);
        System.exit(0);
    }


    public static void main(String[] args) {

        boolean isO = false;
        boolean isP = false;
        boolean isA = false;
        boolean isS = false;
        boolean isF = false;
        /* Путь для сохранения результатов */
        Path path = Paths.get("");
        /* Массив файлов для чтения */
        List<Path> filePaths = new ArrayList<>();
        /* Названия файлов */
        String nameIntFile = "integers.txt";
        String nameFloatFile = "floats.txt";
        String nameStringFile = "strings.txt";
        /* Количество записанных элементов */
        int countInt = 0;
        int countFloat = 0;
        int countString = 0;

        int minInt = 0;
        int maxInt = 0;
        int sumInt = 0;
        float minFloat = 0;
        float maxFloat = 0;
        float sumFloat = 0;
        int minString = 0;
        int maxString = 0;

        /* Чтение опций */
        for(int i = 0; i < args.length; i++){

            switch(args[i]){

                case "-o":

                    isO = true;
                    i++;
                    if(i >= args.length){

                        err("Не передан аргумент для параметра -o");

                    }
                    try{

                        /*  TODO
                         * Не нравится это решение, но я не знаю почему каталог не создается 
                         * если первый символ \, хотя внутри конструктора есть нормализация
                         */
                        if (args[i].startsWith("/")) {
                            args[i] = args[i].substring(1);
                        }
                        File theDir = new File(args[i]);
                        if (!theDir.exists() && !theDir.mkdirs()){

                            err("Не удалось создать каталог, убедитесь в правильности названия, пример /test/case");

                        };
                        path = Paths.get(args[i]);

                    } catch(Exception e){

                        err("Указан некорректный путь для сохранения файлов");

                    }

                    break;

                case "-p":

                    isP = true;
                    
                    i++;
                    if(i >= args.length){

                        err("Не передан аргумент для параметра -p");

                    }
                    nameIntFile = args[i] + nameIntFile;
                    nameFloatFile = args[i] + nameFloatFile;
                    nameStringFile = args[i] + nameStringFile;

                    break;

                case "-a":

                    isA = true;
                    break;

                case "-s":

                    isS = true;
                    break;

                case "-f":

                    isF = true;
                    break;

                default:

                    try{

                        filePaths.add(Paths.get(args[i]));

                    } catch(Exception e){

                        err("Произошла ошибка при попытке считать имя файла, проверьте правильность введенных данных");

                    }
                    break;

            }
        }

        /* Создание 3 файлов */
        Path resInt = null;
        Path resFloat = null;
        Path resString = null;
        try{

            resInt = Paths.get(path + "/" + nameIntFile);
            resFloat = Paths.get(path + "/" + nameFloatFile);
            resString = Paths.get(path + "/" + nameStringFile);

            /* Если выбрана опция -a, но каких-то файлов нет - создаем их */
            if(isA){

                File f = new File (resInt.toString());
                if(!f.exists()){
                    Files.createFile(resInt);
                }
                f = new File (resFloat.toString());
                if(!f.exists()){
                    Files.createFile(resFloat);
                }
                f = new File (resString.toString());
                if(!f.exists()){
                    Files.createFile(resString);
                }

            } else{
                
                File f = new File (resInt.toString());
                if(f.exists()){
                    Files.delete(resInt);
                }
                f = new File (resFloat.toString());
                if(f.exists()){
                    Files.delete(resFloat);
                }
                f = new File (resString.toString());
                if(f.exists()){
                    Files.delete(resString);
                }

                Files.createFile(resInt);
                Files.createFile(resFloat);
                Files.createFile(resString);

            }

        } catch(Exception e){

            err("Не удалось создать файлы результатов. Вероятные причина ошибки: некорректный префикс или файлы уже существуют");

        }

        /* Чтение информации из файлов */ 
        for(Path filePath : filePaths){

            try (
                BufferedReader reader = Files.newBufferedReader(filePath);
                BufferedWriter writerInt = Files.newBufferedWriter(resInt, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                BufferedWriter writerFloat = Files.newBufferedWriter(resFloat, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                BufferedWriter writerString = Files.newBufferedWriter(resString, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            ){

                
                String line;
                Float fl;
                Integer integer;
                String str;
                while ((line = reader.readLine()) != null) {
                    try{
                        integer = Integer.parseInt(line);
                        writerInt.write(integer.toString());
                        writerInt.newLine();
                        countInt++;

                        if(countInt == 1){
                            minInt = integer;
                            maxInt = integer;
                            sumInt = integer;
                        }
                        else{
                            
                            if(integer < minInt) 
                                minInt = integer;

                            if(integer > maxInt)
                                maxInt = integer;

                            sumInt += integer;

                        }


                    } catch(Exception e1){
                        try{
                            fl = Float.parseFloat(line);
                            writerFloat.write(fl.toString());
                            writerFloat.newLine();
                            countFloat++;

                            if(countFloat == 1){
                                minFloat = fl;
                                maxFloat = fl;
                                sumFloat = fl;
                            }
                            else{
                                
                                if(fl < minFloat) 
                                    minFloat = fl;
    
                                if(fl > maxFloat)
                                    maxFloat = fl;
    
                                sumFloat += fl;
    
                            }

                        } catch(Exception e2){
                            str = line;
                            writerString.write(str);
                            writerString.newLine();
                            countString++;

                            if(countInt == 1){
                                minString = str.length();
                                maxString = str.length();
                            }
                            else{
                                
                                if(str.length() < minString) 
                                    minString = str.length();
    
                                if(str.length() > maxString)
                                    maxString = str.length();

                            }
                        }
                    }
                }
            } catch (Exception e) {

                err("Файл не существует, либо введено неверное имя");

            }

        }

        if(isS){

            System.out.println("Целых чисел: " + countInt);
            System.out.println("Вещественных чисел: " + countFloat);
            System.out.println("Строк: " + countString + "\n");

        }

        if(isF){

            System.out.println("Максимальное среди целых чисел: " + maxInt);
            System.out.println("Минимальное среди целых чисел: " + minInt);
            System.out.println("Сумма целых чисел: " + sumInt);
            System.out.println("Среднее целых чисел: " + (float)sumInt/countInt + "\n");

            System.out.println("Максимальное среди вещественных чисел: " + maxFloat);
            System.out.println("Минимальное среди вещественных чисел: " + minFloat);
            System.out.println("Сумма вещественных чисел: " + sumFloat);
            System.out.println("Среднее вещественных чисел: " + (float)sumFloat/countFloat + "\n");

            System.out.println("Длина самой длинной строки: " + maxString);
            System.out.println("Длина самой короткой строки: " + minString + "\n");

        }

    }
}