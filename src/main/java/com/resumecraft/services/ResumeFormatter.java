package com.resumecraft.services;

import com.resumecraft.model.Education;
import com.resumecraft.model.Project;
import com.resumecraft.model.Resume;
import com.resumecraft.model.Skill;

public class ResumeFormatter {

    /*
     * ============================================================
     * DEFAULT FORMATTER
     * ============================================================
     *
     * This method is kept so that all existing code which uses:
     *
     * ResumeFormatter.format(resume)
     *
     * continues to work.
     *
     * The default template is MODERN.
     */
    public static String format(Resume resume) {
        return format(resume, ResumeTemplate.MODERN);
    }


    /*
     * ============================================================
     * TEMPLATE FORMATTER
     * ============================================================
     *
     * This method allows the user to choose:
     *
     * MODERN
     * CLASSIC
     * MINIMAL
     */
    public static String format(Resume resume, ResumeTemplate template) {

        if (resume == null) {
            return "No resume data available.";
        }

        if (template == null) {
            template = ResumeTemplate.MODERN;
        }

        switch (template) {

            case CLASSIC:
                return formatClassic(resume);

            case MINIMAL:
                return formatMinimal(resume);

            case MODERN:
            default:
                return formatModern(resume);
        }
    }


    /*
     * ============================================================
     * MODERN TEMPLATE
     * ============================================================
     */
    private static String formatModern(Resume resume) {

        StringBuilder output = new StringBuilder();

        output.append("╔══════════════════════════════════════════════════════════╗\n");
        output.append("║                     RESUMECRAFT                         ║\n");
        output.append("║                  MODERN RESUME                          ║\n");
        output.append("╚══════════════════════════════════════════════════════════╝\n\n");


        // --------------------------------------------------------
        // PERSONAL INFORMATION
        // --------------------------------------------------------
        if (resume.getPersonalInfo() != null) {

            output.append(resume.getPersonalInfo().getFullName()).append("\n");
            output.append(resume.getPersonalInfo().getEmail())
                    .append("  |  ")
                    .append(resume.getPersonalInfo().getPhone())
                    .append("\n");

            output.append(resume.getPersonalInfo().getAddress()).append("\n");

            output.append("LinkedIn: ")
                    .append(resume.getPersonalInfo().getLinkedin())
                    .append("\n");

            output.append("GitHub: ")
                    .append(resume.getPersonalInfo().getGithub())
                    .append("\n\n");


            output.append("SUMMARY\n");
            output.append("──────────────────────────────────────────────────────────\n");
            output.append(resume.getPersonalInfo().getSummary())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // EDUCATION
        // --------------------------------------------------------
        output.append("EDUCATION\n");
        output.append("──────────────────────────────────────────────────────────\n");

        for (Education education : resume.getEducationList()) {

            output.append("• ")
                    .append(education.getDegree())
                    .append("\n");

            output.append("  ")
                    .append(education.getInstitution())
                    .append(", ")
                    .append(education.getLocation())
                    .append("\n");

            output.append("  ")
                    .append(education.getStartDate())
                    .append(" - ")
                    .append(education.getEndDate())
                    .append("\n");

            output.append("  Grade: ")
                    .append(education.getGrade())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // PROJECTS
        // --------------------------------------------------------
        output.append("PROJECTS\n");
        output.append("──────────────────────────────────────────────────────────\n");

        for (Project project : resume.getProjectList()) {

            output.append("• ")
                    .append(project.getProjectName())
                    .append("\n");

            output.append("  Technologies: ")
                    .append(project.getTechnologies())
                    .append("\n");

            output.append("  Description: ")
                    .append(project.getDescription())
                    .append("\n");

            output.append("  GitHub: ")
                    .append(project.getProjectLink())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // SKILLS
        // --------------------------------------------------------
        output.append("SKILLS\n");
        output.append("──────────────────────────────────────────────────────────\n");

        for (Skill skill : resume.getSkillList()) {

            output.append("• ")
                    .append(skill.getName())
                    .append("  |  ")
                    .append(skill.getCategory())
                    .append("  |  ")
                    .append(skill.getProficiency())
                    .append("\n");
        }


        output.append("\n");
        output.append("══════════════════════════════════════════════════════════\n");
        output.append("                    END OF RESUME\n");
        output.append("══════════════════════════════════════════════════════════\n");

        return output.toString();
    }


    /*
     * ============================================================
     * CLASSIC TEMPLATE
     * ============================================================
     */
    private static String formatClassic(Resume resume) {

        StringBuilder output = new StringBuilder();

        output.append("============================================================\n");
        output.append("                         RESUME\n");
        output.append("============================================================\n\n");


        // --------------------------------------------------------
        // PERSONAL INFORMATION
        // --------------------------------------------------------
        if (resume.getPersonalInfo() != null) {

            output.append(resume.getPersonalInfo().getFullName())
                    .append("\n");

            output.append(resume.getPersonalInfo().getEmail())
                    .append("\n");

            output.append(resume.getPersonalInfo().getPhone())
                    .append("\n");

            output.append(resume.getPersonalInfo().getAddress())
                    .append("\n");

            output.append("LinkedIn: ")
                    .append(resume.getPersonalInfo().getLinkedin())
                    .append("\n");

            output.append("GitHub: ")
                    .append(resume.getPersonalInfo().getGithub())
                    .append("\n\n");


            output.append("PROFESSIONAL SUMMARY\n");
            output.append("------------------------------------------------------------\n");
            output.append(resume.getPersonalInfo().getSummary())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // EDUCATION
        // --------------------------------------------------------
        output.append("EDUCATION\n");
        output.append("------------------------------------------------------------\n");

        for (Education education : resume.getEducationList()) {

            output.append("Degree/Course: ")
                    .append(education.getDegree())
                    .append("\n");

            output.append("Institution: ")
                    .append(education.getInstitution())
                    .append("\n");

            output.append("Location: ")
                    .append(education.getLocation())
                    .append("\n");

            output.append("Duration: ")
                    .append(education.getStartDate())
                    .append(" - ")
                    .append(education.getEndDate())
                    .append("\n");

            output.append("Grade: ")
                    .append(education.getGrade())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // PROJECTS
        // --------------------------------------------------------
        output.append("PROJECTS\n");
        output.append("------------------------------------------------------------\n");

        for (Project project : resume.getProjectList()) {

            output.append("Project Name: ")
                    .append(project.getProjectName())
                    .append("\n");

            output.append("Technologies: ")
                    .append(project.getTechnologies())
                    .append("\n");

            output.append("Description: ")
                    .append(project.getDescription())
                    .append("\n");

            output.append("GitHub: ")
                    .append(project.getProjectLink())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // SKILLS
        // --------------------------------------------------------
        output.append("SKILLS\n");
        output.append("------------------------------------------------------------\n");

        for (Skill skill : resume.getSkillList()) {

            output.append("Skill: ")
                    .append(skill.getName())
                    .append("\n");

            output.append("Category: ")
                    .append(skill.getCategory())
                    .append("\n");

            output.append("Proficiency: ")
                    .append(skill.getProficiency())
                    .append("\n\n");
        }


        output.append("============================================================\n");
        output.append("                      END OF RESUME\n");
        output.append("============================================================\n");

        return output.toString();
    }


    /*
     * ============================================================
     * MINIMAL TEMPLATE
     * ============================================================
     */
    private static String formatMinimal(Resume resume) {

        StringBuilder output = new StringBuilder();


        // --------------------------------------------------------
        // NAME / CONTACT
        // --------------------------------------------------------
        if (resume.getPersonalInfo() != null) {

            output.append(resume.getPersonalInfo().getFullName())
                    .append("\n");

            output.append(resume.getPersonalInfo().getEmail())
                    .append(" | ")
                    .append(resume.getPersonalInfo().getPhone())
                    .append("\n");

            output.append(resume.getPersonalInfo().getAddress())
                    .append("\n");

            output.append(resume.getPersonalInfo().getLinkedin())
                    .append(" | ")
                    .append(resume.getPersonalInfo().getGithub())
                    .append("\n\n");


            // ----------------------------------------------------
            // SUMMARY
            // ----------------------------------------------------
            output.append("SUMMARY\n");
            output.append(resume.getPersonalInfo().getSummary())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // EDUCATION
        // --------------------------------------------------------
        output.append("EDUCATION\n");

        for (Education education : resume.getEducationList()) {

            output.append(education.getDegree())
                    .append(" — ")
                    .append(education.getInstitution())
                    .append("\n");

            output.append(education.getStartDate())
                    .append(" - ")
                    .append(education.getEndDate())
                    .append(" | ")
                    .append(education.getGrade())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // PROJECTS
        // --------------------------------------------------------
        output.append("PROJECTS\n");

        for (Project project : resume.getProjectList()) {

            output.append(project.getProjectName())
                    .append("\n");

            output.append(project.getTechnologies())
                    .append("\n");

            output.append(project.getDescription())
                    .append("\n");

            output.append(project.getProjectLink())
                    .append("\n\n");
        }


        // --------------------------------------------------------
        // SKILLS
        // --------------------------------------------------------
        output.append("SKILLS\n");

        for (Skill skill : resume.getSkillList()) {

            output.append(skill.getName())
                    .append(" — ")
                    .append(skill.getProficiency())
                    .append("\n");
        }


        output.append("\n");
        output.append("END OF RESUME\n");

        return output.toString();
    }
}