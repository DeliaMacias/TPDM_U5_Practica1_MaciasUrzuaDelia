package mx.edu.ittepic.themickyebmo.tpdm_u5_practica1maciasurzuadelia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class ReceptorSMS extends BroadcastReceiver {
    String telefono,texto;
    Frase base;
    Frase search;

    @Override
    public void onReceive(Context context, Intent intent) {
        base = new Frase(context);
        Bundle b = intent.getExtras();
        Object[] pdus = (Object[]) b.get("pdus");
        SmsMessage mensajes = SmsMessage.createFromPdu((byte[]) pdus[0]);
        telefono = mensajes.getOriginatingAddress();
        texto = mensajes.getMessageBody();
        Log.e("ReceptorSMS", "Remitente: " + telefono);
        Log.e("ReceptorSMS", "Mensaje: " + texto);
        search = base.buscarCodigo(texto);
        String res = search == null ? "No se reconoce el codigo" : search.informacion;
        Log.e("RESPUESTA", res);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(telefono,null,res,null,null);

    }

    private void buscaCodigo(String texto) {

        if(search!=null){
            enviarMensaje(telefono,""+search.informacion);
        }else{
            enviarMensaje(telefono,"No se reconoce el codigo");
        }


    }
    private void enviarMensaje(String tel,String men){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
