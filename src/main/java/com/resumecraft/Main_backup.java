package com.resumecraft;

import java.util.Scanner;

import com.resumecraft.model.Education;
import com.resumecraft.model.PersonalInfo;
import com.resumecraft.model.Project;
import com.resumecraft.model.Skill;
import com.resumecraft.services.ResumeService;
import com.resumecraft.services.ResumeFormatter;
import com.resumecraft.services.ResumeFileWriter;

public class Main_backup {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------");
        System.out.println("             RESUMECRAFT");
        System.out.println("          JAVA RESUME BUILDER");
        System.out.println("----------------------------------------");
        System.out.println();

        ResumeService service = new ResumeService();

        // =========================================================
        // PERSONAL INFORMATION
        // =========================================================

        System.out.println("----- PERSONAL INFORMATION -----");

        String fullName = readNonEmpty(
                scanner,
                "Enter your full name: "
        );

        String email = readEmail(
                scanner,
                "Enter your email: "
        );

        String phone = readPhone(
                scanner,
                "Enter your phone number: "
        );

        String location = readNonEmpty(
                scanner,
                "Enter your location: "
        );

        String linkedin = readNonEmpty(
                scanner,
                "Enter your LinkedIn URL: "
        );

        String github = readNonEmpty(
                scanner,
                "Enter your GitHub URL: "
        );

        String summary = readNonEmpty(
                scanner,
                "Enter your professional summary: "
        );

        PersonalInfo personalInfo = new PersonalInfo(
                fullName,
                email,
                phone,
                location,
                linkedin,
                github,
                summary
        );

        service.setPersonalInfo(personalInfo);

        System.out.println();
        System.out.println("Personal information saved successfully!");
        System.out.println();

        // =========================================================
        // EDUCATION
        // =========================================================

        System.out.println("----- EDUCATION -----");

        String degree = readNonEmpty(
                scanner,
                "Enter your degree/course: "
        );

        String university = readNonEmpty(
                scanner,
                "Enter your college/university: "
        );

        String educationLocation = readNonEmpty(
                scanner,
                "Enter your college location: "
        );

        String startYear = readYear(
                scanner,
                "Enter your start year: "
        );

        String endYear = readEndYear(
                scanner,
                startYear,
                "Enter your end year: "
        );

        String grade = readNonEmpty(
                scanner,
                "Enter your CGPA/percentage: "
        );

        Education education = new Education(
                degree,
                university,
                educationLocation,
                startYear,
                endYear,
                grade
        );

        service.addEducation(education);

        System.out.println();
        System.out.println("Education details saved successfully!");
        System.out.println();

        // =========================================================
        // PROJECT
        // =========================================================

        System.out.println("----- PROJECT -----");

        String projectName = readNonEmpty(
                scanner,
                "Enter project name: "
        );

        String technologies = readNonEmpty(
                scanner,
                "Enter technologies used: "
        );

        String projectDescription = readNonEmpty(
                scanner,
                "Enter project description: "
        );

        String projectGithub = readNonEmpty(
                scanner,
                "Enter project GitHub URL: "
        );

        Project project = new Project(
                projectName,
                technologies,
                projectDescription,
                projectGithub
        );

        service.addProject(project);

        System.out.println();
        System.out.println("Project details saved successfully!");
        System.out.println();

        // =========================================================
        // SKILLS
        // =========================================================

        System.out.println("=-=-= SKILLS =-=-=");

        String skillName1 = readNonEmpty(
                scanner,
                "Enter first skill: "
        );

        String skillCategory1 = readNonEmpty(
                scanner,
                "Enter skill category: "
        );

        String skillLevel1 = readNonEmpty(
                scanner,
                "Enter skill level: "
        );

        service.addSkill(new Skill(
                skillName1,
                skillCategory1,
                skillLevel1
        ));

        System.out.println();

        String skillName2 = readNonEmpty(
                scanner,
                "Enter second skill: "
        );

        String skillCategory2 = readNonEmpty(
                scanner,
                "Enter skill category: "
        );

        String skillLevel2 = readNonEmpty(
                scanner,
                "Enter skill level: "
        );

        service.addSkill(new Skill(
                skillName2,
                skillCategory2,
                skillLevel2
        ));

        System.out.println();
        System.out.println("Skills saved successfully!");
        System.out.println();

        // =========================================================
        // RESUME CREATED
        // =========================================================

        System.out.println("----------------------------------------");
        System.out.println("        RESUME CREATED SUCCESSFULLY");
        System.out.println("----------------------------------------");
        System.out.println();

        System.out.println("Name: "
                + service.getResume()
                        .getPersonalInfo()
                        .getFullName());

        System.out.println("Email: "
                + service.getResume()
                        .getPersonalInfo()
                        .getEmail());

        System.out.println("Education entries: "
                + service.getResume()
                        .getEducationList()
                        .size());

        System.out.println("Projects: "
                + service.getResume()
                        .getProjectList()
                        .size());

