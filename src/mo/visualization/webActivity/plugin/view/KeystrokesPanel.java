package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.Keystroke;

import java.util.List;

public class KeystrokesPanel extends BasePanel {

    public KeystrokesPanel() {
        super();
        this.tableHeaders = this.getTableHeaders();
    }

    @Override
    List<String> getTableHeaders() {
        List<String> headers = this.initCommonsHeaders();
        headers.add(this.i18n.s("keyValueColumnName"));
        headers.add(this.i18n.s("captureTimestampColumnName"));
        return headers;
    }

    @Override
    void updateData(String data) {
        Keystroke keystroke = gson.fromJson(data, Keystroke.class);
        Object[] rowData = new String[]{
                keystroke.getBrowser(),
                keystroke.getPageUrl(),
                keystroke.getPageTitle(),
                keystroke.getKeyValue(),
                keystroke.getCaptureTimestamp()
        };
        this.tableModel.addRow(rowData);
        int rowCount = this.table.getRowCount();
        this.tableModel.fireTableRowsInserted(rowCount, rowCount + 1);
    }


}
