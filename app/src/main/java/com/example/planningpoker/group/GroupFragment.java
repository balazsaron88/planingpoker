package com.example.planningpoker.group;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpoker.R;
import com.example.planningpoker.group.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment {
    private static final String TAG = GroupFragment.class.getName();

    public static GroupFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("key_id", id);
        GroupFragment fragment = new GroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String groupId;
    private TextView groupNameTextView;
    private EditText questionEditText;
    private ImageView addImageView;
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<Question> questionList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        groupNameTextView = view.findViewById(R.id.et_group_name);
        questionEditText = view.findViewById(R.id.et_question);
        addImageView = view.findViewById(R.id.img_add_question);
        recyclerView = view.findViewById(R.id.recycler_view_questions);

        if (getArguments() == null) {
            // New group
        } else {
            // Edit group
            groupId = getArguments().getString("key_id");
            groupNameTextView.setText(groupId);
        }


        questionList = new ArrayList<>();
        questionAdapter = new QuestionAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(questionAdapter);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("groups/" + groupId + "/questions");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String questionId = child.getKey();
                    String question = (String) child.getValue();
                    questionList.add(new Question(questionId, question));
                }

                questionAdapter.setList(questionList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled");
            }
        });

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion(questionEditText.getText().toString());
                questionEditText.setText("");
            }
        });
    }

    private void addQuestion(String question) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("groups/" + groupId + "/questions");
        String key = ref.push().getKey();
        ref.child(key).setValue(question);
    }
}
