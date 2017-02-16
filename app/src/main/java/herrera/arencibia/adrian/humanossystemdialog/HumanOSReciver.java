package herrera.arencibia.adrian.humanossystemdialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by adrian on 2/15/17.
 */
public class HumanOSReciver extends BroadcastReceiver {
    static boolean isShow[] = {false};

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isShow[0])
            if (checkDrawOverlayPermission(context))
                mostrarDialog(context);
    }

    private void mostrarDialog(Context context) {
        final WindowManager manager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        /* posición en la pantalla */
        layoutParams.gravity = Gravity.CENTER;
        /* tipo de view */
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
       /* alto y ancho */
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = 150;
        /* animación */
        layoutParams.windowAnimations = android.R.style.Animation_Dialog;

        /* el layout que vamos a utilizar */
        final View view = View.inflate(context.getApplicationContext(), R.layout.test_layout, null);
        Button yesButton = (Button) view.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                manager.removeView(view);
                isShow[0]=false;
            }
        });

        /* la mostramos así */
        manager.addView(view, layoutParams);
        isShow[0]=true;
    }


    /**
     * code para identificar la respuesta del intent
     */
    public final static int REQUEST_CODE = 30101;

    public boolean checkDrawOverlayPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /** chequeamos que no tengamos los permisos ya */
            if (!Settings.canDrawOverlays(context)) {
                /** lanzamos el intent para activar el permiso */
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + context.getPackageName()));
                /** startActivityForResult() para ver la respuesta del usuario */
                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                return false;
            }
            return true;
        }
        return true;
    }
}
