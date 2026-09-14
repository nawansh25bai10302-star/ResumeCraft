package com.resumecraft.model;

public class Project {

    private String projectName;
    private String technologies;
    private String description;
    private String projectLink;

    public Project() {
    }

    public Project(String projectName, String technologies,
                   String description, String projectLink) {

        this.projectName = projectName;
        this.technologies = technologies;
        this.description = description;
        this.projectLink = projectLink;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }
}