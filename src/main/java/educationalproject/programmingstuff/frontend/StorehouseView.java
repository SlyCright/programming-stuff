package educationalproject.programmingstuff.frontend;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import educationalproject.programmingstuff.servicies.CommodityItemService;
import educationalproject.programmingstuff.servicies.dto.CommodityItemResponseDto;

@Route("/storehouse")
@PageTitle("Storehouse")
public class StorehouseView extends VerticalLayout {

    Grid<CommodityItemResponseDto> grid = new Grid<>(CommodityItemResponseDto.class);

    CommodityItemService commodityItemService;

    public StorehouseView(CommodityItemService commodityItemService) {
        this.commodityItemService = commodityItemService;
        addClassName("storehouse-view");
        setSizeFull();
        configureGrid();

        add(grid);
        update();
    }

    private void update() {
        this.grid.setItems(this.commodityItemService.getAllCommodityItems());
    }

    private void configureGrid() {
        grid.addClassNames("commodity-item-grid");
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(CommodityItemResponseDto::getId).setHeader("Storehouse ID");
        grid.addColumn(commodityItemDto->commodityItemDto.getItemResponseDto().getId()).setHeader("Item ID");
        grid.addColumn(commodityItemDto->commodityItemDto.getItemResponseDto().getTitle()).setHeader("Title");
        grid.addColumn(commodityItemDto->commodityItemDto.getItemResponseDto().getDescription()).setHeader("Description");
        grid.addColumn(commodityItemDto->commodityItemDto.getItemResponseDto().getPrice()).setHeader("Price");
        grid.addColumn(CommodityItemResponseDto::getQuantity).setHeader("Quantity");
        grid.getColumns().forEach(c -> c.setAutoWidth(true));
        grid.getColumns().forEach(c -> c.setResizable(true));
        grid.getColumns().forEach(c -> c.setSortable(true));
    }

}
