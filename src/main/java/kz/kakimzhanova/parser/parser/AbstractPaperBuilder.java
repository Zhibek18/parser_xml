package kz.kakimzhanova.parser.parser;

import kz.kakimzhanova.parser.entity.Paper;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPaperBuilder {
    protected Set <Paper> papers;
    protected AbstractPaperBuilder(){
        papers = new HashSet<>();
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public abstract Set<Paper> buildSetPapers(String fileName);
}
