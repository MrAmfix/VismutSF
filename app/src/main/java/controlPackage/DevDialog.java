package controlPackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


public class DevDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Информация";
        String message = "Разработчик: Шафиков Максим\nЕсли нашли ошибки - напишите в телеграм";
        String button1String = "Связь со мной (Telegram)";
        String button2String = "Исходный код проекта (GitHub)";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://t.me/Eqcgh"));
                startActivity(i);
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/MrAmfix/VismutProject"));
                startActivity(i);
            }
        });
        builder.setCancelable(true);
        return builder.create();
    }
}
