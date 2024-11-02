package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.classes.GlobalVariables;
import com.example.myapplication.classes.User;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    User user;
    BottomNavigationView bottom_nav;
    FloatingActionButton scan_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        toolbar =(MaterialToolbar) findViewById(R.id.topAppBar);
        bottom_nav = (BottomNavigationView) findViewById(R.id.bottom_nav);
        scan_nav = (FloatingActionButton) findViewById(R.id.scan_nav) ;

        User user = new User();
        user.setId(GlobalVariables.getUserIdCourant());
        user.read(HomeActivity.this);
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


                if (item.getItemId() == R.id.menu_help){ // click in button help

                }else if (item.getItemId() == R.id.menu_settings){ // click in button profil

                }else if (item.getItemId() == R.id.menu_search){ // click in button profil
                    Toast.makeText(HomeActivity.this, "ajouter element", Toast.LENGTH_SHORT).show();
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

        scan_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("QRCode","");
                Intent myIntent = new Intent(HomeActivity.this, DetailsMedecineActivity.class);
                myIntent.putExtras(bundle);
                startActivity(myIntent);
                /*
                IntentIntegrator integrator = new IntentIntegrator(HomeActivity.this);
                integrator.setOrientationLocked(false); // Déverrouiller l'orientation
                integrator.initiateScan();
                 */
            }
        });
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

    public User getUser(){
        return this.user;
    }

    // Méthode appelée lorsque le scanner a terminé
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // Utilisez result.getContents() pour obtenir la valeur du code QR
                String qrCodeValue = result.getContents();
                // Faites quelque chose avec la valeur du code QR (par exemple, l'afficher dans un Toast)
                Bundle bundle = new Bundle();
                bundle.putString("QRCode",qrCodeValue);
                Intent myIntent = new Intent(HomeActivity.this, DetailsMedecineActivity.class);
                myIntent.putExtras(bundle);
                startActivity(myIntent);

            } else {
                // Aucun résultat
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Toast.makeText(HomeActivity.this,"CreateOptionsMenu",Toast.LENGTH_LONG).show();
        getMenuInflater().inflate(R.menu.top_menu_main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint("Search Data here ...");
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(@NonNull MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(@NonNull MenuItem menuItem) {
                return true;
            }

        };
        menu.findItem(R.id.menu_search).setOnActionExpandListener(onActionExpandListener);

        return true;
    }


}