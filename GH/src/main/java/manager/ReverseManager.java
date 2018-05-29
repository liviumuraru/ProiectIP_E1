package manager;

import reverse.RunScript;

public class ReverseManager {
    String reverseFolder = "../reverse/reverse_";
    String reverseString;
    String reverseArgs;
    List<String> projects;
    List<String> projectsReverse;

    public ReverseManager(List<String> projects, List<String> projectsReverse) {
        this.projects = projects;
        this.projectsReverse = projectsReverse;

        reverseString = reverseFolder + "c#/c#.py";
        reverseArgs = "default_folder";
    }

    public ReverseManager(List<String> projects, String reverseArgs) {
        this.projects = projects;
        this.reverseArgs = reverseArgs;
    }

    public void callReverse() {
        if (projects.size() != projectsLanguage.size()) {
            System.out.println("Projects' paths list size must equal projects' reverse list size.");
            return;
        }

        for(int i = 0; i < projects.size(); i++) {
            callScript(projectsReverse.get(i), projects.get(i));
        }
    }

    public void callScript(String script, String project) {
        RunScript.run(script, project);
    }

    public void callCSharp(String project) {
        RunScript.run(reverseFolder + "c#/c#.py", project);
    }

    public void callPython(String project) {
        RunScript.run(reverseFolder + "python/PyRev.py", project);
    }

    public void callJava(String project) {
        RunScript.run(reverseFolder + "java/JavaParser.py", project);
    }
}
