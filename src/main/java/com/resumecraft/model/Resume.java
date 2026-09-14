package com.resumecraft.model;

import java.util.ArrayList;
import java.util.List;

public class Resume {

    private PersonalInfo personalInfo;

    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Project> projectList;
    private List<Skill> skillList;

    public Resume() {
        educationList = new ArrayList<>();
        experienceList = new ArrayList<>();
        projectList = new ArrayList<>();
        skillList = new ArrayList<>();
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void addEducation(Education education) {
        educationList.add(education);
    }

    public void addExperience(Experience experience) {
        experienceList.add(experience);
    }

    public void addProject(Project project) {
        projectList.add(project);
    }

    public void addSkill(Skill skill) {
        skillList.add(skill);
    }

    public void removeEducation(int index) {
        educationList.remove(index);
    }

    public void removeExperience(int index) {
        experienceList.remove(index);
    }

    public void removeProject(int index) {
        projectList.remove(index);
    }

    public void removeSkill(int index) {
        skillList.remove(index);
    }
}