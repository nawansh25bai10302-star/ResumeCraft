package com.resumecraft;

import java.util.Scanner;

import com.resumecraft.model.Education;
import com.resumecraft.model.PersonalInfo;
import com.resumecraft.model.Project;
import com.resumecraft.model.Skill;
import com.resumecraft.services.ResumeFormatter;
import com.resumecraft.services.ResumeFileWriter;
import com.resumecraft.services.ResumeService;
import com.resumecraft.services.ResumeTemplate;

public class Main {

    // Currently selected resume template.
    private static ResumeTemplate selectedTemplate = ResumeTemplate.MODERN;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ResumeService service = new ResumeService();

        // =========================================================
        // STEP 4A - RESUME TEMPLATE SELECTION
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          CHOOSE RESUME TEMPLATE");
        System.out.println("========================================");
        System.out.println("1. Modern");
        System.out.println("2. Classic");
        System.out.println("3. Minimal");
        System.out.println("========================================");

        int templateChoice = readNumber(
                scanner,
                "Enter your template choice: ",
                1,
                3
        );

        switch (templateChoice) {

            case 1:
                selectedTemplate = ResumeTemplate.MODERN;
                break;

            case 2:
                selectedTemplate = ResumeTemplate.CLASSIC;
                break;

            case 3:
                selectedTemplate = ResumeTemplate.MINIMAL;
                break;

            default:
                selectedTemplate = ResumeTemplate.MODERN;
        }

        System.out.println();
        System.out.println("Template selected: " + selectedTemplate);
        System.out.println();

        showWelcome();

        createPersonalInfo(scanner, service);
        createEducationEntries(scanner, service);
        createProjectEntries(scanner, service);
        createSkillEntries(scanner, service);

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("        RESUME CREATED SUCCESSFULLY");
        System.out.println("----------------------------------------");

        saveResume(service);

        mainMenu(scanner, service);

