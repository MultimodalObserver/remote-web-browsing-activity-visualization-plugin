package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.Keystroke;

import java.util.List;

class KeystrokesPanel extends BasePanel {

    KeystrokesPanel() {
        super();
        this.tableHeaders = this.getTableHeaders();
        this.addHeaders();
        this.columnWidths = new float[]{0.1f, 0.5f, 0.1f, 0.1f, 0.2f};
        this.resizeColumns();
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
        Object[] rowData = new Object[]{
                keystroke.getBrowser(),
                keystroke.getPageUrl(),
                keystroke.getPageTitle(),
                keystroke.getKeyValue(),
                keystroke.getCaptureTimestamp()
        };
        this.tableModel.addRow(rowData);
    }


}
