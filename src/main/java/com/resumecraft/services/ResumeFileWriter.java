package com.resumecraft.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.resumecraft.model.Resume;

public class ResumeFileWriter {

    public static void writeResume(String resumeText) {

        File outputDirectory = new File("output");

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        // -------------------------------------------------
        // SAVE TEXT VERSION
        // -------------------------------------------------

        File resumeFile = new File(outputDirectory, "resume.txt");

        try (FileWriter writer = new FileWriter(resumeFile)) {

            writer.write(resumeText);

            System.out.println();
            System.out.println("Resume saved successfully!");
            System.out.println("Text file location: "
                    + resumeFile.getAbsolutePath());

        } catch (IOException e) {

            System.out.println("Error while saving text resume.");
            e.printStackTrace();
        }

        // -------------------------------------------------
        // SAVE MODERN HTML VERSION
        // -------------------------------------------------

        File htmlFile = new File(outputDirectory, "resume.html");

        try (FileWriter writer = new FileWriter(htmlFile)) {

            writer.write(createModernHTML(resumeText));

            System.out.println("Modern HTML resume saved successfully!");
            System.out.println("HTML file location: "
                    + htmlFile.getAbsolutePath());

        } catch (IOException e) {

            System.out.println("Error while saving HTML resume.");
            e.printStackTrace();
        }
    }

    // =====================================================
    // NEW METHOD - TEMPLATE SUPPORT
    // =====================================================

    public static void writeResume(
            String resumeText,
            Resume resume,
            ResumeTemplate template) {

        if (template == null) {
            template = ResumeTemplate.MODERN;
        }

        // -------------------------------------------------
        // SAVE TEXT VERSION
        // -------------------------------------------------

        File outputDirectory = new File("output");

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        File resumeFile = new File(outputDirectory, "resume.txt");

        try (FileWriter writer = new FileWriter(resumeFile)) {

            writer.write(resumeText);

            System.out.println();
            System.out.println("Resume saved successfully!");
            System.out.println("Text file location: "
                    + resumeFile.getAbsolutePath());

        } catch (IOException e) {

            System.out.println("Error while saving text resume.");
            e.printStackTrace();
        }

        // -------------------------------------------------
        // SAVE HTML VERSION BASED ON SELECTED TEMPLATE
        // -------------------------------------------------

        File htmlFile = new File(
                outputDirectory,
                "resume-" + template.name().toLowerCase() + ".html"
        );

        try (FileWriter writer = new FileWriter(htmlFile)) {

            String htmlContent;

            switch (template) {

                case CLASSIC:
                    htmlContent = createClassicHTML(resumeText);
                    break;

                case MINIMAL:
                    htmlContent = createMinimalHTML(resumeText);
                    break;

                case MODERN:
                default:
                    htmlContent = createModernHTML(resumeText);
                    break;
            }

            writer.write(htmlContent);

            System.out.println("HTML resume saved successfully!");
            System.out.println("HTML file location: "
                    + htmlFile.getAbsolutePath());

        } catch (IOException e) {

            System.out.println("Error while saving HTML resume.");
            e.printStackTrace();
        }
    }

    // =====================================================
    // MODERN HTML RESUME TEMPLATE
    // =====================================================

