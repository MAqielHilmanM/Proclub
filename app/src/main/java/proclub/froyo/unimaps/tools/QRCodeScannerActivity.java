package proclub.froyo.unimaps.tools;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import proclub.froyo.unimaps.R;

public class QRCodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private String qrCodeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.v("TAG", result.getText()); // Prints scan results
        Log.v("TAG", result.getBarcodeFormat().toString());
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();*/
        Intent i = new Intent();
        i.putExtra("result", result.getText());
        setResult(RESULT_OK, i);
        onBackPressed();

        //mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
