package com.example.planningpoker.question;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.planningpoker.R;
import com.example.planningpoker.group.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestionFragment extends Fragment {

    private static final String TAG = QuestionFragment.class.getName();

    public static QuestionFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putSerializable("key_question", question);
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView averageAnswerTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Question question = (Question) getArguments().getSerializable("key_question");
        averageAnswerTextView = view.findViewById(R.id.tv_average_answer);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("answers/" + question.getId());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange");
                int count = 0;
                int sum = 0;

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    count++;
                    String valueString = (String) child.getValue();
                    if (valueString != null) {
                        sum += Integer.valueOf(valueString);
                    }
                }

                if (count == 0) {
                    averageAnswerTextView.setText("0");
                } else {
                    String average = String.valueOf(sum / count);
                    averageAnswerTextView.setText(average);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled");
            }
        });
    }
}
