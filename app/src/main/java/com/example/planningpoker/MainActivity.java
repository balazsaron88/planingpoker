package com.example.planningpoker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planningpoker.group.GroupFragment;
import com.example.planningpoker.group.GroupListFragment;
import com.example.planningpoker.group.model.Group;
import com.example.planningpoker.group.model.Question;
import com.example.planningpoker.login.LoginFragment;
import com.example.planningpoker.question.QuestionFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance(), "")
                .commit();
    }

    @Override
    public void showEditGroup(Group group) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, GroupFragment.newInstance(group.getId()), "")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showGroupListing() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, GroupListFragment.newInstance(), "")
                .commit();
    }

    @Override
    public void showQuestion(Question question) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, QuestionFragment.newInstance(question), "")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
