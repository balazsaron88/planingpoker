package com.example.planningpoker;

import com.example.planningpoker.group.model.Group;
import com.example.planningpoker.group.model.Question;

public interface MainView {

    void showEditGroup(Group group);

    void showGroupListing();

    void showQuestion(Question question);
}
