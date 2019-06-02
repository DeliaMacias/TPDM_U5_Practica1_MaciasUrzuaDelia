package mx.edu.ittepic.themickyebmo.tpdm_u5_practica1maciasurzuadelia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE FRASES(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,CODIGO VARCHAR (10),INFORMACION VARCHAR (200))");
        String insertar = "INSERT INTO FRASES VALUES(NULL,'DELIA','>>El dia de hoy te ira muy bien<<'),"+
                "(NULL,'SERGIO','>>No tengas miedo a fracasar, ten miedo a no intentarlo.\nNo compitas con nadie, no tienes que demostrarle nada a nadie no tienes que llegar a donde otro llegó, solo superar tus propios límites.<<'),"+
                "(NULL,'JUAN','>> El dolor es inevitable, el sufrimiento es cuestión de elección'),"+
                "(NULL,'HOLA','>>QUE YA FUNCIONE ESTO.<<'),"+
                "(NULL,'SAGITARIO','>> Se avecina una etapa muy entretenida en tu vida <<'),"+
                "(NULL,'LEO','>>Empiezas una fase de tu vida amorosa<<'),"+
                "(NULL,'ARIES','>>Tu capacidad de comunicación está en alza.<<'),"+
                "(NULL,'VIRGO','>>En vísperas del novilunio recibirás muy buenas noticias.<<'),"+
                "(NULL,'ACUARIO','>>Una etapa nueva dentro de tu realidad afectiva.<<'),"+
                "(NULL,'TAURO','>>La Luna en tránsito al elemento aire, tu elocuencia es alta<<'),"+
                "(NULL,'ESCORPION','>>Encuentros sorpresivos con personas muy interesantes.<<'),"+
                "(NULL,'LIBRA','>>Un encuento no planeado que resultará muy agradable.<<'),"+
                "(NULL,'BENIGNO','>>El mundo necesita gente que ame lo que hace.<<'),"+
                "(NULL,'DIME ALGO','“No inventes, no engañes, no robes ni bebas; pero si inventas, invéntate un mundo mejor; si engañas, engáñale a la muerte; si robas, róbate un corazón y si bebes, bébete los mejores momentos de tu vida.” Hitch'),"+
                "(NULL,'.','“En mi opinión, lo normal es solo lo ordinario, lo mediocre. La vida pertenece a aquellos individuos raros y excepcionales que se atreven a ser diferentes.”')";


        sqLiteDatabase.execSQL(insertar);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
