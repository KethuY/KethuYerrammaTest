package com.satya.kethuyerrammatest.activities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.satya.kethuyerrammatest.R;
import com.satya.kethuyerrammatest.adapters.BottomViewPageAdapter;
import com.satya.kethuyerrammatest.adapters.TopViewPagerAdapter;
import com.satya.kethuyerrammatest.fragments.ImageFragment;
import com.satya.kethuyerrammatest.fragments.MileStoneFragment;
import com.satya.kethuyerrammatest.fragments.VideoFragment;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mTopViewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private TopViewPagerAdapter mTopViewPageAdapter;
    private ArrayList<ImageView> dots;
    private ViewPager mBottomViewPager;
    private TabLayout mTabLayout;

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
        mTopViewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        addBottomDots(0);
    }

    private void setUpBottomViewPager() {
        mBottomViewPager = (ViewPager) findViewById(R.id.bottom_view_pager);
        BottomViewPageAdapter adapter = new BottomViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new VideoFragment(), "Videos");
        adapter.addFragment(new ImageFragment(), "Images");
        adapter.addFragment(new MileStoneFragment(), "MileStoneFragment");
        mBottomViewPager.setAdapter(adapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mBottomViewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_header, null);
        tabOne.setText("Videos");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.video, 0, 0);
        mTabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_header, null);
        tabTwo.setText("Images");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img, 0, 0);
        mTabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_header, null);
        tabThree.setText("Milestone");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mile, 0, 0);
        mTabLayout.getTabAt(2).setCustomView(tabThree);


    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

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
            par.leftMargin=5;
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


}
