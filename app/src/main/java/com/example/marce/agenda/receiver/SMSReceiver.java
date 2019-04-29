package com.example.marce.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.marce.agenda.R;
import com.example.marce.agenda.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");

        byte[] pdu = (byte[]) pdus[0];

        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDAO dao = new AlunoDAO(context);

        if (dao.ehAluno(telefone)) {
            Toast.makeText(context, "Chegou um SMS do " + telefone, Toast.LENGTH_SHORT).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
        dao.close();
    }

}
