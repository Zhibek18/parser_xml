package kz.kakimzhanova.parser.builder;

import kz.kakimzhanova.parser.entity.Paper;

public class Runner {
    public static void main(String[] args) {
        Paper paper = Director.createPaper(new DomBuilder());
    }
}