    private static String createModernHTML(String resumeText) {

        String[] lines = resumeText.split("\\r?\\n");

        StringBuilder html = new StringBuilder();

        html.append("""
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ResumeCraft - Resume</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
            background: #eef1f5;
            color: #222;
            line-height: 1.6;
            padding: 40px 20px;
        }

        .resume {
            max-width: 900px;
            margin: auto;
            background: white;
            box-shadow: 0 10px 35px rgba(0, 0, 0, 0.12);
            border-radius: 12px;
            overflow: hidden;
        }

        /* -----------------------------------------------
           HEADER
        ------------------------------------------------ */

        .header {
            background: linear-gradient(135deg, #111827, #374151);
            color: white;
            padding: 45px 55px;
        }

        .brand {
            font-size: 13px;
            letter-spacing: 4px;
            color: #9ca3af;
            margin-bottom: 15px;
            font-weight: bold;
        }

        .name {
            font-size: 38px;
            font-weight: 700;
            margin-bottom: 12px;
        }

        .contact {
            color: #d1d5db;
            font-size: 14px;
            line-height: 1.8;
        }

        .contact a {
            color: #d1d5db;
            text-decoration: none;
        }

        .contact a:hover {
            text-decoration: underline;
        }

        /* -----------------------------------------------
           CONTENT
        ------------------------------------------------ */

        .content {
            padding: 40px 55px;
        }

        .section {
            margin-bottom: 35px;
        }

        .section-title {
            font-size: 17px;
            font-weight: 700;
            color: #111827;
            text-transform: uppercase;
            letter-spacing: 2px;
            margin-bottom: 12px;
            padding-bottom: 8px;
            border-bottom: 2px solid #e5e7eb;
        }

        .section-title::after {
            content: "";
            display: block;
            width: 55px;
            border-bottom: 3px solid #374151;
            position: relative;
            top: 10px;
        }

        .summary {
            color: #4b5563;
            font-size: 15px;
        }

        .entry {
            margin-bottom: 22px;
        }

        .entry-title {
            font-size: 17px;
            font-weight: bold;
            color: #111827;
        }

        .entry-details {
            color: #6b7280;
            font-size: 14px;
        }

        .description {
            color: #4b5563;
            font-size: 14px;
            margin-top: 5px;
        }

        .skill {
            display: inline-block;
            background: #f3f4f6;
            border: 1px solid #e5e7eb;
            padding: 7px 12px;
            margin: 5px 5px 5px 0;
            border-radius: 20px;
            font-size: 13px;
            color: #374151;
        }

        .footer {
            text-align: center;
            padding: 20px;
            background: #f9fafb;
            color: #9ca3af;
            font-size: 12px;
        }

        @media (max-width: 650px) {

            body {
                padding: 10px;
            }

            .header {
                padding: 30px 25px;
            }

            .content {
                padding: 30px 25px;
            }

            .name {
                font-size: 30px;
            }
        }

    </style>
</head>

<body>

<div class="resume">

    <div class="header">

        <div class="brand">
            RESUMECRAFT
        </div>
""");

        // -------------------------------------------------
        // FIND BASIC PERSONAL INFORMATION
        // -------------------------------------------------

        String name = "";
        String email = "";
        String phone = "";
        String location = "";
        String linkedin = "";
        String github = "";

        for (String line : lines) {

            String trimmed = line.trim();

            if (trimmed.startsWith("Email:")) {
                email = trimmed.substring(6).trim();
            }

            if (trimmed.startsWith("Phone:")) {
                phone = trimmed.substring(6).trim();
            }

            if (trimmed.startsWith("Location:")) {
                location = trimmed.substring(9).trim();
            }

            if (trimmed.startsWith("LinkedIn:")) {
                linkedin = trimmed.substring(9).trim();
            }

            if (trimmed.startsWith("GitHub:")) {
                github = trimmed.substring(7).trim();
            }
        }

        // Find name from first meaningful line after RESUMECRAFT
        for (String line : lines) {

            String trimmed = line.trim();

            if (!trimmed.isEmpty()
                    && !trimmed.equalsIgnoreCase("RESUMECRAFT")
                    && !trimmed.equalsIgnoreCase("JAVA RESUME BUILDER")
                    && !trimmed.equals("========================================")
                    && !trimmed.equals("----------------------------------------")
                    && !trimmed.equals("----------------------------------------")) {

                if (!trimmed.equalsIgnoreCase("SUMMARY")
                        && !trimmed.equalsIgnoreCase("EDUCATION")
                        && !trimmed.equalsIgnoreCase("PROJECTS")
                        && !trimmed.equalsIgnoreCase("SKILLS")) {

                    name = trimmed;
                    break;
                }
            }
        }

        html.append("        <div class=\"name\">")
                .append(escapeHTML(name))
                .append("</div>\n");

        html.append("        <div class=\"contact\">\n");

        if (!email.isEmpty()) {
            html.append(escapeHTML(email)).append(" &nbsp; | &nbsp; ");
        }

        if (!phone.isEmpty()) {
            html.append(escapeHTML(phone)).append(" &nbsp; | &nbsp; ");
        }

        if (!location.isEmpty()) {
            html.append(escapeHTML(location));
        }

        html.append("<br>");

        if (!linkedin.isEmpty()) {
            html.append("LinkedIn: ")
                    .append(escapeHTML(linkedin))
                    .append(" &nbsp;&nbsp; ");
        }

        if (!github.isEmpty()) {
            html.append("GitHub: ")
                    .append(escapeHTML(github));
        }

        html.append("""
        </div>

    </div>

    <div class="content">
""");

        // -------------------------------------------------
        // RESUME SECTIONS
        // -------------------------------------------------

        String currentSection = "";

        for (String line : lines) {

            String trimmed = line.trim();

            if (trimmed.isEmpty()) {
                continue;
            }

            // Ignore formatting lines from TXT resume
            if (isSeparator(trimmed)) {
                continue;
            }

            if (trimmed.equalsIgnoreCase("SUMMARY")) {

                closePreviousSection(html, currentSection);

                currentSection = "SUMMARY";

                html.append("""
                    <div class="section">
                        <div class="section-title">Summary</div>
""");

                continue;
            }

            if (trimmed.equalsIgnoreCase("EDUCATION")) {

                closePreviousSection(html, currentSection);

                currentSection = "EDUCATION";

                html.append("""
                    <div class="section">
                        <div class="section-title">Education</div>
""");

                continue;
            }

            if (trimmed.equalsIgnoreCase("PROJECTS")) {

                closePreviousSection(html, currentSection);

                currentSection = "PROJECTS";

                html.append("""
                    <div class="section">
                        <div class="section-title">Projects</div>
""");

                continue;
            }

            if (trimmed.equalsIgnoreCase("SKILLS")) {

                closePreviousSection(html, currentSection);

                currentSection = "SKILLS";

                html.append("""
                    <div class="section">
                        <div class="section-title">Skills</div>
""");

                continue;
            }

            // Ignore duplicate personal information in body
            if (trimmed.startsWith("Email:")
                    || trimmed.startsWith("Phone:")
                    || trimmed.startsWith("Location:")
                    || trimmed.startsWith("LinkedIn:")
                    || trimmed.startsWith("GitHub:")) {
                continue;
            }

            // Ignore ResumeCraft title lines
            if (trimmed.equalsIgnoreCase("RESUMECRAFT")
                    || trimmed.equalsIgnoreCase("JAVA RESUME BUILDER")
                    || trimmed.equalsIgnoreCase("END OF RESUME")) {
                continue;
            }

            // ---------------------------------------------
            // SUMMARY
            // ---------------------------------------------

            if (currentSection.equals("SUMMARY")) {

                html.append("<p class=\"summary\">")
                        .append(escapeHTML(trimmed))
                        .append("</p>\n");
            }

            // ---------------------------------------------
            // EDUCATION
            // ---------------------------------------------

            else if (currentSection.equals("EDUCATION")) {

                html.append("<div class=\"entry\">");

                html.append("<div class=\"entry-title\">")
                        .append(escapeHTML(trimmed))
                        .append("</div>");

                html.append("</div>\n");
            }

            // ---------------------------------------------
            // PROJECTS
            // ---------------------------------------------

            else if (currentSection.equals("PROJECTS")) {

                if (trimmed.startsWith("Technologies:")) {

                    html.append("<div class=\"entry-details\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");

                } else if (trimmed.startsWith("Description:")) {

                    html.append("<div class=\"description\">")
                            .append(escapeHTML(trimmed.substring(12).trim()))
                            .append("</div>");

                } else if (trimmed.startsWith("GitHub:")) {

                    html.append("<div class=\"entry-details\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");

                } else {

                    html.append("<div class=\"entry\">")
                            .append("<div class=\"entry-title\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");
                }
            }

            // ---------------------------------------------
            // SKILLS
            // ---------------------------------------------

            else if (currentSection.equals("SKILLS")) {

                String skillText = trimmed;

                html.append("<span class=\"skill\">")
                        .append(escapeHTML(skillText))
                        .append("</span>\n");
            }
        }

        closePreviousSection(html, currentSection);

        html.append("""
    </div>

    <div class="footer">
        Created with ResumeCraft &bull; Java Resume Builder
    </div>

</div>

</body>
</html>
""");

        return html.toString();
    }


