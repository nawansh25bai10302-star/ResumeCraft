package com.resumecraft.services;

import com.resumecraft.model.Education;
import com.resumecraft.model.Experience;
import com.resumecraft.model.PersonalInfo;
import com.resumecraft.model.Project;
import com.resumecraft.model.Resume;
import com.resumecraft.model.Skill;

public class ResumeService {

    private Resume resume;

    public ResumeService() {
        resume = new Resume();
    }

    public Resume getResume() {
        return resume;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        resume.setPersonalInfo(personalInfo);
    }

    public void addEducation(Education education) {
        resume.addEducation(education);
    }

    public void addExperience(Experience experience) {
        resume.addExperience(experience);
    }

    public void addProject(Project project) {
        resume.addProject(project);
    }

    public void addSkill(Skill skill) {
        resume.addSkill(skill);
    }

    public void removeEducation(int index) {
        resume.removeEducation(index);
    }

    public void removeExperience(int index) {
        resume.removeExperience(index);
    }

    public void removeProject(int index) {
        resume.removeProject(index);
    }

    public void removeSkill(int index) {
        resume.removeSkill(index);
    }
}