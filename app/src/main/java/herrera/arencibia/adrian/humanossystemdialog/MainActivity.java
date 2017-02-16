package herrera.arencibia.adrian.humanossystemdialog;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


import static android.R.attr.permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkDrawOverlayPermission();
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialog(MainActivity.this);
            }
        });


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
            }
        });

        /* la mostramos así */
        manager.addView(view, layoutParams);
    }

    /**
     * code para identificar la respuesta del intent
     */
    public final static int REQUEST_CODE = 30101;

    public void checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /** chequeamos que no tengamos los permisos ya */
            if (!Settings.canDrawOverlays(MainActivity.this)) {
                /** lanzamos el intent para activar el permiso */
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                /** startActivityForResult() para ver la respuesta del usuario */
                startActivityForResult(intent, REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /** check if received result code
         is equal our requested code for draw permission  */
        if (requestCode == REQUEST_CODE) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    // continue here - permission was granted
                }
            }
        }
    }
}