        scanner.close();
    }

    // =========================================================
    // WELCOME
    // =========================================================

    private static void showWelcome() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("             RESUMECRAFT");
        System.out.println("          JAVA RESUME BUILDER");
        System.out.println("========================================");
        System.out.println();
    }

    // =========================================================
    // CREATE PERSONAL INFORMATION
    // =========================================================

    private static void createPersonalInfo(
            Scanner scanner,
            ResumeService service) {

        System.out.println("----- PERSONAL INFORMATION -----");
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
        System.out.println(
                "Personal information saved successfully!"
        );
        System.out.println();
    }

    // =========================================================
    // CREATE MULTIPLE EDUCATION ENTRIES
    // =========================================================

    private static void createEducationEntries(
            Scanner scanner,
            ResumeService service) {

        System.out.println("----- EDUCATION -----");
        System.out.println();

        int count = readPositiveNumber(
                scanner,
                "How many education entries do you want to add? "
        );

        for (int i = 1; i <= count; i++) {

            System.out.println();
            System.out.println(
                    "----- EDUCATION " + i + " -----"
            );

            String degree = readNonEmpty(
                    scanner,
                    "Enter your degree/course: "
            );

            String university = readNonEmpty(
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

            Education education = new Education(
                    degree,
                    university,
                    location,
                    startYear,
                    endYear,
                    grade
            );

            service.addEducation(education);

            System.out.println(
                    "Education entry " + i + " saved successfully!"
            );
        }

        System.out.println();
    }

    // =========================================================
    // CREATE MULTIPLE PROJECTS
    // =========================================================

    private static void createProjectEntries(
            Scanner scanner,
            ResumeService service) {

        System.out.println("----- PROJECTS -----");
        System.out.println();

        int count = readPositiveNumber(
                scanner,
                "How many projects do you want to add? "
        );

        for (int i = 1; i <= count; i++) {

            System.out.println();
            System.out.println(
                    "----- PROJECT " + i + " -----"
            );

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

            Project project = new Project(
                    projectName,
                    technologies,
                    description,
                    projectLink
            );

            service.addProject(project);

            System.out.println(
                    "Project " + i + " saved successfully!"
            );
        }

        System.out.println();
    }

    // =========================================================
    // CREATE MULTIPLE SKILLS
    // =========================================================

    private static void createSkillEntries(
            Scanner scanner,
            ResumeService service) {

        System.out.println("----- SKILLS -----");
        System.out.println();

        int count = readPositiveNumber(
                scanner,
                "How many skills do you want to add? "
        );

        for (int i = 1; i <= count; i++) {

            System.out.println();
            System.out.println(
                    "----- SKILL " + i + " -----"
            );

            String name = readNonEmpty(
                    scanner,
                    "Enter skill name: "
            );

            String category = readNonEmpty(
                    scanner,
                    "Enter skill category: "
            );

            String proficiency = readNonEmpty(
                    scanner,
                    "Enter skill level: "
            );

            service.addSkill(
                    new Skill(
                            name,
                            category,
                            proficiency
                    )
            );

            System.out.println(
                    "Skill " + i + " saved successfully!"
            );
        }

        System.out.println();
    }

    // =========================================================
    // MAIN MENU
    // =========================================================

    private static void mainMenu(
            Scanner scanner,
            ResumeService service) {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("             RESUMECRAFT MENU");
            System.out.println("========================================");
            System.out.println("1. View Resume");
            System.out.println("2. Edit Resume");
            System.out.println("3. Delete Entries");
            System.out.println("4. Add More Entries");
            System.out.println("5. Save Resume");
            System.out.println("6. Save Resume & Exit");
            System.out.println("========================================");

            int choice = readNumber(
                    scanner,
                    "Enter your choice: ",
                    1,
                    6
            );

            switch (choice) {

                case 1:
                    viewResume(service);
                    break;

                case 2:
                    editResume(scanner, service);
                    break;

                case 3:
                    deleteEntries(scanner, service);
                    break;

                case 4:
                    addMoreEntries(scanner, service);
                    break;

                case 5:
                    saveResume(service);
                    break;

                case 6:
                    saveResume(service);

                    System.out.println();
                    System.out.println(
                            "Thank you for using ResumeCraft!"
                    );
                    System.out.println(
                            "Goodbye!"
                    );

                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // =========================================================
    // VIEW RESUME
    // =========================================================

    private static void viewResume(
            ResumeService service) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("              YOUR RESUME");
        System.out.println("========================================");
        System.out.println();

        String formattedResume =
                ResumeFormatter.format(
                        service.getResume(),
                        selectedTemplate
                );

        System.out.println(formattedResume);
    }

    // =========================================================
    // EDIT RESUME MENU
    // =========================================================

    private static void editResume(
            Scanner scanner,
            ResumeService service) {

        boolean editing = true;

        while (editing) {

            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("              EDIT RESUME");
            System.out.println("----------------------------------------");
            System.out.println("1. Edit Personal Information");
            System.out.println("2. Edit Education");
            System.out.println("3. Edit Project");
            System.out.println("4. Edit Skills");
            System.out.println("5. Back");
            System.out.println("----------------------------------------");

            int choice = readNumber(
                    scanner,
                    "Enter your choice: ",
                    1,
                    5
            );

            switch (choice) {

                case 1:
                    editPersonalInfo(scanner, service);
                    break;

                case 2:
                    editEducation(scanner, service);
                    break;

                case 3:
                    editProject(scanner, service);
                    break;

                case 4:
                    editSkills(scanner, service);
                    break;

                case 5:
                    editing = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // =========================================================
    // EDIT PERSONAL INFORMATION
    // =========================================================

    private static void editPersonalInfo(
            Scanner scanner,
            ResumeService service) {

        PersonalInfo info =
                service.getResume().getPersonalInfo();

        System.out.println();
        System.out.println(
                "----- EDIT PERSONAL INFORMATION -----"
        );
        System.out.println(
                "Press Enter to keep the current value."
        );
        System.out.println();

        String fullName = readOptionalNonEmpty(
                scanner,
                "Full name [" + info.getFullName() + "]: "
        );

        if (!fullName.isEmpty()) {
            info.setFullName(fullName);
        }

        String email = readOptionalEmail(
                scanner,
                "Email [" + info.getEmail() + "]: "
        );

        if (!email.isEmpty()) {
            info.setEmail(email);
        }

        String phone = readOptionalPhone(
                scanner,
                "Phone [" + info.getPhone() + "]: "
        );

        if (!phone.isEmpty()) {
            info.setPhone(phone);
        }

        String address = readOptionalNonEmpty(
                scanner,
                "Location [" + info.getAddress() + "]: "
        );

        if (!address.isEmpty()) {
            info.setAddress(address);
        }

        String linkedin = readOptionalNonEmpty(
                scanner,
                "LinkedIn [" + info.getLinkedin() + "]: "
        );

        if (!linkedin.isEmpty()) {
            info.setLinkedin(linkedin);
        }

        String github = readOptionalNonEmpty(
                scanner,
                "GitHub [" + info.getGithub() + "]: "
        );

        if (!github.isEmpty()) {
            info.setGithub(github);
        }

        String summary = readOptionalNonEmpty(
                scanner,
                "Summary [" + info.getSummary() + "]: "
        );

        if (!summary.isEmpty()) {
            info.setSummary(summary);
        }

        System.out.println();
        System.out.println(
                "Personal information updated successfully!"
        );

        saveResumeSilently(service);
    }

    // =========================================================
    // EDIT EDUCATION
    // =========================================================

    private static void editEducation(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume().getEducationList().isEmpty()) {

            System.out.println();
            System.out.println(
                    "No education entries available."
            );

            return;
        }

        displayEducation(service);

        int index = readNumber(
                scanner,
                "Select education entry to edit: ",
                1,
                service.getResume()
                        .getEducationList()
                        .size()
        ) - 1;

        Education education =
                service.getResume()
                        .getEducationList()
                        .get(index);

        System.out.println();
        System.out.println(
                "Press Enter to keep the current value."
        );

        String degree = readOptionalNonEmpty(
                scanner,
                "Degree [" + education.getDegree() + "]: "
        );

        if (!degree.isEmpty()) {
            education.setDegree(degree);
        }

        String institution = readOptionalNonEmpty(
                scanner,
                "College [" + education.getInstitution() + "]: "
        );

        if (!institution.isEmpty()) {
            education.setInstitution(institution);
        }

        String location = readOptionalNonEmpty(
                scanner,
                "Location [" + education.getLocation() + "]: "
        );

        if (!location.isEmpty()) {
            education.setLocation(location);
        }

        String startYear = readOptionalYear(
                scanner,
                "Start year [" + education.getStartDate() + "]: "
        );

        if (!startYear.isEmpty()) {
            education.setStartDate(startYear);
        }

        String endYear = readOptionalEndYear(
                scanner,
                startYear.isEmpty()
                        ? education.getStartDate()
                        : startYear,
                "End year [" + education.getEndDate() + "]: "
        );

        if (!endYear.isEmpty()) {
            education.setEndDate(endYear);
        }

        String grade = readOptionalNonEmpty(
                scanner,
                "CGPA/percentage [" + education.getGrade() + "]: "
        );

        if (!grade.isEmpty()) {
            education.setGrade(grade);
        }

        System.out.println();
        System.out.println(
                "Education entry updated successfully!"
        );

        saveResumeSilently(service);
    }

    // =========================================================
    // EDIT PROJECT
    // =========================================================

    private static void editProject(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume().getProjectList().isEmpty()) {

            System.out.println();
            System.out.println(
                    "No project entries available."
            );

            return;
        }

        displayProjects(service);

        int index = readNumber(
                scanner,
                "Select project to edit: ",
                1,
                service.getResume()
                        .getProjectList()
                        .size()
        ) - 1;

        Project project =
                service.getResume()
                        .getProjectList()
                        .get(index);

        System.out.println();
        System.out.println(
                "Press Enter to keep the current value."
        );

        String name = readOptionalNonEmpty(
                scanner,
                "Project name [" + project.getProjectName() + "]: "
        );

        if (!name.isEmpty()) {
            project.setProjectName(name);
        }

        String technologies = readOptionalNonEmpty(
                scanner,
                "Technologies [" + project.getTechnologies() + "]: "
        );

        if (!technologies.isEmpty()) {
            project.setTechnologies(technologies);
        }

        String description = readOptionalNonEmpty(
                scanner,
                "Description [" + project.getDescription() + "]: "
        );

        if (!description.isEmpty()) {
            project.setDescription(description);
        }

        String link = readOptionalNonEmpty(
                scanner,
                "GitHub URL [" + project.getProjectLink() + "]: "
        );

        if (!link.isEmpty()) {
            project.setProjectLink(link);
        }

        System.out.println();
        System.out.println(
                "Project updated successfully!"
        );

        saveResumeSilently(service);
    }

    // =========================================================
    // EDIT SKILLS
    // =========================================================

    private static void editSkills(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume().getSkillList().isEmpty()) {

            System.out.println();
            System.out.println(
                    "No skills available."
            );

            return;
        }

        displaySkills(service);

        int index = readNumber(
                scanner,
                "Select skill to edit: ",
                1,
                service.getResume()
                        .getSkillList()
                        .size()
        ) - 1;

        Skill skill =
                service.getResume()
                        .getSkillList()
                        .get(index);

        System.out.println();
        System.out.println(
                "Press Enter to keep the current value."
        );

        String name = readOptionalNonEmpty(
                scanner,
                "Skill name [" + skill.getName() + "]: "
        );

        if (!name.isEmpty()) {
            skill.setName(name);
        }

        String category = readOptionalNonEmpty(
                scanner,
                "Category [" + skill.getCategory() + "]: "
        );

        if (!category.isEmpty()) {
            skill.setCategory(category);
        }

        String proficiency = readOptionalNonEmpty(
                scanner,
                "Proficiency [" + skill.getProficiency() + "]: "
        );

        if (!proficiency.isEmpty()) {
            skill.setProficiency(proficiency);
        }

        System.out.println();
        System.out.println(
                "Skill updated successfully!"
        );

        saveResumeSilently(service);
    }

    // =========================================================
    // DELETE MENU
    // =========================================================

    private static void deleteEntries(
            Scanner scanner,
            ResumeService service) {

        boolean deleting = true;

        while (deleting) {

            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("             DELETE ENTRIES");
            System.out.println("----------------------------------------");
            System.out.println("1. Delete Education");
            System.out.println("2. Delete Project");
            System.out.println("3. Delete Skill");
            System.out.println("4. Back");
            System.out.println("----------------------------------------");

            int choice = readNumber(
                    scanner,
                    "Enter your choice: ",
                    1,
                    4
            );

            switch (choice) {

                case 1:
                    deleteEducation(scanner, service);
                    break;

                case 2:
                    deleteProject(scanner, service);
                    break;

                case 3:
                    deleteSkill(scanner, service);
                    break;

                case 4:
                    deleting = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // =========================================================
    // DELETE EDUCATION
    // =========================================================

    private static void deleteEducation(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume()
                .getEducationList()
                .isEmpty()) {

            System.out.println();
            System.out.println(
                    "No education entries available."
            );

            return;
        }

        displayEducation(service);

        int index = readNumber(
                scanner,
                "Select education entry to delete: ",
                1,
                service.getResume()
                        .getEducationList()
                        .size()
        ) - 1;

        String confirmation = readNonEmpty(
                scanner,
                "Are you sure? (yes/no): "
        );

        if (confirmation.equalsIgnoreCase("yes")) {

            service.removeEducation(index);

            System.out.println(
                    "Education entry deleted successfully!"
            );

            saveResumeSilently(service);

        } else {

            System.out.println(
                    "Deletion cancelled."
            );
        }
    }

    // =========================================================
    // DELETE PROJECT
    // =========================================================

    private static void deleteProject(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume()
                .getProjectList()
                .isEmpty()) {

            System.out.println();
            System.out.println(
                    "No project entries available."
            );

            return;
        }

        displayProjects(service);

        int index = readNumber(
                scanner,
                "Select project to delete: ",
                1,
                service.getResume()
                        .getProjectList()
                        .size()
        ) - 1;

        String confirmation = readNonEmpty(
                scanner,
                "Are you sure? (yes/no): "
        );

        if (confirmation.equalsIgnoreCase("yes")) {

            service.removeProject(index);

            System.out.println(
                    "Project deleted successfully!"
            );

            saveResumeSilently(service);

        } else {

            System.out.println(
                    "Deletion cancelled."
            );
        }
    }

    // =========================================================
    // DELETE SKILL
    // =========================================================

    private static void deleteSkill(
            Scanner scanner,
            ResumeService service) {

        if (service.getResume()
                .getSkillList()
                .isEmpty()) {

            System.out.println();
            System.out.println(
                    "No skills available."
            );

            return;
        }

        displaySkills(service);

        int index = readNumber(
                scanner,
                "Select skill to delete: ",
                1,
                service.getResume()
                        .getSkillList()
                        .size()
        ) - 1;

        String confirmation = readNonEmpty(
                scanner,
                "Are you sure? (yes/no): "
        );

        if (confirmation.equalsIgnoreCase("yes")) {

            service.removeSkill(index);

            System.out.println(
                    "Skill deleted successfully!"
            );

            saveResumeSilently(service);

        } else {

            System.out.println(
                    "Deletion cancelled."
            );
        }
    }

    // =========================================================
    // ADD MORE ENTRIES
    // =========================================================

    private static void addMoreEntries(
            Scanner scanner,
            ResumeService service) {

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("           ADD MORE ENTRIES");
        System.out.println("----------------------------------------");
        System.out.println("1. Add Education");
        System.out.println("2. Add Project");
        System.out.println("3. Add Skill");
        System.out.println("4. Back");

        int choice = readNumber(
                scanner,
                "Enter your choice: ",
                1,
                4
        );

        switch (choice) {

            case 1:
                createEducationEntries(scanner, service);
                break;

            case 2:
                createProjectEntries(scanner, service);
                break;

            case 3:
                createSkillEntries(scanner, service);
                break;

            case 4:
                return;

            default:
                System.out.println(
                        "Invalid choice."
                );
        }

        saveResumeSilently(service);
    }

    // =========================================================
    // DISPLAY EDUCATION
    // =========================================================

    private static void displayEducation(
            ResumeService service) {

        System.out.println();
        System.out.println("----- EDUCATION ENTRIES -----");

        for (int i = 0;
             i < service.getResume()
                    .getEducationList()
                    .size();
             i++) {

            Education education =
                    service.getResume()
                            .getEducationList()
                            .get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + education.getDegree()
                    + " | "
                    + education.getInstitution()
                    + " | "
                    + education.getStartDate()
                    + " - "
                    + education.getEndDate()
            );
        }
    }

    // =========================================================
    // DISPLAY PROJECTS
    // =========================================================

    private static void displayProjects(
            ResumeService service) {

        System.out.println();
        System.out.println("----- PROJECT ENTRIES -----");

        for (int i = 0;
             i < service.getResume()
                    .getProjectList()
                    .size();
             i++) {

            Project project =
                    service.getResume()
                            .getProjectList()
                            .get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + project.getProjectName()
                    + " | "
                    + project.getTechnologies()
            );
        }
    }

    // =========================================================
    // DISPLAY SKILLS
    // =========================================================

    private static void displaySkills(
            ResumeService service) {

        System.out.println();
        System.out.println("----- SKILL ENTRIES -----");

        for (int i = 0;
             i < service.getResume()
                    .getSkillList()
                    .size();
             i++) {

            Skill skill =
                    service.getResume()
                            .getSkillList()
                            .get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + skill.getName()
                    + " | "
                    + skill.getCategory()
                    + " | "
                    + skill.getProficiency()
            );
        }
    }

    // =========================================================
    // SAVE RESUME
    // =========================================================

    private static void saveResume(
            ResumeService service) {

        String formattedResume =
                ResumeFormatter.format(
                        service.getResume(),
                        selectedTemplate
                );

        ResumeFileWriter.writeResume(
                formattedResume,
                service.getResume(),
                selectedTemplate
        );
    }

    // =========================================================
    // SILENT SAVE
    // =========================================================

    private static void saveResumeSilently(
            ResumeService service) {

        String formattedResume =
                ResumeFormatter.format(
                        service.getResume(),
                        selectedTemplate
                );

        ResumeFileWriter.writeResume(
                formattedResume,
                service.getResume(),
                selectedTemplate
        );
    }

    // =========================================================
    // READ NON-EMPTY INPUT
    // =========================================================

    private static String readNonEmpty(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }

    // =========================================================
    // OPTIONAL NON-EMPTY INPUT
    // =========================================================

    private static String readOptionalNonEmpty(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (input.isEmpty()) {
                return "";
            }

            return input;
        }
    }

    // =========================================================
    // EMAIL VALIDATION
    // =========================================================

    private static String readEmail(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String email =
                    scanner.nextLine().trim();

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                return email;
            }

            System.out.println(
                    "Invalid email. Please enter a valid email address."
            );
        }
    }

    // =========================================================
    // OPTIONAL EMAIL
    // =========================================================

    private static String readOptionalEmail(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String email =
                    scanner.nextLine().trim();

            if (email.isEmpty()) {
                return "";
            }

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                return email;
            }

            System.out.println(
                    "Invalid email. Please try again."
            );
        }
    }

    // =========================================================
    // PHONE VALIDATION
    // =========================================================

    private static String readPhone(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String phone =
                    scanner.nextLine().trim();

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

    // =========================================================
    // OPTIONAL PHONE
    // =========================================================

    private static String readOptionalPhone(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String phone =
                    scanner.nextLine().trim();

            if (phone.isEmpty()) {
                return "";
            }

            if (phone.matches(
                    "(\\+91[- ]?)?[6-9][0-9]{9}")) {

                return phone;
            }

            System.out.println(
                    "Invalid phone number. Please try again."
            );
        }
    }

    // =========================================================
    // YEAR VALIDATION
    // =========================================================

    private static String readYear(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String year =
                    scanner.nextLine().trim();

            if (year.matches("[0-9]{4}")) {

                int value =
                        Integer.parseInt(year);

                if (value >= 1900 && value <= 2100) {
                    return year;
                }
            }

            System.out.println(
                    "Invalid year. Enter a four-digit year "
                    + "between 1900 and 2100."
            );
        }
    }

    // =========================================================
    // OPTIONAL YEAR
    // =========================================================

    private static String readOptionalYear(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String year =
                    scanner.nextLine().trim();

            if (year.isEmpty()) {
                return "";
            }

            if (year.matches("[0-9]{4}")) {

                int value =
                        Integer.parseInt(year);

                if (value >= 1900 && value <= 2100) {
                    return year;
                }
            }

            System.out.println(
                    "Invalid year. Please enter a valid four-digit year."
            );
        }
    }

    // =========================================================
    // END YEAR VALIDATION
    // =========================================================

    private static String readEndYear(
            Scanner scanner,
            String startYear,
            String message) {

        int start =
                Integer.parseInt(startYear);

        while (true) {

            System.out.print(message);

            String endYear =
                    scanner.nextLine().trim();

            if (endYear.matches("[0-9]{4}")) {

                int end =
                        Integer.parseInt(endYear);

                if (end >= start && end <= 2100) {
                    return endYear;
                }
            }

            System.out.println(
                    "End year cannot be earlier than start year."
            );
        }
    }

    // =========================================================
    // OPTIONAL END YEAR
    // =========================================================

    private static String readOptionalEndYear(
            Scanner scanner,
            String startYear,
            String message) {

        while (true) {

            System.out.print(message);

            String endYear =
                    scanner.nextLine().trim();

            if (endYear.isEmpty()) {
                return "";
            }

            if (endYear.matches("[0-9]{4}")) {

                int start =
                        Integer.parseInt(startYear);

                int end =
                        Integer.parseInt(endYear);

                if (end >= start && end <= 2100) {
                    return endYear;
                }
            }

            System.out.println(
                    "End year cannot be earlier than start year."
            );
        }
    }

    // =========================================================
    // POSITIVE NUMBER
    // =========================================================

    private static int readPositiveNumber(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                int value =
                        Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

            } catch (NumberFormatException e) {

                // Handled below.
            }

            System.out.println(
                    "Please enter a positive whole number."
            );
        }
    }

    // =========================================================
    // NUMBER WITH RANGE
    // =========================================================

    private static int readNumber(
            Scanner scanner,
            String message,
            int minimum,
            int maximum) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                int value =
                        Integer.parseInt(input);

                if (value >= minimum
                        && value <= maximum) {

                    return value;
                }

            } catch (NumberFormatException e) {

                // Handled below.
            }

            System.out.println(
                    "Please enter a number between "
                    + minimum
                    + " and "
                    + maximum
                    + "."
            );
        }
    }
}