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

        public ReverseBuilder withReverseScript(List<String> projectsReverse) {
            this.projectsReverse = projectsReverse;
            return this;
        }

        public ReverseBuilder withArg(String arg) {
            this.arg = arg;
            return this;
        }

        public ReverseManager build(){
            ReverseManager manager = new ReverseManager();

            manager.projects = this.projects;
            manager.projectsReverse = this.projectsReverse;
            manager.reverseArgs = this.arg;
            return manager;
        }
    }

    private String reverseFolder = "../reverse/reverse_";
    private String reverseString = reverseFolder + "c#/c#.py";;
    private String reverseArgs = "default_folder";
    private List<String> projects;
    private List<String> projectsReverse;

    private ReverseManager() {

    }

    public void callReverse() {
        if (projects.size() != projectsReverse.size()) {
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
