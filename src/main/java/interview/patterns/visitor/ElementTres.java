package interview.patterns.visitor;

public class ElementTres implements Element {
    @Override
    public void visitBy(Visitor v) {
        v.visitTo(this);
    }
}
