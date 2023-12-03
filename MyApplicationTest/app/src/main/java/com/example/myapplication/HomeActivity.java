package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "OnCreate - Home", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_home);

        Bundle b = getIntent().getExtras();//get parameters from Main Activity
        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText(b.getString("mail") + " " + b.getString("password"));

        /* Init Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle("subtitle");
        /* add menu to toolbar */
        toolbar.inflateMenu(R.menu.menu_main);
        /* Item menu action */
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = (String) item.getTitle();
                Toast.makeText(HomeActivity.this, title + " Selected !", Toast.LENGTH_SHORT).show();

                if(item.getItemId() == R.id.save){ //click in button save

                } else if (item.getItemId() == R.id.mail){ // click in button mail

                }else{ //other case

                }
                return false;
            }
        });

        /* back button */
        final Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.this.finish();
            }
        });

        //load imgs int dict
        ImageView img_Viewer = (ImageView) findViewById(R.id.imgView);
        HashMap<Integer,Integer> imgs = new HashMap();
        imgs.put(0, R.drawable.gray);
        imgs.put(1, R.drawable.red);
        imgs.put(2, R.drawable.purple);

        //init first img
        int id_current = 0;
        img_Viewer.setImageResource(imgs.get(id_current));

        Button btn_previous = (Button) findViewById(R.id.previous);
        btn_previous.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Drawable drawable_active = img_Viewer.getDrawable();

                int id_current=0;

                for(Map.Entry<Integer, Integer> img : imgs.entrySet()){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), img.getValue(),null);
                    if (drawable.getConstantState().equals(drawable_active.getConstantState()) ){
                        id_current = img.getKey();
                        break;
                    }
                }

                id_current--;
                if (id_current == -1 )
                    id_current=imgs.size()-1;

                img_Viewer.setImageResource(imgs.get(id_current));
            }
        });

        /* Button Next */
        Button btn_next = (Button) findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Drawable drawable_active = img_Viewer.getDrawable();

                int id_current=0;

                for(Map.Entry<Integer, Integer> img : imgs.entrySet()){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), img.getValue(),null);
                    if (drawable.getConstantState().equals(drawable_active.getConstantState()) ){
                        id_current = img.getKey();
                        break;
                    }
                }

                id_current++;
                if (id_current == imgs.size())
                    id_current=0;

                img_Viewer.setImageResource(imgs.get(id_current));
            }
        });




    }



    @Override
    public void onStart(){//call before pause
        super.onStart();
        Toast.makeText(this, "Start - Home", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){//call before pause
        super.onResume();
        Toast.makeText(this, "Resume - Home", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPause(){//call before pause
        super.onPause();
        Toast.makeText(this, "Pause - Home", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onStop(){//call before pause
        super.onStop();
        Toast.makeText(this, "Stop - Home", Toast.LENGTH_LONG).show();
    }

    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Destroy - Home", Toast.LENGTH_LONG).show();
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