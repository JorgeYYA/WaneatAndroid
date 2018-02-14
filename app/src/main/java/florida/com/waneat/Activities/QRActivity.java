package florida.com.waneat.Activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.koushikdutta.ion.Ion;

import florida.com.waneat.R;
import florida.com.waneat.Services.RestaurantService;
import florida.com.waneat.Utils.PointsOverlayView;

public class QRActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {
    private TextView labelClick;
    private QRCodeReaderView qrCodeReaderView;
    private PointsOverlayView pointsOverlayView;

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
                if (ContextCompat.checkSelfPermission(QRActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(QRActivity.this, new String[]{Manifest.permission.CAMERA}, 50);
                else
                    qrCodeReaderView.startCamera();
            }
        });
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        labelClick.setText(text);
        pointsOverlayView.setPoints(points);
        RestaurantService service = new RestaurantService(this);
        service.getRestaurant(Integer.parseInt(text));

        Intent returnIntent = new Intent();
        returnIntent.putExtra("read_qr",text);
        setResult(MainActivity.RESULT_OK,returnIntent);
        finish();
    }

    @Override protected void onPause() {
        super.onPause();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }
}
