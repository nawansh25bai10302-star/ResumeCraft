# ResumeCraft

## Java Resume Builder

ResumeCraft is a Java-based console application designed to help users create, manage, format, and export professional resumes.

The application collects resume information such as personal details, education, experience, projects, and skills. It validates user input, allows resume information to be managed, provides multiple resume templates, and generates formatted resume output in text and HTML formats.

## Project Overview

Creating a professional resume manually can be time-consuming, especially when information needs to be edited repeatedly or presented in different formats.

ResumeCraft provides a structured and menu-driven solution for creating a resume. The project also demonstrates important Java programming concepts such as object-oriented programming, collections, input validation, exception handling, file handling, modular design, and template-based formatting.

## Objectives

- Provide a simple console-based resume-building workflow.
- Organize resume information into separate model classes.
- Validate user input before storing it.
- Allow users to add, edit, and delete resume information.
- Provide multiple resume templates.
- Generate readable text and HTML resume files.
- Demonstrate modular Java application design.

## Features

- Interactive console-based interface
- Personal information management
- Education management
- Experience management
- Project management
- Skills management
- Input validation
- Resume creation and viewing
- Resume editing
- Resume deletion
- Template selection
- Modern resume template
- Classic resume template
- Minimal resume template
- TXT resume generation
- HTML resume generation
- Local file handling
- File-writing error handling

## Resume Templates

### Modern
A contemporary resume design with modern visual styling and a clean presentation.

### Classic
A traditional professional resume layout suitable for a formal presentation.

### Minimal
A clean and compact layout focused on readability and simplicity.

## Technologies Used

- **Java**
- **Maven**
- **Java Collections Framework**
- **Java File I/O**
- **HTML/CSS**
- **Git**
- **GitHub**

## Project Structure

```text
ResumeCraft/
│
├── pom.xml
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── resumecraft/
│                   ├── Main.java
│                   │
│                   ├── model/
│                   │   ├── Resume.java
│                   │   ├── PersonalInfo.java
│                   │   ├── Education.java
│                   │   ├── Experience.java
│                   │   ├── Project.java
│                   │   └── Skill.java
│                   │
│                   └── services/
│                       ├── ResumeService.java
│                       ├── ResumeFormatter.java
│                       ├── ResumeFileWriter.java
│                       └── ResumeTemplate.java
│
├── screenshots/
│
├── output/
│
├── README.md
├── statement.md
└── .gitignore
```

## Requirements

Install the following before running the project:

- Java JDK
- Apache Maven
- Git

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

## Installation

Clone the repository:

```bash
git clone <YOUR-GITHUB-REPOSITORY-URL>
```

Enter the project directory:

```bash
cd ResumeCraft
```

Build the project:

```bash
mvn clean package
```

A successful build should end with:

```text
BUILD SUCCESS
```

## Running the Application

Run the compiled application with:

```bash
java -cp target/classes com.resumecraft.Main
```

The ResumeCraft console interface will then start.

## Application Workflow

The general workflow is:

1. Start ResumeCraft.
2. Select a resume template.
3. Enter personal information.
4. Enter education information.
5. Enter experience information, where applicable.
6. Enter project information.
7. Enter skills.
8. Review the generated resume.
9. Edit or delete information when required.
10. Generate and save the resume.
11. Open the generated TXT or HTML output.

## Input Validation

ResumeCraft validates user input to reduce invalid data and prevent unexpected program termination.

Examples of validation include:

- Required text fields
- Email format
- Phone number format
- URL format
- Numeric input where required
- Template/menu choices

Invalid input is rejected and the user is prompted to enter the value again.

## Output

The application generates resume files in the project's output directory.

Depending on the selected template, generated HTML files include:

```text
resume-modern.html
resume-classic.html
resume-minimal.html
```

A text version is also generated:

```text
resume.txt
```

## Testing

The project has been tested using:

- Valid user input
- Invalid email input
- Invalid phone input
- Invalid menu/template choices
- Resume creation
- Resume editing
- Resume deletion
- Modern template
- Classic template
- Minimal template
- TXT generation
- HTML generation
- Maven compilation

Build verification:

```bash
mvn clean package
```

Expected result:

```text
BUILD SUCCESS
```

## Design and Architecture

ResumeCraft follows a modular structure.

### Main.java
Handles the console interface, user interaction, menu flow, input collection, and validation.

### Model Package
Contains classes representing resume data:

- `Resume`
- `PersonalInfo`
- `Education`
- `Experience`
- `Project`
- `Skill`

### ResumeService
Manages resume data and provides operations for adding, editing, and removing resume information.

### ResumeFormatter
Converts resume data into formatted resume content and works with the selected template.

### ResumeFileWriter
Handles generation and writing of resume output files, including HTML and text files.

### ResumeTemplate
Represents the supported resume templates:

```text
MODERN
CLASSIC
MINIMAL
```

## Object-Oriented Programming Concepts

The project demonstrates:

- Classes and objects
- Encapsulation
- Constructors
- Methods
- Separation of responsibilities
- Collections
- Modular package organization

## Error Handling

The application uses input validation and exception handling to improve reliability.

File-writing operations handle possible I/O errors so that the application can report a problem instead of failing silently.

## Future Enhancements

Possible future improvements include:

- PDF resume export
- Graphical user interface using JavaFX or Swing
- Database-based persistence
- User accounts
- Additional resume templates
- Resume preview
- Cloud storage
- More advanced resume customization
- Job-description-based resume customization

## Author

**[Your Name]**

**[Registration Number]**

**VIT**

## Academic Project

ResumeCraft was developed as a Java programming project and prepared according to the VITyarthi Build Your Own Project submission requirements.
