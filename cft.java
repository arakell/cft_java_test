import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        try{

            Path resInt = Paths.get( pref + "integers.txt");
            Path resFloat = Paths.get(pref + "floats.txt");
            Path resString = Paths.get(pref + "strings.txt");
            Files.createFile(resInt);
            Files.createFile(resFloat);
            Files.createFile(resString);

        } catch(Exception e){

            err("Не удалось создать файлы результатов. Вероятная причина ошибки: некорректный префикс");

        }


/* 
        BufferedReader reader;
         Чтение информации из файлов 
        for(Path filePath : filePaths){

            try {

                reader = Files.newBufferedReader(filePath);

                String line;
                while ((line = reader.readLine()) != null) {

                    System.out.println(line);

                }

            } catch (IOException e) {

                err("Файл не существует либо введено неверное имя");

            }

        }
*/

    }
}