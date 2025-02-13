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
        String pref;
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
                    } catch(Error err){
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
                    } catch(Error err){
                        err("Произошла ошибка при попытке считать имя файла, проверьте правильность введенных данных");
                    }
                    break;

            }
        }

        /* Чтение информации из файлов */
        for(int i = 0; i < filePaths.size(); i++){

            

        }

    }
}