        System.out.println("Skills: "
                + service.getResume()
                        .getSkillList()
                        .size());

        System.out.println();
        System.out.println("Resume service is working successfully!");

        // =========================================================
        // MAIN MENU
        // =========================================================

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("             RESUMECRAFT MENU");
            System.out.println("========================================");
            System.out.println("1. View Resume");
            System.out.println("2. Edit Personal Information");
            System.out.println("3. Edit Education");
            System.out.println("4. Edit Project");
            System.out.println("5. Edit Skills");
            System.out.println("6. Save Resume & Exit");
            System.out.println("========================================");

            String choice = readChoice(
                    scanner,
                    "Enter your choice: ",
                    1,
                    6
            );

            switch (choice) {

                case "1":
                    viewResume(service);
                    break;

                case "2":
                    editPersonalInfo(scanner, service);
                    break;

                case "3":
                    editEducation(scanner, service);
                    break;

                case "4":
                    editProject(scanner, service);
                    break;

                case "5":
                    editSkills(scanner, service);
                    break;

                case "6":
                    saveResume(service);
                    running = false;
                    break;

                default:
                    // This should never be reached because
                    // readChoice() already validates the input.
                    System.out.println(
                            "Invalid choice. Please enter a number from 1 to 6."
                    );
            }
        }

        scanner.close();
    }

    // =============================================================
    // VIEW RESUME
    // =============================================================

    public static void viewResume(ResumeService service) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("                    YOUR RESUME");
        System.out.println("==================================================");
        System.out.println();

        String formattedResume =
                ResumeFormatter.format(service.getResume());

        System.out.println(formattedResume);
    }

    // =============================================================
    // EDIT PERSONAL INFORMATION
    // =============================================================

    public static void editPersonalInfo(
            Scanner scanner,
            ResumeService service) {

        PersonalInfo personalInfo =
                service.getResume().getPersonalInfo();

        System.out.println();
        System.out.println("----- EDIT PERSONAL INFORMATION -----");
        System.out.println();

        String fullName = readNonEmpty(
                scanner,
                "Enter your full name: "
        );

        String email = readEmail(
                scanner,
                "Enter your email: "
        );

        String phone = readPhone(
                scanner,
                "Enter your phone number: "
        );

        String address = readNonEmpty(
                scanner,
                "Enter your location: "
        );

        String linkedin = readNonEmpty(
                scanner,
                "Enter your LinkedIn URL: "
        );

        String github = readNonEmpty(
                scanner,
                "Enter your GitHub URL: "
        );

        String summary = readNonEmpty(
                scanner,
                "Enter your professional summary: "
        );

        personalInfo.setFullName(fullName);
        personalInfo.setEmail(email);
        personalInfo.setPhone(phone);
        personalInfo.setAddress(address);
        personalInfo.setLinkedin(linkedin);
        personalInfo.setGithub(github);
        personalInfo.setSummary(summary);

        System.out.println();
        System.out.println(
                "Personal information updated successfully!"
        );
    }

    // =============================================================
    // EDIT EDUCATION
    // =============================================================

    public static void editEducation(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume().getEducationList().isEmpty()) {
            System.out.println();
            System.out.println("No education entry found.");
            return;
        }

        Education education =
                service.getResume().getEducationList().get(0);

        System.out.println();
        System.out.println("----- EDIT EDUCATION -----");
        System.out.println();

        String degree = readNonEmpty(
                scanner,
                "Enter your degree/course: "
        );

        String institution = readNonEmpty(
                scanner,
                "Enter your college/university: "
        );

        String location = readNonEmpty(
                scanner,
                "Enter your college location: "
        );

        String startYear = readYear(
                scanner,
                "Enter your start year: "
        );

        String endYear = readEndYear(
                scanner,
                startYear,
                "Enter your end year: "
        );

        String grade = readNonEmpty(
                scanner,
                "Enter your CGPA/percentage: "
        );

        education.setDegree(degree);
        education.setInstitution(institution);
        education.setLocation(location);
        education.setStartDate(startYear);
        education.setEndDate(endYear);
        education.setGrade(grade);

        System.out.println();
        System.out.println(
                "Education details updated successfully!"
        );
    }

    // =============================================================
    // EDIT PROJECT
    // =============================================================

    public static void editProject(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume().getProjectList().isEmpty()) {
            System.out.println();
            System.out.println("No project found.");
            return;
        }

        Project project =
                service.getResume().getProjectList().get(0);

        System.out.println();
        System.out.println("----- EDIT PROJECT -----");
        System.out.println();

        String projectName = readNonEmpty(
                scanner,
                "Enter project name: "
        );

        String technologies = readNonEmpty(
                scanner,
                "Enter technologies used: "
        );

        String description = readNonEmpty(
                scanner,
                "Enter project description: "
        );

        String projectLink = readNonEmpty(
                scanner,
                "Enter project GitHub URL: "
        );

        project.setProjectName(projectName);
        project.setTechnologies(technologies);
        project.setDescription(description);
        project.setProjectLink(projectLink);

        System.out.println();
        System.out.println(
                "Project details updated successfully!"
        );
    }

    // =============================================================
    // EDIT SKILLS
    // =============================================================

    public static void editSkills(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume().getSkillList().size() < 2) {
            System.out.println();
            System.out.println("At least two skills are required.");
            return;
        }

        Skill skill1 =
                service.getResume().getSkillList().get(0);

        Skill skill2 =
                service.getResume().getSkillList().get(1);

        // ---------------------------------------------------------
        // FIRST SKILL
        // ---------------------------------------------------------

        System.out.println();
        System.out.println("----- EDIT FIRST SKILL -----");
        System.out.println();

        String skillName1 = readNonEmpty(
                scanner,
                "Enter skill name: "
        );

        String skillCategory1 = readNonEmpty(
                scanner,
                "Enter skill category: "
        );

        String skillLevel1 = readNonEmpty(
                scanner,
                "Enter skill level: "
        );

        skill1.setName(skillName1);
        skill1.setCategory(skillCategory1);
        skill1.setProficiency(skillLevel1);

        // ---------------------------------------------------------
        // SECOND SKILL
        // ---------------------------------------------------------

        System.out.println();
        System.out.println("----- EDIT SECOND SKILL -----");
        System.out.println();

        String skillName2 = readNonEmpty(
                scanner,
                "Enter skill name: "
        );

        String skillCategory2 = readNonEmpty(
                scanner,
                "Enter skill category: "
        );

        String skillLevel2 = readNonEmpty(
                scanner,
                "Enter skill level: "
        );

        skill2.setName(skillName2);
        skill2.setCategory(skillCategory2);
        skill2.setProficiency(skillLevel2);

        System.out.println();
        System.out.println("Skills updated successfully!");
    }

    // =============================================================
    // SAVE RESUME
    // =============================================================

    public static void saveResume(ResumeService service) {

        System.out.println();
        System.out.println("----- SAVING RESUME -----");

        String formattedResume =
                ResumeFormatter.format(service.getResume());

        ResumeFileWriter.writeResume(formattedResume);

        System.out.println();
        System.out.println("Thank you for using ResumeCraft!");
        System.out.println("Goodbye!");
    }

    // =============================================================
    // INPUT VALIDATION METHODS
    // =============================================================

    /**
     * Reads a value that cannot be empty.
     */
    private static String readNonEmpty(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }

    /**
     * Validates email format.
     */
    private static String readEmail(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String email = scanner.nextLine().trim();

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                return email;
            }

            System.out.println(
                    "Invalid email. Please enter a valid email address."
            );
        }
    }

    /**
     * Validates an Indian phone number.
     *
     * Accepted examples:
     * 9876543210
     * +919876543210
     * +91 9876543210
     * +91-9876543210
     */
    private static String readPhone(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String phone = scanner.nextLine().trim();

            if (phone.matches(
                    "(\\+91[- ]?)?[6-9][0-9]{9}")) {

                return phone;
            }

            System.out.println(
                    "Invalid phone number. Please enter a valid "
                    + "10-digit Indian phone number."
            );
        }
    }

    /**
     * Validates a four-digit year.
     */
    private static String readYear(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String year = scanner.nextLine().trim();

            if (year.matches("[0-9]{4}")) {

                int yearValue = Integer.parseInt(year);

                if (yearValue >= 1900 && yearValue <= 2100) {
                    return year;
                }
            }

            System.out.println(
                    "Invalid year. Please enter a valid "
                    + "four-digit year between 1900 and 2100."
            );
        }
    }

    /**
     * Validates the end year and makes sure it is not
     * earlier than the start year.
     */
    private static String readEndYear(
            Scanner scanner,
            String startYear,
            String message) {

        int start = Integer.parseInt(startYear);

        while (true) {

            System.out.print(message);

            String endYear = scanner.nextLine().trim();

            if (endYear.matches("[0-9]{4}")) {

                int end = Integer.parseInt(endYear);

                if (end >= start && end <= 2100) {
                    return endYear;
                }
            }

            System.out.println(
                    "Invalid end year. It must be a four-digit year "
                    + "and cannot be earlier than the start year."
            );
        }
    }

    /**
     * Validates menu choices.
     */
    private static String readChoice(
            Scanner scanner,
            String message,
            int minimum,
            int maximum) {

        while (true) {

            System.out.print(message);

            String choice = scanner.nextLine().trim();

            try {

                int value = Integer.parseInt(choice);

                if (value >= minimum && value <= maximum) {
                    return choice;
                }

            } catch (NumberFormatException e) {
                // Invalid input will be handled below.
            }

            System.out.println(
                    "Invalid choice. Please enter a number from "
                    + minimum + " to " + maximum + "."
            );
        }
    }
}