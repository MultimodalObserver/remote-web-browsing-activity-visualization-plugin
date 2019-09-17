package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.SearchAction;

import java.util.List;

class SearchsPanel extends BasePanel{

    public SearchsPanel(){
        super();
        this.tableHeaders = this.getTableHeaders();
        this.addHeaders();
        this.columnWidths = new float[]{0.1f, 0.5f, 0.1f, 0.2f, 0.1f};
        this.resizeColumns();
    }

    @Override
    List<String> getTableHeaders() {
        List<String> headers = this.initCommonsHeaders();
        headers.add(this.i18n.s("searchColumnName"));
        headers.add(this.i18n.s("captureTimestampColumnName"));
        return headers;
    }

    @Override
    void updateData(String data) {
        SearchAction searchAction = gson.fromJson(data, SearchAction.class);
        Object[] rowData = new Object[]{
                searchAction.getBrowser(),
                searchAction.getPageUrl(),
                searchAction.getPageTitle(),
                searchAction.getSearch(),
                searchAction.getCaptureTimestamp()
        };
        this.tableModel.addRow(rowData);
    }
}
