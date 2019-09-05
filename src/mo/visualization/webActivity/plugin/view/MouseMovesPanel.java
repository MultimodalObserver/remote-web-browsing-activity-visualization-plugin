package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.MouseMove;

import java.util.List;

class MouseMovesPanel extends BasePanel {

    MouseMovesPanel() {
        super();
        this.tableHeaders = this.getTableHeaders();
        this.addHeaders();
    }

    @Override
    List<String> getTableHeaders() {
        List<String> headers = this.initCommonsHeaders();
        this.addPositionHeaders(headers);
        headers.add(this.i18n.s("captureTimestampColumnName"));
        return headers;
    }

    @Override
    void updateData(String data) {
        MouseMove mouseMove = gson.fromJson(data, MouseMove.class);
        Object[] rowData = new String[]{
                mouseMove.getBrowser(),
                mouseMove.getPageUrl(),
                mouseMove.getPageTitle(),
                String.valueOf(mouseMove.getxPage()),
                String.valueOf(mouseMove.getyPage()),
                String.valueOf(mouseMove.getxClient()),
                String.valueOf(mouseMove.getyClient()),
                String.valueOf(mouseMove.getxScreen()),
                String.valueOf(mouseMove.getyScreen()),
                String.valueOf(mouseMove.getxMovement()),
                String.valueOf(mouseMove.getyMovement()),
                String.valueOf(mouseMove.getCaptureTimestamp())
        };
        this.tableModel.addRow(rowData);
        /*int rowCount = this.table.getRowCount();
        this.tableModel.fireTableRowsInserted(rowCount, rowCount + 1);*/
    }

}
