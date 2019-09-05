package mo.visualization.webActivity.plugin.view;

import com.google.gson.Gson;
import mo.core.I18n;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract class BasePanel extends JPanel {
    /*protected JLabel noDataLabel;*/
    DefaultTableModel tableModel;
    I18n i18n;
    List<String> tableHeaders;
    static final Gson gson = new Gson();


    BasePanel(){
        this.i18n = new I18n(BasePanel.class);
        this.initComponents();
        this.setLayout(new GridBagLayout());
    }

    abstract List<String> getTableHeaders();
    abstract void updateData(String data);



    void showPanel(){
        this.setVisible(true);
    }

    private void initComponents(){
        /* Iniciamos la tabla y el modelo*/
        this.tableModel = new DefaultTableModel();
        JTable table = new JTable(this.tableModel);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(true);
        table.setShowHorizontalLines(false);
        table.tableChanged(null);

        /* Iniciamos el scroll pane que tendra la tabla */
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);

        //this.noDataLabel = new JLabel(this.i18n.s("noDataLabelText"));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 0;
        constraints.gridwidth = 0;
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

    void addPositionHeaders(List<String> headers){
        headers.add(this.i18n.s("xPageColumnName"));
        headers.add(this.i18n.s("yPageColumnName"));
        headers.add(this.i18n.s("xClientColumnName"));
        headers.add(this.i18n.s("yClientColumnName"));
        headers.add(this.i18n.s("xScreenColumnName"));
        headers.add(this.i18n.s("yScreenColumnName"));
        headers.add(this.i18n.s("xMovementColumnName"));
        headers.add(this.i18n.s("yMovementColumnName"));
    }

    void addHeaders(){
        for(String header: this.tableHeaders){
            this.tableModel.addColumn(header);
        }
    }
}
