package com.bitbucket.util.helper;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Ram Mishra
 */
public class ProjectSelector {

    private final String projectName;

    private final CheckBox choose;

    public ProjectSelector(String projectName, CheckBox choose) {
        this.projectName = projectName;
        this.choose = choose;
    }

    public String getProjectName() {
        return projectName;
    }

    public CheckBox getChoose() {
        return choose;
    }

}
