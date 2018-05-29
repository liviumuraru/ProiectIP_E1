package manager;

import com.jcabi.immutable.ArrayComparator;
import reverse.RunScript;

import java.util.List;

public class ReverseManager {
    public static class ReverseBuilder {
        private List<String> projects;
        private List<String> projectsReverse;
        private String arg;

        public ReverseBuilder() {
        }

        public ReverseBuilder withProjects(List<String> projects) {
            this.projects = projects;
            return this;
        }

        public ReverseBuilder withArg(String arg) {
            this.arg = arg;
            return this;
        }

        public ReverseManager build(){
            ReverseManager manager = new ReverseManager();

            manager.projects = this.projects;
            manager.reverseArgs = this.arg;
            return manager;
        }
    }

    private String reverseFolder = "../reverse/reverse_";
    private String reverseString = reverseFolder + "c#/c#.py";;
    private String reverseArgs = "default_folder";

    private String revPython = "python/PyRev.py";
    private String revCSharp = "c#/c#.py";
    private String revJava = "java/JavaParser.py";

    private List<String> projects;

    private ReverseManager() {

    }

    public void callReverseAll() {
        for(int i = 0; i < projects.size(); i++) {
            callCSharp(projects.get(i));
            callPython(projects.get(i));
            callJava(projects.get(i));
        }
    }

    public void callReverse(List<String> projectsReverse) {
        if (projects.size() != projectsReverse.size()) {
            System.out.println("Projects' paths list size must equal projects' reverse list size.");
            return;
        }

        for(int i = 0; i < projects.size(); i++) {
            callScript(projectsReverse.get(i), projects.get(i));
            //callCSharp(projects.get(i));
            //callPython(projects.get(i));
            //callJava(projects.get(i));
        }
    }

    public void callScript(String script, String project) {
        RunScript.run(script, project);
    }

    public void callCSharp(String project) {
        RunScript.run(reverseFolder + revCSharp, project);
    }

    public void callPython(String project) {
        RunScript.run(reverseFolder + revPython, project);
    }

    public void callJava(String project) {
        RunScript.run(reverseFolder + revJava, project);
    }
}
