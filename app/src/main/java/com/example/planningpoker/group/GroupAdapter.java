package com.example.planningpoker.group;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpoker.R;
import com.example.planningpoker.group.model.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private List<Group> groups;
    private ClickListener listener;

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        final Group group = groups.get(position);
        holder.groupNameTextView.setText(group.getId() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onGroupClicked(group);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return groups != null ? groups.size() : 0;
    }

    public void setList(List<Group> groups) {
        this.groups = groups;
        notifyDataSetChanged();
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    interface ClickListener {
        void onGroupClicked(Group group);
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {

        private TextView groupNameTextView;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.tv_group_name);
        }
    }
}
