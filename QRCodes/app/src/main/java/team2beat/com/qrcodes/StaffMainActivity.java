package team2beat.com.qrcodes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class StaffMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);
    }

    public void loadQRCode(View v)
    {
        Intent i = new Intent(getBaseContext(), QrDisplayActivity.class);
        StaffMainActivity.this.startActivity(i);
    }

    public void loadRegister(View v)
    {
        Intent i = new Intent(getBaseContext(), RegisterView.class);
        StaffMainActivity.this.startActivity(i);
    }

}
