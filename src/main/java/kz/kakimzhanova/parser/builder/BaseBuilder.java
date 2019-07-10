package kz.kakimzhanova.parser.builder;

import kz.kakimzhanova.parser.entity.Paper;

public abstract class BaseBuilder {
    private Paper paper = new Paper();
    public Paper getPaper(){
        return paper;
    }
    public abstract void buildTitle();
    public abstract void buildType();
    public abstract void buildMonthly();
}
