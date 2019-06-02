package mx.edu.ittepic.themickyebmo.tpdm_u5_practica1maciasurzuadelia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Frase {
    int id;
    String codigo;
    String informacion;
    BaseDatos base;

    public Frase(Context activity){
        base= new BaseDatos(activity,"frases",null,1);
    }

    public Frase(int id, String codigo, String informacion) {
        this.id = id;
        this.codigo = codigo;
        this.informacion = informacion;
    }

    public Frase buscarCodigo(String codigo){
        Frase recuperado = null;
        try {
            SQLiteDatabase frases = base.getReadableDatabase();
            String[] columnas = {"ID", "CODIGO", "INFORMACION"};
            String[] argumentos = {codigo};
            Cursor resultado = frases.query("FRASES", columnas, "CODIGO = ?", argumentos, null, null , null, null);
            if (resultado.moveToFirst()) {
                recuperado = new Frase(resultado.getInt(0), resultado.getString(1), resultado.getString(2));
            }
            frases.close();
        }catch(SQLiteException e){
            return null;
        }
        return recuperado;
    }
}
