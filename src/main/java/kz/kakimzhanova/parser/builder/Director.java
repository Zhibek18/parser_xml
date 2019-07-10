package kz.kakimzhanova.parser.builder;

import kz.kakimzhanova.parser.entity.Paper;

public class Director {
    public static Paper createPaper(BaseBuilder builder){
        builder.buildTitle();
        builder.buildType();
        builder.buildMonthly();
        return builder.getPaper();
    }
}
