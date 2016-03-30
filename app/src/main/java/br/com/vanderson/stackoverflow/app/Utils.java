package br.com.vanderson.stackoverflow.app;

import java.util.Random;

/**
 * Created by vauruk on 29/03/16.
 */
public class Utils {

    private static int num = 1000000;

    public static int radomNumber(){
        Random ran = new Random();
        return ran.nextInt(num)+1;
    }
}
