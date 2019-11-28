package com.example.planningpoker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planningpoker.model.Group;

public class MainActivity extends AppCompatActivity implements MainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, GroupListFragment.newInstance(), "")
                .commit();
    }

    @Override
    public void showGroup(Group group) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, GroupFragment.newInstance(group.getId()), "")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
    }
}
