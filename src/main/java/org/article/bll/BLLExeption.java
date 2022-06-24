package org.article.bll;

public class BLLExeption extends Exception{
    public BLLExeption() {
        super();
    }

    public BLLExeption(String message, Throwable cause) {
        super("Erreur de la BLL "+message, cause);
    }

    public BLLExeption(String message) {
        super("Erreur de la BLL "+message);
    }
}
