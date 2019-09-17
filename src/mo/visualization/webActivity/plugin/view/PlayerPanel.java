package mo.visualization.webActivity.plugin.view;

import com.google.gson.Gson;
import mo.core.I18n;
import mo.visualization.webActivity.plugin.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerPanel extends JTabbedPane{

    private Map<String, BasePanel> panelsMap;


    /* Los tipos de datos que vienen en el archivo de mapeo a los archivos reales,
    tienen el mismo nombre que las rutas definidas en el plugin de captura, sin considerar el / al inicio!!
     */
    private static final String KEYSTROKES_DATA_TYPE = "keystrokes";
    private static final String MOUSE_MOVES_DATA_TYPE = "mouseMoves";
    private static final String MOUSE_CLICKS_DATA_TYPE = "mouseClicks";
    private static final String MOUSE_UPS_DATA_TYPE = "mouseUps";
    private static final String SEARCHS_DATA_TYPE = "searchs";
    private static final String TABS_DATA_TYPE = "tabs";
    private static final String[] DATA_TYPES= new String[]{KEYSTROKES_DATA_TYPE,MOUSE_MOVES_DATA_TYPE,MOUSE_CLICKS_DATA_TYPE
            ,MOUSE_UPS_DATA_TYPE,SEARCHS_DATA_TYPE,TABS_DATA_TYPE};

    private final Gson gson;


    /* Cuando creamos el panel, definimos los tipos de datos que vamos a mostrar, seteando una lista de subvistas
     * donde cada una corresponde a un tipo de dato en específico, y las agregamos al panel principal del player.
     *
     *
     * Mostraremos cada tipo de dato en una tab correspondiente
     * */
    public PlayerPanel(){
        I18n i18n = new I18n(PlayerPanel.class);
        this.panelsMap = this.createPanelsMap();
        this.gson = new Gson();
        for(String dataType : DATA_TYPES){
            this.addTab(i18n.s(dataType + "PanelName"), this.panelsMap.get(dataType));
        }
        this.setVisible(true);
    }

    private Map<String, BasePanel> createPanelsMap(){
        Map<String, BasePanel> panelsMap = new HashMap<>();
        for(String dataType : PlayerPanel.DATA_TYPES){
            BasePanel panel;
            switch(dataType){
                case KEYSTROKES_DATA_TYPE:
                    panel = new KeystrokesPanel();
                    break;
                case MOUSE_MOVES_DATA_TYPE:
                    panel = new MouseMovesPanel();
                    break;
                case MOUSE_CLICKS_DATA_TYPE:
                    panel = new MouseClicksPanel();
                    break;
                case MOUSE_UPS_DATA_TYPE:
                    panel = new MouseUpsPanel();
                    break;
                case SEARCHS_DATA_TYPE:
                    panel = new SearchsPanel();
                    break;
                case TABS_DATA_TYPE:
                    panel = new TabsPanel();
                    break;
                default:
                    panel = null;
                    break;
            }
            panelsMap.put(dataType, panel);
        }
        return panelsMap;
    }

    public void updatePanelData(String data){
        DataMessage dataMessage = this.gson.fromJson(data, DataMessage.class);
        BasePanel panel = this.panelsMap.get(dataMessage.getDataType());
        String dataMessageData = dataMessage.getData();
        panel.updateData(dataMessageData);
    }

}
