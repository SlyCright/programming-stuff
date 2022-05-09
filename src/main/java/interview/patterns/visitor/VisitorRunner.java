package interview.patterns.visitor;

import java.util.List;

public class VisitorRunner {

    public static void main(String[] args) {
        List<Element> elements = List.of(new ElementUno(), new ElementDos(), new ElementTres());
        List<Visitor> visitors = List.of(new VisitorOne(), new VisitorTwo());

        elements.forEach(e -> visitors.forEach(e::visitBy));
    }

}