    // =====================================================
    // CLASSIC HTML RESUME TEMPLATE
    // =====================================================

    private static String createClassicHTML(String resumeText) {

        String[] lines = resumeText.split("\\r?\\n");
        StringBuilder html = new StringBuilder();

        html.append("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ResumeCraft - Classic Resume</title>
    <style>
        * { box-sizing: border-box; }
        body {
            font-family: Georgia, "Times New Roman", serif;
            background: #f5f5f5;
            color: #222;
            line-height: 1.55;
            padding: 35px 20px;
        }
        .resume {
            max-width: 850px;
            margin: auto;
            background: white;
            padding: 55px 65px;
            box-shadow: 0 4px 18px rgba(0,0,0,.10);
        }
        .header {
            text-align: center;
            border-bottom: 2px solid #222;
            padding-bottom: 22px;
            margin-bottom: 28px;
        }
        .brand {
            font-size: 11px;
            letter-spacing: 3px;
            margin-bottom: 10px;
        }
        .name {
            font-size: 34px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .contact {
            font-family: Arial, sans-serif;
            font-size: 13px;
            color: #444;
            line-height: 1.7;
        }
        .contact a { color: inherit; text-decoration: none; }
        .section {
            margin-bottom: 28px;
        }
        .section-title {
            font-size: 17px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
            border-bottom: 1px solid #555;
            padding-bottom: 5px;
            margin-bottom: 12px;
        }
        .summary {
            font-size: 14px;
            text-align: justify;
        }
        .entry {
            margin-bottom: 16px;
        }
        .entry-title {
            font-size: 16px;
            font-weight: bold;
        }
        .entry-details {
            font-family: Arial, sans-serif;
            font-size: 13px;
            color: #555;
        }
        .description {
            font-size: 14px;
            margin-top: 4px;
        }
        .skill {
            display: inline-block;
            margin: 3px 10px 3px 0;
            font-size: 14px;
        }
        .footer {
            border-top: 1px solid #aaa;
            margin-top: 30px;
            padding-top: 12px;
            text-align: center;
            font-family: Arial, sans-serif;
            color: #777;
            font-size: 11px;
        }
        @media (max-width: 650px) {
            body { padding: 10px; }
            .resume { padding: 30px 25px; }
            .name { font-size: 28px; }
        }
    </style>
</head>
<body>
<div class="resume">
    <div class="header">
        <div class="brand">RESUMECRAFT</div>
""");

        String name = "";
        String email = "";
        String phone = "";
        String location = "";
        String linkedin = "";
        String github = "";

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.startsWith("Email:")) {
                email = trimmed.substring(6).trim();
            }
            if (trimmed.startsWith("Phone:")) {
                phone = trimmed.substring(6).trim();
            }
            if (trimmed.startsWith("Location:")) {
                location = trimmed.substring(9).trim();
            }
            if (trimmed.startsWith("LinkedIn:")) {
                linkedin = trimmed.substring(9).trim();
            }
            if (trimmed.startsWith("GitHub:")) {
                github = trimmed.substring(7).trim();
            }
        }

        for (String line : lines) {
            String trimmed = line.trim();

            if (!trimmed.isEmpty()
                    && !trimmed.equalsIgnoreCase("RESUMECRAFT")
                    && !trimmed.equalsIgnoreCase("JAVA RESUME BUILDER")
                    && !trimmed.equalsIgnoreCase("SUMMARY")
                    && !trimmed.equalsIgnoreCase("EDUCATION")
                    && !trimmed.equalsIgnoreCase("PROJECTS")
                    && !trimmed.equalsIgnoreCase("SKILLS")
                    && !isSeparator(trimmed)) {
                name = trimmed;
                break;
            }
        }

        html.append("        <div class=\"name\">")
                .append(escapeHTML(name))
                .append("</div>\n");

        html.append("        <div class=\"contact\">\n");

        if (!email.isEmpty()) {
            html.append(escapeHTML(email)).append(" &nbsp; | &nbsp; ");
        }
        if (!phone.isEmpty()) {
            html.append(escapeHTML(phone)).append(" &nbsp; | &nbsp; ");
        }
        if (!location.isEmpty()) {
            html.append(escapeHTML(location));
        }

        if (!linkedin.isEmpty() || !github.isEmpty()) {
            html.append("<br>");
        }
        if (!linkedin.isEmpty()) {
            html.append("LinkedIn: ").append(escapeHTML(linkedin));
        }
        if (!linkedin.isEmpty() && !github.isEmpty()) {
            html.append(" &nbsp; | &nbsp; ");
        }
        if (!github.isEmpty()) {
            html.append("GitHub: ").append(escapeHTML(github));
        }

        html.append("""
        </div>
    </div>
    <div class="content">
""");

        String currentSection = "";

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.isEmpty() || isSeparator(trimmed)) {
                continue;
            }

            if (trimmed.equalsIgnoreCase("SUMMARY")) {
                closePreviousSection(html, currentSection);
                currentSection = "SUMMARY";
                html.append("""
        <div class="section">
            <div class="section-title">Professional Summary</div>
""");
                continue;
            }

            if (trimmed.equalsIgnoreCase("EDUCATION")) {
                closePreviousSection(html, currentSection);
                currentSection = "EDUCATION";
                html.append("""
        <div class="section">
            <div class="section-title">Education</div>
""");
                continue;
            }

            if (trimmed.equalsIgnoreCase("PROJECTS")) {
                closePreviousSection(html, currentSection);
                currentSection = "PROJECTS";
                html.append("""
        <div class="section">
            <div class="section-title">Projects</div>
""");
                continue;
            }

            if (trimmed.equalsIgnoreCase("SKILLS")) {
                closePreviousSection(html, currentSection);
                currentSection = "SKILLS";
                html.append("""
        <div class="section">
            <div class="section-title">Skills</div>
""");
                continue;
            }

            if (trimmed.startsWith("Email:")
                    || trimmed.startsWith("Phone:")
                    || trimmed.startsWith("Location:")
                    || trimmed.startsWith("LinkedIn:")
                    || trimmed.startsWith("GitHub:")
                    || trimmed.equalsIgnoreCase("RESUMECRAFT")
                    || trimmed.equalsIgnoreCase("JAVA RESUME BUILDER")
                    || trimmed.equalsIgnoreCase("END OF RESUME")) {
                continue;
            }

            if (currentSection.equals("SUMMARY")) {
                html.append("<p class=\"summary\">")
                        .append(escapeHTML(trimmed))
                        .append("</p>\n");

            } else if (currentSection.equals("EDUCATION")) {
                html.append("<div class=\"entry\">")
                        .append("<div class=\"entry-title\">")
                        .append(escapeHTML(trimmed))
                        .append("</div>")
                        .append("</div>\n");

            } else if (currentSection.equals("PROJECTS")) {
                if (trimmed.startsWith("Technologies:")) {
                    html.append("<div class=\"entry-details\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");
                } else if (trimmed.startsWith("Description:")) {
                    html.append("<div class=\"description\">")
                            .append(escapeHTML(trimmed.substring(12).trim()))
                            .append("</div>");
                } else if (trimmed.startsWith("GitHub:")) {
                    html.append("<div class=\"entry-details\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");
                } else {
                    html.append("<div class=\"entry\">")
                            .append("<div class=\"entry-title\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");
                }

            } else if (currentSection.equals("SKILLS")) {
                html.append("<span class=\"skill\">")
                        .append(escapeHTML(trimmed))
                        .append("</span>\n");
            }
        }

        closePreviousSection(html, currentSection);

        html.append("""
    </div>
    <div class="footer">
        Created with ResumeCraft &bull; Classic Template
    </div>
</div>
</body>
</html>
""");

        return html.toString();
    }


    // =====================================================
    // MINIMAL HTML RESUME TEMPLATE
    // =====================================================

    private static String createMinimalHTML(String resumeText) {

        String[] lines = resumeText.split("\\r?\\n");
        StringBuilder html = new StringBuilder();

        html.append("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ResumeCraft - Minimal Resume</title>
    <style>
        * { box-sizing: border-box; }
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: white;
            color: #222;
            line-height: 1.55;
            padding: 35px 20px;
        }
        .resume {
            max-width: 780px;
            margin: auto;
        }
        .header {
            padding-bottom: 20px;
            border-bottom: 1px solid #222;
            margin-bottom: 25px;
        }
        .brand {
            font-size: 10px;
            letter-spacing: 3px;
            color: #777;
            margin-bottom: 8px;
        }
        .name {
            font-size: 32px;
            font-weight: 600;
            margin-bottom: 8px;
        }
        .contact {
            font-size: 12px;
            color: #666;
            line-height: 1.7;
        }
        .section {
            margin-bottom: 25px;
        }
        .section-title {
            font-size: 13px;
            font-weight: 700;
            letter-spacing: 2px;
            text-transform: uppercase;
            margin-bottom: 9px;
        }
        .summary {
            font-size: 14px;
            color: #444;
        }
        .entry {
            margin-bottom: 12px;
        }
        .entry-title {
            font-size: 14px;
            font-weight: 600;
        }
        .entry-details,
        .description {
            font-size: 13px;
            color: #555;
        }
        .skill {
            display: inline-block;
            font-size: 13px;
            margin: 0 14px 5px 0;
        }
        .footer {
            margin-top: 35px;
            padding-top: 10px;
            border-top: 1px solid #ddd;
            color: #999;
            font-size: 10px;
        }
        @media (max-width: 650px) {
            body { padding: 15px; }
            .name { font-size: 27px; }
        }
    </style>
</head>
<body>
<div class="resume">
    <div class="header">
        <div class="brand">RESUMECRAFT</div>
""");

        String name = "";
        String email = "";
        String phone = "";
        String location = "";
        String linkedin = "";
        String github = "";

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.startsWith("Email:")) {
                email = trimmed.substring(6).trim();
            }
            if (trimmed.startsWith("Phone:")) {
                phone = trimmed.substring(6).trim();
            }
            if (trimmed.startsWith("Location:")) {
                location = trimmed.substring(9).trim();
            }
            if (trimmed.startsWith("LinkedIn:")) {
                linkedin = trimmed.substring(9).trim();
            }
            if (trimmed.startsWith("GitHub:")) {
                github = trimmed.substring(7).trim();
            }
        }

