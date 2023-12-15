package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Fragment;
import android.app.Notification;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    User user;
    BottomNavigationView bottom_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        toolbar =(MaterialToolbar) findViewById(R.id.topAppBar);
        bottom_nav = (BottomNavigationView) findViewById(R.id.bottom_nav);

        User user = new User();
        user.setId(GlobalVariables.getUserIdCourant());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Handle navigation icon press */
                Toast.makeText(HomeActivity.this, "left !", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        *   Menu de haut
        */
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
        // Ajouter le fragment par défaut s'il n'y a pas d'état enregistré (par exemple, après une rotation de l'écran)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new HomeFragment())
                    .commit();
        }

        /*
         *   Menu de bas
         */
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId() == R.id.home) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new HomeFragment())
                            .commit();
                    return true;
                }else if(item.getItemId() == R.id.calender) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new CalenderFragment())
                            .commit();
                    return true;
                }else if(item.getItemId() == R.id.scan) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ScanFragment())
                            .commit();
                    return true;
                }else if(item.getItemId() == R.id.favorite) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new FavoriteFragment())
                            .commit();
                    return true;

                }else if(item.getItemId() == R.id.profil) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new ProfileFragment())
                                .commit();
                        return true;
                }
                return false;
            }
        });



    }

}