package florida.com.waneat.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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
    private QRCodeReaderView qrCodeReaderView = null;
    private PointsOverlayView pointsOverlayView;
    private DataWebService api = new DataWebService();
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        labelClick = (TextView) findViewById(R.id.result_text_view);

        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        pointsOverlayView = (PointsOverlayView) findViewById(R.id.points_overlay_view);


        initCamera();

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        labelClick.setText(text);
        pointsOverlayView.setPoints(points);
        count++;
        if(count == 1){
            try{
                restaurantCall(Integer.parseInt(text));
            }catch(Exception e){
                Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override protected void onPause() {
        super.onPause();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }

    @Override protected void onResume() {
        super.onResume();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.startCamera();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    qrCodeReaderView.startCamera();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
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
                Toast.makeText(QRActivity.this, getResources().getString(R.string.infoRes), Toast.LENGTH_SHORT).show();
                Preferences.restaurantToString(QRActivity.this, restaurant);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("read_qr","readen");
                setResult(MainActivity.RESULT_OK,returnIntent);
                finish();
            }

            @Override
            public void isError(Throwable t) {
                Toast.makeText(QRActivity.this, getResources().getString(R.string.conexFail), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initCamera(){
        qrCodeReaderView.setOnQRCodeReadListener(QRActivity.this);

        qrCodeReaderView.setQRDecodingEnabled(true);

        qrCodeReaderView.setBackCamera();

        ActivityCompat.requestPermissions(QRActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
    }

}
