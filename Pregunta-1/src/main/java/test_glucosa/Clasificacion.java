package test_glucosa;

public class Clasificacion {
    public static int clasificar(int nivel) {
        if (nivel < 99) return 0;        
        else if (nivel <= 125) return 1; 
        else return 2;                   
    }
}