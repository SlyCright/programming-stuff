package interview.patterns.visitor;

public class ElementDos implements Element {
    @Override
    public void visitBy(Visitor v) {
        v.visitTo(this);
    }
}
