package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.Keystroke;
import mo.visualization.webActivity.plugin.model.MouseClick;

import java.util.List;

public class MouseClicksPanel extends BasePanel {

    public MouseClicksPanel() {
        super();
        this.tableHeaders = this.getTableHeaders();
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
        MouseClick mouseClick = gson.fromJson(data, MouseClick.class);
        Object[] rowData = new String[]{
                mouseClick.getBrowser(),
                mouseClick.getPageUrl(),
                mouseClick.getPageTitle(),
                String.valueOf(mouseClick.getxPage()),
                String.valueOf(mouseClick.getyPage()),
                String.valueOf(mouseClick.getxClient()),
                String.valueOf(mouseClick.getyClient()),
                String.valueOf(mouseClick.getxScreen()),
                String.valueOf(mouseClick.getyScreen()),
                String.valueOf(mouseClick.getxMovement()),
                String.valueOf(mouseClick.getyMovement()),
                String.valueOf(mouseClick.getCaptureTimestamp())
        };
        this.tableModel.addRow(rowData);
        int rowCount = this.table.getRowCount();
        this.tableModel.fireTableRowsInserted(rowCount, rowCount + 1);
    }
}
