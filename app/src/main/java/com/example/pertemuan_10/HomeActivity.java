package com.example.pertemuan_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pertemuan_10.Controller.PagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {
    EditText foodName, foodDesc, foodPrice;
    Button addBtn;
    FirstFragment firstFragment;


    TextView usernameView;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    PagerAdapter pagerAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        foodDesc = findViewById(R.id.et_foodDesc);
        foodName = findViewById(R.id.et_foodName);
        foodPrice = findViewById(R.id.et_foodPrice);
        addBtn = findViewById(R.id.btn_add);
        usernameView = findViewById(R.id.tv_username);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);

        Intent intent = getIntent();
        usernameView.setText(intent.getExtras().getString("username_user"));

        setViewPager2(viewPager2);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(pagerAdapter.getFragmentTitle(position))).attach();

        firstFragment = (FirstFragment) pagerAdapter.createFragment(0);
        addBtn.setOnClickListener(v->{
            if(firstFragment != null){
                firstFragment.addNewFood(foodName.getText().toString(), foodDesc.getText().toString(), foodPrice.getText().toString());
                Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Data Failed to be Added", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menu = item.getItemId();

        switch (menu){
            case R.id.menu_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Logout...");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent loginIntent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(HomeActivity.this, "Logout Batal", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

            case R.id.menu_settings:
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void setViewPager2(ViewPager2 viewPager2) {
        if(pagerAdapter == null){
            pagerAdapter = new PagerAdapter(this);
            pagerAdapter.addFragment(new FirstFragment(),"First");
            pagerAdapter.addFragment(new SecondFragment(),"Second");
            pagerAdapter.addFragment(new ThirdFragment(),"Third");
            viewPager2.setAdapter(pagerAdapter);
        }
    }
}