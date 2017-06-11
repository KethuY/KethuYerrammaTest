package com.satya.kethuyerrammatest.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.satya.kethuyerrammatest.R;
import com.satya.kethuyerrammatest.adapters.BtmViewPageAdapter;
import com.satya.kethuyerrammatest.adapters.TopViewPagerAdapter;
import com.satya.kethuyerrammatest.fragments.ImageFragment;
import com.satya.kethuyerrammatest.fragments.MileStoneFragment;
import com.satya.kethuyerrammatest.fragments.VideoFragment;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener {

    private ViewPager mTopViewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private TopViewPagerAdapter mTopViewPageAdapter;
    private ArrayList<ImageView> dots;
    private ViewPager mBottomViewPager;
    private TabLayout mTabLayout;
    private int[] tabIcons = {
            R.drawable.video,
            R.drawable.img,
            R.drawable.mile

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setUpToolBarDrawerAndNavigationView();
        setUpTopViewPager();
        setUpBottomViewPager();

    }

    private void setUpTopViewPager() {
        layouts = new int[]{
                R.layout.slide_one,
                R.layout.slide_two,
                R.layout.slide_three,
                R.layout.slide_four,
                R.layout.slide_five
        };

        mTopViewPager = (ViewPager) findViewById(R.id.top_view_pager);
        mTopViewPageAdapter = new TopViewPagerAdapter(HomePageActivity.this);
        mTopViewPager.setAdapter(mTopViewPageAdapter);
        mTopViewPager.addOnPageChangeListener(topViewPageChangeListener);
        addBottomDots(0);
    }

    private void setUpBottomViewPager() {
        mBottomViewPager = (ViewPager) findViewById(R.id.bottom_view_pager);
        BtmViewPageAdapter adapter = new BtmViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new VideoFragment(), "Videos");
        adapter.addFragment(new ImageFragment(), "Images");
        adapter.addFragment(new MileStoneFragment(), "MileStone");
        mBottomViewPager.setAdapter(adapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mBottomViewPager);
        mTabLayout.setOnTabSelectedListener(this);
        setupTabIcon();

        Drawable icon = mTabLayout.getTabAt(0).getIcon();
        if (icon != null) {
            icon.setColorFilter(ContextCompat.getColor(HomePageActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        }
        mBottomViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupTabIcon() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }


    ViewPager.OnPageChangeListener topViewPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    private void addBottomDots(int pos) {
        dots = new ArrayList<>();
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        dotsLayout.removeAllViews();

        for (int i = 0; i < layouts.length; i++) {
            TextView tv = new TextView(this);
            LinearLayout.LayoutParams par = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            par.width = 10;
            par.height = 10;
            par.leftMargin = 5;
            tv.setLayoutParams(par);

            if (pos == i) {

                tv.setBackgroundResource(R.drawable.selected_oval);
            } else {
                tv.setBackgroundResource(R.drawable.unselected_oval);
            }

            dotsLayout.addView(tv);
        }
    }


    private void setUpToolBarDrawerAndNavigationView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_video) {
            mBottomViewPager.setCurrentItem(0);
        } else if (id == R.id.nav_img) {
            // Handle the Imag action
            mBottomViewPager.setCurrentItem(1);

        } else if (id == R.id.nav_milestone) {
            // Handle the Milestone action
            mBottomViewPager.setCurrentItem(2);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Drawable icon = tab.getIcon();
        if (icon != null) {
            icon.setColorFilter(ContextCompat.getColor(HomePageActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Drawable icon = tab.getIcon();
        if (icon != null) {
            icon.setColorFilter(null);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
