import java.io.BufferedReader;
import java.io.BufferedWriter;
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

        boolean isO;
        boolean isP;
        boolean isA;
        boolean isS;
        boolean isF;
        /* Путь для сохранения результатов */
        Path path = Paths.get("");
        String pref = "";
        /* Массив файлов для чтения */
        List<Path> filePaths = new ArrayList<>();

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
                    pref = args[i];

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

            resInt = Paths.get(pref + "integers.txt");
            resFloat = Paths.get(pref + "floats.txt");
            resString = Paths.get(pref + "strings.txt");
            Files.createFile(resInt);
            Files.createFile(resFloat);
            Files.createFile(resString);

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
                    System.out.println(line);
                    try{
                        integer = Integer.parseInt(line);
                        writerInt.write(integer.toString());
                        writerInt.newLine();
                    } catch(Exception e1){
                        try{
                            fl = Float.parseFloat(line);
                            writerFloat.write(fl.toString());
                            writerFloat.newLine();
                        } catch(Exception e2){
                            str = line;
                            writerString.write(str);
                            writerString.newLine();
                        }
                    }



                }

            } catch (Exception e) {

                err("Файл не существует, либо введено неверное имя");

            }

        }

    }
}