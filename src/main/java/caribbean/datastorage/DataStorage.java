package caribbean.datastorage;

import caribbean.datastructures.Fila;
import caribbean.datastructures.ListaEncadeada;

public class DataStorage {
    private static DataStorage instance;
    private DataStorage() {
        lista = new ListaEncadeada();
    }
    public static synchronized DataStorage getInstance() {
        if (instance == null)
            instance = new DataStorage();
        return instance;
    }

    public ListaEncadeada lista;
    public Fila rota;

    public Island currentIsland;
}
