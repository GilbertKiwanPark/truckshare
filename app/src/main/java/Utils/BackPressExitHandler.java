package Utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by gilbert-mac on 2017. 6. 2..
 */

public class BackPressExitHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressExitHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        DialogInterface.OnClickListener exitListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SystemExit();
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new android.support.v7.app.AlertDialog.Builder(activity)
                .setTitle("앱을 종료하시겠습니까")
                .setNeutralButton("취소", cancelListener)
                .setPositiveButton("종료하기", exitListener)
                .show();

    }

    public void SystemExit() {
        activity.moveTaskToBack(true);
        activity.finish();
        try{toast.cancel();}catch(Exception e){}
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}