package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.classes.GlobalVariables;
import com.example.myapplication.classes.User;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    TextView tv_QRResult;
    Button btn_QRCode;
     MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        toolbar =(MaterialToolbar) findViewById(R.id.topAppBar);
        tv_QRResult = (TextView) findViewById(R.id.homeQRResult);
        btn_QRCode = (Button) findViewById(R.id.homeQRCode);

        User user = new User();
        user.setId(GlobalVariables.getUserIdCourant());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Handle navigation icon press */
                Toast.makeText(HomeActivity.this, "navigation menu", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = (String) item.getTitle();
                Toast.makeText(HomeActivity.this, title + " Selected !", Toast.LENGTH_SHORT).show();

                if(item.getItemId() == R.id.settings){ //click in button settings
                    Toast.makeText(HomeActivity.this, "settings", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.help){ // click in button help
                    Toast.makeText(HomeActivity.this, "help", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.profil){ // click in button profil
                    Toast.makeText(HomeActivity.this, "Profil", Toast.LENGTH_SHORT).show();
                }else{ //other case

                }
                return false;
            }
        });


        btn_QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(HomeActivity.this);

                intentIntegrator.setPrompt("Scan a QR Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult != null){
            String contents = intentResult.getContents();
            if(contents != null){
                tv_QRResult.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart(){//call before pause
        super.onStart();
        //Toast.makeText(this, "Start - Home", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){//call before pause
        super.onResume();
        //Toast.makeText(this, "Resume - Home", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPause(){//call before pause
        super.onPause();
        //Toast.makeText(this, "Pause - Home", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onStop(){//call before pause
        super.onStop();
        //Toast.makeText(this, "Stop - Home", Toast.LENGTH_LONG).show();
    }

    public void onDestroy(){
        super.onDestroy();
        //Toast.makeText(this, "Destroy - Home", Toast.LENGTH_LONG).show();
    }

    /*
    *     public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){//donne item selectionner
        int id = item.getItemId();

        if (id == R.id.back){
            Toast.makeText(this,"back is clicked", Toast.LENGTH_LONG);
        }else if ( id == R.id.settings){
            Toast.makeText(this,"settings has clicked", Toast.LENGTH_LONG);
        }else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG);
        }



        return super.onOptionsItemSelected(item);
    }
    * */
}