package com.resumecraft.services;

import com.resumecraft.model.Education;
import com.resumecraft.model.Project;
import com.resumecraft.model.Resume;
import com.resumecraft.model.Skill;

public class ResumeHtmlFormatter {

    public static String format(
            Resume resume,
            ResumeTemplate template) {

        if (template == null) {
            template = ResumeTemplate.MODERN;
        }

        switch (template) {

            case CLASSIC:
                return classicTemplate(resume);

            case MINIMAL:
                return minimalTemplate(resume);

            case MODERN:
            default:
                return modernTemplate(resume);
        }
    }

    // =========================================================
    // MODERN TEMPLATE
    // =========================================================

    private static String modernTemplate(Resume resume) {

        StringBuilder html = new StringBuilder();

        html.append("""
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>ResumeCraft - Resume</title>

<style>

* {
    box-sizing: border-box;
}

body {
    margin: 0;
    padding: 40px 20px;
    background: #eef1f5;
    color: #1f2937;
    font-family: Arial, Helvetica, sans-serif;
    line-height: 1.6;
}

.resume {
    max-width: 900px;
    margin: auto;
    background: white;
    border-radius: 14px;
    overflow: hidden;
    box-shadow: 0 12px 40px rgba(0,0,0,0.12);
}

.header {
    background: linear-gradient(
        135deg,
        #111827,
        #374151
    );

    color: white;
    padding: 45px 55px;
}

.brand {
    font-size: 12px;
    letter-spacing: 4px;
    color: #9ca3af;
    font-weight: bold;
}

.name {
    font-size: 40px;
    font-weight: bold;
    margin: 8px 0;
}

.contact {
    color: #d1d5db;
    font-size: 14px;
}

.contact a {
    color: white;
    text-decoration: none;
}

.contact a:hover {
    text-decoration: underline;
}

.content {
    padding: 40px 55px;
}

.section {
    margin-bottom: 35px;
}

.section-title {
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    letter-spacing: 2px;
    padding-bottom: 8px;
    border-bottom: 2px solid #e5e7eb;
    margin-bottom: 16px;
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
    border-radius: 20px;
    padding: 6px 12px;
    margin: 4px;
    font-size: 13px;
}

.footer {
    text-align: center;
    padding: 18px;
    background: #f9fafb;
    color: #9ca3af;
    font-size: 12px;
}

@media (max-width: 650px) {

    body {
        padding: 10px;
    }

    .header,
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

""");

        appendHeader(html, resume);

        html.append("<div class=\"content\">");

        appendSections(html, resume);

        html.append("""
</div>

<div class="footer">
    Created with ResumeCraft • Java Resume Builder
</div>

</div>

</body>

</html>
""");

        return html.toString();
    }

    // =========================================================
    // CLASSIC TEMPLATE
    // =========================================================

    private static String classicTemplate(Resume resume) {

        StringBuilder html = new StringBuilder();

        html.append("""
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>ResumeCraft - Classic Resume</title>

<style>

body {
    background: white;
    color: #222;
    font-family: Georgia, serif;
    margin: 0;
    padding: 45px;
}

.resume {
    max-width: 850px;
    margin: auto;
}

.header {
    text-align: center;
    border-bottom: 2px solid #222;
    padding-bottom: 22px;
}

.brand {
    font-size: 11px;
    letter-spacing: 3px;
    color: #777;
}

.name {
    font-size: 36px;
    margin: 8px;
}

.contact {
    font-size: 13px;
}

.contact a {
    color: #222;
}

.content {
    padding-top: 30px;
}

.section {
    margin-bottom: 28px;
}

.section-title {
    font-size: 18px;
    text-transform: uppercase;
    border-bottom: 1px solid #222;
    padding-bottom: 5px;
    margin-bottom: 12px;
}

.entry {
    margin-bottom: 18px;
}

.entry-title {
    font-weight: bold;
}

.entry-details {
    color: #555;
    font-size: 14px;
}

.description,
.summary {
    font-size: 14px;
}

.skill {
    margin-right: 15px;
}

</style>

</head>

<body>

<div class="resume">

""");

        appendHeader(html, resume);

        html.append("<div class=\"content\">");

        appendSections(html, resume);

        html.append("""
</div>

</div>

</body>

</html>
""");

        return html.toString();
    }

    // =========================================================
    // MINIMAL TEMPLATE
    // =========================================================

