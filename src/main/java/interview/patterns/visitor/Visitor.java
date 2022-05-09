package interview.patterns.visitor;

public interface Visitor {
    void visitTo(ElementUno elementUno);

    void visitTo(ElementDos elementDos);

    void visitTo(ElementTres elementTres);
}
