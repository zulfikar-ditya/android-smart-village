package com.example.user.smartvillage.Activity.dashboard_user;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.smartvillage.Activity.dashboard_user.list.ListFragment;
import com.example.user.smartvillage.Activity.dashboard_user.notification.NotificationFragment;
import com.example.user.smartvillage.Activity.dashboard_user.profile.ProfileFragment;
import com.example.user.smartvillage.Activity.dashboard_user.request.RequestFragment;
import com.example.user.smartvillage.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> contents_fragment;
    private DashboardTabAdapter tab_adapter;
    private ImageView options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.pager);
        options = (ImageView)findViewById(R.id.options);

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, options);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.list_options, popupMenu.getMenu());

                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.setting:
                                Toast.makeText(MainActivity.this, "ini setting gan", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.help:
                                Toast.makeText(MainActivity.this, "ini help gan", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.signout:
                                Toast.makeText(MainActivity.this, "ini sign out gan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });

            }
        });

        contents_fragment = new ArrayList<>();

        contents_fragment.add(new ListFragment());
        contents_fragment.add(new RequestFragment());
        contents_fragment.add(new NotificationFragment());
        contents_fragment.add(new ProfileFragment());

        tab_adapter = new DashboardTabAdapter(getSupportFragmentManager(), contents_fragment);

        viewPager.setAdapter(tab_adapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position, positionOffset, false);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
