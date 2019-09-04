package mo.visualization.webActivity.plugin.view;

import mo.visualization.webActivity.plugin.model.MouseMove;
import mo.visualization.webActivity.plugin.model.MouseUp;

import java.util.List;

public class MouseUpsPanel extends BasePanel {

    public MouseUpsPanel() {
        super();
        this.tableHeaders = this.getTableHeaders();
    }

    @Override
    List<String> getTableHeaders() {
        List<String> headers = this.initCommonsHeaders();
        headers.add(this.i18n.s("selectedTextColumnName"));
        headers.add(this.i18n.s("captureTimestampColumnName"));
        return headers;
    }

    @Override
    void updateData(String data) {
        MouseUp mouseUp = gson.fromJson(data, MouseUp.class);
        Object[] rowData = new String[]{
                mouseUp.getBrowser(),
                mouseUp.getPageUrl(),
                mouseUp.getPageTitle(),
                mouseUp.getSelectedText(),
                /* VER EL TEMA DE PASAR EL LONG DE CAPTURA A TIMESTAMO ENTENDIBLE PARA USUARIO*/
                String.valueOf(mouseUp.getCaptureTimestamp())
        };
        this.tableModel.addRow(rowData);
        int rowCount = this.table.getRowCount();
        this.tableModel.fireTableRowsInserted(rowCount, rowCount + 1);
    }
}
