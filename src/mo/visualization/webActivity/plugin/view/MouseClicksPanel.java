package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.MouseClick;

import java.util.List;

class MouseClicksPanel extends BasePanel {

    MouseClicksPanel() {
        super();
        this.tableHeaders = this.getTableHeaders();
        this.addHeaders();
        this.columnWidths = new float[]{0.083f, 0.1245f, 0.083f, 0.083f, 0.083f, 0.083f,
                0.083f, 0.083f,0.083f, 0.14525f, 0.06225f};
        this.resizeColumns();
    }

    @Override
    List<String> getTableHeaders() {
        List<String> headers = this.initCommonsHeaders();
        this.addMousePositionHeaders(headers, false);
        headers.add(this.i18n.s("mouseClickButtonColumnName"));
        headers.add(this.i18n.s("captureTimestampColumnName"));
        return headers;
    }

    @Override
    void updateData(String data) {
        MouseClick mouseClick = gson.fromJson(data, MouseClick.class);
        Object[] rowData = new Object[]{
                mouseClick.getBrowser(),
                mouseClick.getPageUrl(),
                mouseClick.getPageTitle(),
                mouseClick.getxPage(),
                mouseClick.getyPage(),
                mouseClick.getxClient(),
                mouseClick.getyClient(),
                mouseClick.getxScreen(),
                mouseClick.getyScreen(),
                this.getButtonAsString(mouseClick.getButton()),
                mouseClick.getCaptureTimestamp()
        };
        this.tableModel.addRow(rowData);
    }

    private String getButtonAsString(Integer which){
        String value = "";
        if(which == 0){
            value = this.i18n.s("leftMouseClickValue");
        }
        else if(which == 1){
            value = this.i18n.s("centerMouseClickValue");
        }
        else if(which == 2){
            value = this.i18n.s("rightMouseClickValue");
        }
        return value;
    }
}
