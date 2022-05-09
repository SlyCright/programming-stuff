package interview.patterns.visitor;

public class VisitorTwo implements Visitor{

    @Override
    public void visitTo(ElementUno elementUno) {
        System.out.println(elementUno.getClass().toString()+" is visiting by "+this.getClass());
    }

    @Override
    public void visitTo(ElementDos elementDos) {
        System.out.println(elementDos.getClass().toString()+" is visiting by "+this.getClass());
    }

    @Override
    public void visitTo(ElementTres elementTres) {
        System.out.println(elementTres.getClass().toString()+" is visiting by "+this.getClass());
    }

}
