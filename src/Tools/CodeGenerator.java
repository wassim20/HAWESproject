/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Random;

/**
 *
 * @author Hassen Chouadah
 */
    public class CodeGenerator {
  
    static String code = null;
    public static String genererCode() {
 
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 6;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
        int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
        buffer.append((char) randomLimitedInt);
    }
    String generatedString = buffer.toString();
    code = generatedString;
    return code;

}

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        CodeGenerator.code = code;
    }
    
}

    

