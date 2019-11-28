package com.example.planningpoker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GroupFragment extends Fragment {

    public static GroupFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("key_id", id);
        GroupFragment fragment = new GroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String groupId;
    private EditText groupNameEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        groupNameEditText = view.findViewById(R.id.et_group_name);

        if (getArguments() == null) {
            // New group
        }else {
            // Edit group
            groupId = getArguments().getString("key_id");
            groupNameEditText.setText(groupId);
        }
    }
}
