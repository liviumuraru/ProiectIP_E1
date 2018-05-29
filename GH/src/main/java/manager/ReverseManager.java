package manager;

import com.jcabi.immutable.ArrayComparator;
import reverse.RunScript;

import java.util.List;

public class ReverseManager {
    public static class ReverseBuilder {
        private List<String> projects;
        private List<String> projectsReverse;
        private String arg;
        private String revPython = "../reverse/reverse_python/PyRev.py";
        private String revCSharp = "../reverse/reverse_c#/c#.py";
        private String revJava = "../reverse/reverse_java/JavaParser.py";


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
            manager.revCSharp = revCSharp;
            manager.revJava = revJava;
            manager.revPython = revPython;
            manager.projects = this.projects;
            return manager;
        }
    }

    private String revPython;
    private String revCSharp;
    private String revJava;
    private List<String> projects;

    private ReverseManager() {

    }

    public void callReverseAll() {
        for (String project : projects) {
            callCSharp(project);
            callPython(project);
            callJava(project);
        }
    }

    public void callScript(String script, String project) {
        RunScript.run(script, project);
    }

    private void callCSharp(String project) {
        RunScript.run(revCSharp, project);
    }

    private void callPython(String project) {
        RunScript.run(revPython, project);
    }

    private void callJava(String project) {
        RunScript.run(revJava, project);
    }
}
