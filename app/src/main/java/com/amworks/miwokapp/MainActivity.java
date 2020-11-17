package com.amworks.miwokapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends FragmentActivity {

//    Button numbers,familyNumbers,colors,phrases;


    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.pager);
        pagerAdapter = new MainActivity.ScreenSlidePagerAdapter(MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

        //For attaching Tabs to ViewPager2
        tabLayout=findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0) {
                    tab.setText("Numbers");
                }
                else if (position==1){
                    tab.setText("FamilyMembers");
                }
                else if (position==2){
                    tab.setText("Colors");
                }
                else
                {
                    tab.setText("Phrases");
                }
            }
        });
        tabLayoutMediator.attach();


    }



    @Override
    public void onBackPressed() {

        if(viewPager.getCurrentItem()==0)
        {
            super.onBackPressed();
        }else
        {
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
        }

    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter
    {
        public ScreenSlidePagerAdapter(FragmentActivity fa)
        {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if(position==0)
                return new numbersFragment();
            else
                return new ScreenSlidePageFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}