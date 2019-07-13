package kz.kakimzhanova.parser.parser;

import kz.kakimzhanova.parser.entity.Paper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPaperBuilder {
    protected List <Paper> papers;
    protected AbstractPaperBuilder(){
        papers = new ArrayList<>();
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public abstract List<Paper> buildSetPapers(String fileName);
}
