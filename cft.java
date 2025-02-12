import java.nio.file.Path;
import java.nio.file.Paths;

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

        /* Чтение опций */
        for(int i = 0; i < args.length; i++){

            switch(args[i]){

                case "-o":
                    isO = true;
                    i++;
                    if(i >= args.length){
                        err("Не передан аргумент для параметра -o");
                    }
                    //TODO обернуть в exception если путь некорректный
                    Path path = Paths.get(args[i]);

                    break;
                case "-p":
                    isP = true;

                    /* чтение префикса */

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

            }



        }

    }
}