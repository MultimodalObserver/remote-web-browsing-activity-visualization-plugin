package mo.visualization.webActivity.plugin.view;

import com.google.gson.Gson;
import mo.core.I18n;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract class BasePanel extends JPanel {
    DefaultTableModel tableModel;
    private JTable table;
    I18n i18n;
    List<String> tableHeaders;
    final Gson gson;
    float[] columnWidths;


    BasePanel(){
        this.i18n = new I18n(BasePanel.class);
        this.gson = new Gson();
        this.setLayout(new GridBagLayout());
        this.initComponents();
    }

    abstract List<String> getTableHeaders();
    abstract void updateData(String data);

    private void initComponents(){
        /* Iniciamos la tabla y el modelo*/
        this.tableModel = new DefaultTableModel();
        this.table = new JTable(this.tableModel);
        this.table.setFillsViewportHeight(true);
        this.table.setCellSelectionEnabled(true);
        this.table.tableChanged(null);
        JScrollPane scrollPane = new JScrollPane(this.table);
        //this.noDataLabel = new JLabel(this.i18n.s("noDataLabelText"));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1.0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,10);
        this.add(scrollPane, constraints);
        this.setVisible(true);
    }

    List<String> initCommonsHeaders(){
        List<String> headers = new ArrayList<>();
        headers.add(this.i18n.s("browserColumnName"));
        headers.add(this.i18n.s("pageUrlColumnName"));
        headers.add(this.i18n.s("pageTitleColumnName"));
        return headers;
    }

    void addMousePositionHeaders(List<String> headers, boolean withMovement){
        headers.add(this.i18n.s("xPageColumnName"));
        headers.add(this.i18n.s("yPageColumnName"));
        headers.add(this.i18n.s("xClientColumnName"));
        headers.add(this.i18n.s("yClientColumnName"));
        headers.add(this.i18n.s("xScreenColumnName"));
        headers.add(this.i18n.s("yScreenColumnName"));
        if(withMovement){
            headers.add(this.i18n.s("xMovementColumnName"));
            headers.add(this.i18n.s("yMovementColumnName"));
        }
    }

    void addHeaders(){
        for(String header: this.tableHeaders){
            this.tableModel.addColumn(header);
        }
    }

    void resizeColumns() {
        TableColumn column;
        TableColumnModel jTableColumnModel = this.table.getColumnModel();
        int tableWidth = jTableColumnModel.getTotalColumnWidth();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int columnWidth = Math.round(this.columnWidths[i] * tableWidth);
            column.setPreferredWidth(columnWidth);
        }
    }

    void clearTable(){
        this.tableModel.setRowCount(0);
    }
}
