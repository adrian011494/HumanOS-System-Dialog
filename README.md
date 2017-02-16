# HumanOS-System-Dialog
<strong>Hello humanOS!!! </strong>Hoy les traigo un tuto que les ayudara a mostrar views por encima de cualquier aplicación en android.

&nbsp;
<h3>Permisos</h3>
Para esto solo necesitamos un permiso en especifico (android.permission.SYSTEM_ALERT_WINDOW):
<ul>
 	<li>Para <strong>API &lt; 23</strong> o sea, <strong>hasta Android 5</strong> agregaremos al <strong>AndroidManifest.xml</strong> lo siguiente:
<pre class="brush:xml">&lt;uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" /&gt;</pre>
</li>
 	<li>Para <strong>API &gt;= 23</strong> o sea, <strong>a partir de Android 6</strong> usaremos el siguiente método para que el usuario active el permiso necesario.
<pre class="brush:java">/** code para identificar la respuesta del intent */
public final static int REQUEST_CODE = 30101;

public void checkDrawOverlayPermission() {
    if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
    /** chequeamos que no tengamos los permisos ya */
        if (!Settings.canDrawOverlays(MainActivity.this)) {
            /** lanzamos el intent para activar el permiso */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            /** startActivityForResult() para ver la respuesta del usuario */
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
}</pre>
</li>
</ul>
<a href="https://humanos.uci.cu/wp-content/uploads/2017/02/device-2017-02-15-234659.png"><img class="aligncenter size-medium wp-image-81228" src="https://humanos.uci.cu/wp-content/uploads/2017/02/device-2017-02-15-234659-169x300.png" alt="" width="169" height="300" /></a>
<h3>Mostrando nuestra View</h3>
Para mostrar un layout pueden utilizar este método:
<pre class="brush:java">    private void mostrarDialog(Context context) {
        final WindowManager manager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        // posición en la pantalla 
        layoutParams.gravity = Gravity.CENTER;
        // tipo de view 
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
       // alto y ancho 
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = 150;
        // animación 
        layoutParams.windowAnimations = android.R.style.Animation_Dialog;

        // el layout que vamos a utilizar 
        final View view = View.inflate(context.getApplicationContext(),R.layout.test_layout, null);
        Button yesButton = (Button) view.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                manager.removeView(view);
            }
        });

        // la mostramos así         
        manager.addView(view, layoutParams);
    }</pre>
<h3>Y ya</h3>
Ahora solo les queda desarrollar su imaginación. <strong>Aquí les dejo el código ejemplo</strong> de una app que muestra una view cuando se <strong>modifica</strong> el estado del <strong>volumen.</strong>

Para la #UCI: <a href="https://codecomunidades.prod.uci.cu/aaherrera/HumanOS-System-Dialog.git" target="_blank">https://codecomunidades.prod.uci.cu/aaherrera/HumanOS-System-Dialog.git</a>

Para fuera de la #UCI:
