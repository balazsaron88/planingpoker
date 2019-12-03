package com.example.planningpoker.group;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpoker.R;
import com.example.planningpoker.group.model.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Question> questions;

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionAdapter.QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        final Question question = questions.get(position);
        holder.questionTextView.setText(question.getQuestion());
    }


    @Override
    public int getItemCount() {
        return questions != null ? questions.size() : 0;
    }

    public void setList(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        private TextView questionTextView;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.tv_question);
        }
    }
}
