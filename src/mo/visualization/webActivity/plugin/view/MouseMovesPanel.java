package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.MouseMove;

import java.util.List;

class MouseMovesPanel extends BasePanel {

    MouseMovesPanel() {
        super();
        this.tableHeaders = this.getTableHeaders();
        this.addHeaders();
        this.columnWidths = new float[]{0.0415f, 0.1245f, 0.083f, 0.083f, 0.083f, 0.083f, 0.083f,
                0.083f, 0.083f,0.083f, 0.0415f, 0.1245f};
        this.resizeColumns();
    }

    @Override
    List<String> getTableHeaders() {
        List<String> headers = this.initCommonsHeaders();
        this.addMousePositionHeaders(headers, true);
        headers.add(this.i18n.s("captureTimestampColumnName"));
        return headers;
    }

    @Override
    void updateData(String data) {
        MouseMove mouseMove = gson.fromJson(data, MouseMove.class);
        Object[] rowData = new Object[]{
                mouseMove.getBrowser(),
                mouseMove.getPageUrl(),
                mouseMove.getPageTitle(),
                mouseMove.getxPage(),
                mouseMove.getyPage(),
                mouseMove.getxClient(),
                mouseMove.getyClient(),
                mouseMove.getxScreen(),
                mouseMove.getyScreen(),
                mouseMove.getxMovement(),
                mouseMove.getyMovement(),
                mouseMove.getCaptureTimestamp()
        };
        this.tableModel.addRow(rowData);
    }

}