        for (String line : lines) {
            String trimmed = line.trim();

            if (!trimmed.isEmpty()
                    && !trimmed.equalsIgnoreCase("RESUMECRAFT")
                    && !trimmed.equalsIgnoreCase("JAVA RESUME BUILDER")
                    && !trimmed.equalsIgnoreCase("SUMMARY")
                    && !trimmed.equalsIgnoreCase("EDUCATION")
                    && !trimmed.equalsIgnoreCase("PROJECTS")
                    && !trimmed.equalsIgnoreCase("SKILLS")
                    && !isSeparator(trimmed)) {
                name = trimmed;
                break;
            }
        }

        html.append("        <div class=\"name\">")
                .append(escapeHTML(name))
                .append("</div>\n");

        html.append("        <div class=\"contact\">\n");

        if (!email.isEmpty()) {
            html.append(escapeHTML(email));
        }
        if (!phone.isEmpty()) {
            html.append(" &nbsp; | &nbsp; ").append(escapeHTML(phone));
        }
        if (!location.isEmpty()) {
            html.append(" &nbsp; | &nbsp; ").append(escapeHTML(location));
        }
        if (!linkedin.isEmpty() || !github.isEmpty()) {
            html.append("<br>");
        }
        if (!linkedin.isEmpty()) {
            html.append(escapeHTML(linkedin));
        }
        if (!linkedin.isEmpty() && !github.isEmpty()) {
            html.append(" &nbsp; | &nbsp; ");
        }
        if (!github.isEmpty()) {
            html.append(escapeHTML(github));
        }

