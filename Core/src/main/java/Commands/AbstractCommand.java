package Commands;

import java.io.Serializable;
import java.util.Arrays;

public abstract class AbstractCommand implements Command, Serializable {
    @Override
    public String toString() {
        return getClass().getSimpleName().toLowerCase();
    }

    private String fromCamelToSnake(String camelCaseString){

        StringBuilder stringSnakeCase = new StringBuilder();

        char[] chr = camelCaseString.toCharArray();
        int lastAppend = 0;

        for(int i = 1; i < camelCaseString.length() - 1; i++){
            if(Character.isUpperCase(chr[i])){
                stringSnakeCase.append(Arrays.copyOfRange(chr, lastAppend, i));
                stringSnakeCase.append('_');
                lastAppend = i;

            }
        }

        stringSnakeCase.append(Arrays.copyOfRange(chr, lastAppend, camelCaseString.length()) );

        return stringSnakeCase.toString().toLowerCase();
    }
}
