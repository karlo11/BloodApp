package com.example.karlo.aplikacija1;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggleDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerToggle();
        drawerHamburgerIconChange();
        setupDrawerContent();
    }

    // add drawer and drawer toggle button
    private void drawerToggle() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        mToggleDrawer = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggleDrawer);
        mToggleDrawer.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // select which fragment is shown
    public void selectItemDrawer(MenuItem menuItem) {
        Fragment myFragment = null;
        Class fragmentClass;
        // TODO if drawer menu profile is selected from login screen
        switch (menuItem.getItemId()) {
            case R.id.about:
                fragmentClass = AboutFragment.class;
                break;
            case R.id.supplyInfo:
                fragmentClass = InfoFragment.class;
                break;
            case R.id.map:
                fragmentClass = MapFragment.class;
                break;
            case R.id.users:
                fragmentClass = UsersFragment.class;
                break;
//                case R.id.logout:
//                    fragmentClass = InfoFragment.class;
//                    break;
            // case logout
            default:
                fragmentClass = AboutFragment.class;
        }

        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        // replace fragment, main is initial
        fragmentManager.beginTransaction().replace(R.id.frameLayoutMain, myFragment).commit();
        menuItem.setChecked(true);
        // set navigation bar title
        setTitle(menuItem.getTitle());

        mDrawerLayout.closeDrawers();
    }


    // function for switching fragments
    private void setupDrawerContent() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    // custom icon for drawer menu
    private void drawerHamburgerIconChange() {
        Drawable drawerIconResized = resize(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_heart_drop, null));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(drawerIconResized);
        }
    }

    // resizing image, returns drawable
    private Drawable resize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        // width, height
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap, 120, 110, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    @Override
    // returns toggle
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggleDrawer.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