        html.append("""
        </div>
    </div>
    <div class="content">
""");

        String currentSection = "";

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.isEmpty() || isSeparator(trimmed)) {
                continue;
            }

            if (trimmed.equalsIgnoreCase("SUMMARY")) {
                closePreviousSection(html, currentSection);
                currentSection = "SUMMARY";
                html.append("""
        <div class="section">
            <div class="section-title">Summary</div>
""");
                continue;
            }

            if (trimmed.equalsIgnoreCase("EDUCATION")) {
                closePreviousSection(html, currentSection);
                currentSection = "EDUCATION";
                html.append("""
        <div class="section">
            <div class="section-title">Education</div>
""");
                continue;
            }

            if (trimmed.equalsIgnoreCase("PROJECTS")) {
                closePreviousSection(html, currentSection);
                currentSection = "PROJECTS";
                html.append("""
        <div class="section">
            <div class="section-title">Projects</div>
""");
                continue;
            }

            if (trimmed.equalsIgnoreCase("SKILLS")) {
                closePreviousSection(html, currentSection);
                currentSection = "SKILLS";
                html.append("""
        <div class="section">
            <div class="section-title">Skills</div>
""");
                continue;
            }

            if (trimmed.startsWith("Email:")
                    || trimmed.startsWith("Phone:")
                    || trimmed.startsWith("Location:")
                    || trimmed.startsWith("LinkedIn:")
                    || trimmed.startsWith("GitHub:")
                    || trimmed.equalsIgnoreCase("RESUMECRAFT")
                    || trimmed.equalsIgnoreCase("JAVA RESUME BUILDER")
                    || trimmed.equalsIgnoreCase("END OF RESUME")) {
                continue;
            }

            if (currentSection.equals("SUMMARY")) {
                html.append("<p class=\"summary\">")
                        .append(escapeHTML(trimmed))
                        .append("</p>\n");

            } else if (currentSection.equals("EDUCATION")) {
                html.append("<div class=\"entry\">")
                        .append("<div class=\"entry-title\">")
                        .append(escapeHTML(trimmed))
                        .append("</div>")
                        .append("</div>\n");

            } else if (currentSection.equals("PROJECTS")) {
                if (trimmed.startsWith("Technologies:")) {
                    html.append("<div class=\"entry-details\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");
                } else if (trimmed.startsWith("Description:")) {
                    html.append("<div class=\"description\">")
                            .append(escapeHTML(trimmed.substring(12).trim()))
                            .append("</div>");
                } else if (trimmed.startsWith("GitHub:")) {
                    html.append("<div class=\"entry-details\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");
                } else {
                    html.append("<div class=\"entry\">")
                            .append("<div class=\"entry-title\">")
                            .append(escapeHTML(trimmed))
                            .append("</div>");
                }

            } else if (currentSection.equals("SKILLS")) {
                html.append("<span class=\"skill\">")
                        .append(escapeHTML(trimmed))
                        .append("</span>\n");
            }
        }

        closePreviousSection(html, currentSection);

        html.append("""
    </div>
    <div class="footer">
        Created with ResumeCraft &bull; Minimal Template
    </div>
</div>
</body>
</html>
""");

        return html.toString();
    }

    // =====================================================
    // HELPER METHODS
    // =====================================================

    private static boolean isSeparator(String line) {

        if (line.length() < 5) {
            return false;
        }

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c != '-' && c != '=' && c != '_') {
                return false;
            }
        }

        return true;
    }

    private static void closePreviousSection(
            StringBuilder html,
            String currentSection) {

        if (!currentSection.isEmpty()) {
            html.append("</div>\n");
        }
    }

    private static String escapeHTML(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}