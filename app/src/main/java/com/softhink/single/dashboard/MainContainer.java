package com.softhink.single.dashboard;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.softhink.single.BaseActivity;
import com.softhink.single.login.LoginActivity;
import com.softhink.single.onboarding.OnboardingActivity;
import com.softhink.single.R;
import com.softhink.single.dashboard.adapters.PagerAdapter;

public class MainContainer extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);

        if (savedInstanceState == null) {
            setUpToolbar("", false);

            viewPager = findViewById(R.id.viewPager);
            setUpViewPager();
            tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons();
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.mis_citas);
        tabLayout.getTabAt(1).setIcon(R.drawable.perfil);
        tabLayout.getTabAt(2).setIcon(R.drawable.lugares);
    }

    private void setUpViewPager() {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new DatesFragment(), "MIS CITAS");
        adapter.addFragments(new PerfilFragment(), "PERFIL");
        adapter.addFragments(new PlacesFragment(), "LUGARES");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOnboarding:
                startActivity(new Intent(this, OnboardingActivity.class));
                break;

            case R.id.menuLogout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return true;
    }
}