    private static String minimalTemplate(Resume resume) {

        StringBuilder html = new StringBuilder();

        html.append("""
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>ResumeCraft - Minimal Resume</title>

<style>

body {
    background: white;
    color: #222;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 50px;
}

.resume {
    max-width: 820px;
    margin: auto;
}

.header {
    padding-bottom: 25px;
}

.brand {
    font-size: 11px;
    letter-spacing: 3px;
    color: #888;
}

.name {
    font-size: 34px;
    margin: 5px 0;
}

.contact {
    font-size: 13px;
    color: #666;
}

.contact a {
    color: #444;
}

.content {
    padding-top: 30px;
}

.section {
    margin-bottom: 26px;
}

.section-title {
    font-size: 14px;
    letter-spacing: 2px;
    text-transform: uppercase;
    color: #555;
    margin-bottom: 10px;
}

.entry {
    margin-bottom: 16px;
}

.entry-title {
    font-size: 16px;
    font-weight: bold;
}

.entry-details,
.summary {
    font-size: 14px;
    color: #666;
}

.skill {
    font-size: 13px;
    margin-right: 14px;
}

</style>

</head>

<body>

<div class="resume">

""");

        appendHeader(html, resume);

        html.append("<div class=\"content\">");

        appendSections(html, resume);

        html.append("""
</div>

</div>

</body>

</html>
""");

        return html.toString();
    }

    // =========================================================
    // HEADER
    // =========================================================

    private static void appendHeader(
            StringBuilder html,
            Resume resume) {

        html.append("""
<header class="header">

<div class="brand">
    RESUMECRAFT
</div>

""");

        if (resume.getPersonalInfo() != null) {

            html.append("<div class=\"name\">")
                    .append(escape(
                            resume.getPersonalInfo().getFullName()))
                    .append("</div>");

            html.append("<div class=\"contact\">");

            html.append(escape(
                    resume.getPersonalInfo().getEmail()));

            html.append(" &nbsp; | &nbsp; ");

            html.append(escape(
                    resume.getPersonalInfo().getPhone()));

            html.append(" &nbsp; | &nbsp; ");

            html.append(escape(
                    resume.getPersonalInfo().getAddress()));

            html.append("<br><br>");

            appendLink(
                    html,
                    "LinkedIn",
                    resume.getPersonalInfo().getLinkedin());

            html.append(" &nbsp;&nbsp; ");

            appendLink(
                    html,
                    "GitHub",
                    resume.getPersonalInfo().getGithub());

            html.append("</div>");
        }

        html.append("</header>");
    }

    // =========================================================
    // SECTIONS
    // =========================================================

    private static void appendSections(
            StringBuilder html,
            Resume resume) {

        // SUMMARY

        if (resume.getPersonalInfo() != null) {

            sectionStart(html, "Summary");

            html.append("<p class=\"summary\">")
                    .append(escape(
                            resume.getPersonalInfo().getSummary()))
                    .append("</p>");

            html.append("</section>");
        }

        // EDUCATION

        sectionStart(html, "Education");

        for (Education education :
                resume.getEducationList()) {

            html.append("<div class=\"entry\">");

            html.append("<div class=\"entry-title\">")
                    .append(escape(
                            education.getDegree()))
                    .append("</div>");

            html.append("<div class=\"entry-details\">");

            html.append(escape(
                    education.getInstitution()));

            html.append(", ");

            html.append(escape(
                    education.getLocation()));

            html.append("<br>");

            html.append(escape(
                    education.getStartDate()));

            html.append(" - ");

            html.append(escape(
                    education.getEndDate()));

            html.append(" | Grade: ");

            html.append(escape(
                    education.getGrade()));

            html.append("</div>");

            html.append("</div>");
        }

        html.append("</section>");

        // PROJECTS

        sectionStart(html, "Projects");

        for (Project project :
                resume.getProjectList()) {

            html.append("<div class=\"entry\">");

            html.append("<div class=\"entry-title\">")
                    .append(escape(
                            project.getProjectName()))
                    .append("</div>");

            html.append("<div class=\"entry-details\">");

            html.append("Technologies: ");

            html.append(escape(
                    project.getTechnologies()));

            html.append("</div>");

            html.append("<div class=\"description\">");

            html.append(escape(
                    project.getDescription()));

            html.append("</div>");

            html.append("<br>");

            appendLink(
                    html,
                    "GitHub",
                    project.getProjectLink());

            html.append("</div>");
        }

        html.append("</section>");

        // SKILLS

        sectionStart(html, "Skills");

        for (Skill skill :
                resume.getSkillList()) {

            html.append("<span class=\"skill\">");

            html.append(escape(
                    skill.getName()));

            html.append(" • ");

            html.append(escape(
                    skill.getProficiency()));

            html.append("</span>");
        }

        html.append("</section>");
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private static void sectionStart(
            StringBuilder html,
            String title) {

        html.append("<section class=\"section\">");

        html.append("<div class=\"section-title\">")
                .append(title)
                .append("</div>");
    }

    private static void appendLink(
            StringBuilder html,
            String label,
            String url) {

        if (url == null || url.isBlank()) {
            return;
        }

        String href = url.trim();

        if (!href.matches(
                "^[a-zA-Z][a-zA-Z0-9+.-]*://.*$")) {

            href = "https://" + href;
        }

        html.append("<a href=\"")
                .append(escape(href))
                .append("\" target=\"_blank\" rel=\"noopener\">")
                .append(escape(label))
                .append("</a>");
    }

    private static String escape(String text) {

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