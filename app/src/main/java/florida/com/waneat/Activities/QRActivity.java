package florida.com.waneat.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import florida.com.waneat.Api.DataStrategy;
import florida.com.waneat.Api.DataWebService;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.Preferences.Preferences;
import florida.com.waneat.R;
import florida.com.waneat.Utils.PointsOverlayView;

public class QRActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {
    private TextView labelClick;
    private QRCodeReaderView qrCodeReaderView;
    private PointsOverlayView pointsOverlayView;
    private DataWebService api = new DataWebService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        labelClick = (TextView) findViewById(R.id.result_text_view);


        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        pointsOverlayView = (PointsOverlayView) findViewById(R.id.points_overlay_view);

        qrCodeReaderView.setOnQRCodeReadListener(this);

        qrCodeReaderView.setQRDecodingEnabled(true);

        qrCodeReaderView.setAutofocusInterval(2000L);

        qrCodeReaderView.setTorchEnabled(false);

        qrCodeReaderView.setBackCamera();

        labelClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(QRActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                }

            }
        });
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        labelClick.setText(text);
        pointsOverlayView.setPoints(points);
        try{
            restaurantCall(Integer.parseInt(text));
        }catch(Exception e){
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override protected void onPause() {
        super.onPause();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    qrCodeReaderView.startCamera();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(QRActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void restaurantCall(int id){
        api.getRestaurant(id, new DataStrategy.InteractDispacherGetRestaurants() {
            @Override
            public void getRestaurant(Restaurant restaurant) {
                Toast.makeText(QRActivity.this, "Información del restaurante recuperada correctamente", Toast.LENGTH_SHORT).show();
                Preferences.restaurantToString(QRActivity.this, restaurant);
                Intent returnIntent = new Intent();
                startActivity(returnIntent);
                finish();
            }

            @Override
            public void isError(Throwable t) {
                Toast.makeText(QRActivity.this, "Ha fallado la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
