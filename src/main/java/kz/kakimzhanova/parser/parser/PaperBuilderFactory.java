package kz.kakimzhanova.parser.parser;

import kz.kakimzhanova.parser.parser.dom.PapersDOMBuilder;
import kz.kakimzhanova.parser.parser.sax.PapersSAXBuilder;
import kz.kakimzhanova.parser.parser.stax.PapersStAXBuilder;

public class PaperBuilderFactory {
    public AbstractPaperBuilder createPaperBuilder(String parserType){
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type){
            case DOM:
                return new PapersDOMBuilder();
            case SAX:
                return new PapersSAXBuilder();
            case STAX:
                return new PapersStAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
