package manager;

import com.jcabi.immutable.ArrayComparator;
import concatenation.Concat;
import reverse.RunScript;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReverseManager {
    public static class ReverseBuilder {
        private List<String> projects;
        private List<String> projectsReverse;
        private String arg;
        private String revPython = "../reverse/reverse_python/PyRev.py";
        private String revCSharp = "../reverse/reverse_c#/c#.py";
        private String revJava = "../reverse/reverse_java/JavaParser.py";
        private Concat concat;

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
        public ReverseBuilder withConcat(Concat concat) {
            this.concat = concat;
            return this;
        }

        public ReverseManager build(){
            ReverseManager manager = new ReverseManager();
            manager.revCSharp = revCSharp;
            manager.revJava = revJava;
            manager.revPython = revPython;
            manager.projects = this.projects;
            manager.concat= concat;
            return manager;
        }
    }

    private String revPython;
    private String revCSharp;
    private String revJava;
    private List<String> projects;
    private Concat concat;

    private ReverseManager() {

    }

    public void callReverseAll() {
        for (String project : projects) {
            callCSharp(project);
            callPython(project);
            callJava(project);
            try {
                concat.run(new File(project));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
