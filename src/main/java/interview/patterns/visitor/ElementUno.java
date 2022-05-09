package interview.patterns.visitor;

public class ElementUno implements Element {

    @Override
    public void visitBy(Visitor v) {
        v.visitTo(this);
    }

}
