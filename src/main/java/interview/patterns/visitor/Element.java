package interview.patterns.visitor;

public interface Element {
    void visitBy(Visitor v);
}
