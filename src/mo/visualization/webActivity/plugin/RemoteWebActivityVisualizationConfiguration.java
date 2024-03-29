package mo.visualization.webActivity.plugin;

import mo.communication.ClientConnection;
import mo.communication.ConnectionListener;
import mo.communication.PetitionResponse;
import mo.communication.streaming.visualization.PlayableStreaming;
import mo.communication.streaming.visualization.VisualizableStreamingConfiguration;
import mo.organization.Configuration;
import mo.visualization.webActivity.plugin.model.VisualizationConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class RemoteWebActivityVisualizationConfiguration implements VisualizableStreamingConfiguration, ConnectionListener {

    private static final String[] CREATORS = new String[] {"mo.capture.webActivity.plugin.WebBrowsingActivityRecorder"};
    private VisualizationConfiguration temporalConfig;
    private RemoteWebActivityPlayer player;
    private static final String CONTENT_MESSAGE_KEY = "data";
    private static final String PLUGIN_MESSAGE_KEY = "webActivity";

    RemoteWebActivityVisualizationConfiguration(VisualizationConfiguration temporalConfig) {
        this.temporalConfig = temporalConfig;
        this.player = new RemoteWebActivityPlayer();
    }

    @Override
    public void onMessageReceived(Object o, PetitionResponse petitionResponse) {
        if(petitionResponse.getType().equals(PLUGIN_MESSAGE_KEY)){
            if(petitionResponse.getHashMap().containsKey(CONTENT_MESSAGE_KEY)){
                String jsonData = petitionResponse.getHashMap().get(CONTENT_MESSAGE_KEY).toString();
                this.player.getPanel().updatePanelData(jsonData);
            }
            /*else if(petitionResponse.getHashMap().containsKey("stopCapture")){
                String stopType = petitionResponse.getHashMap().get("stopCapture").toString();
                if(stopType.equals("logic")){
                    this.player.getPanel().clearPanelsTables();
                }
            }*/
        }
    }

    @Override
    public void subscribeToConnection(ClientConnection clientConnection) {
        clientConnection.subscribeListener(this);
    }

    @Override
    public List<String> getCompatibleCreators() {
        return Arrays.asList(CREATORS);
    }

    @Override
    public PlayableStreaming getPlayer() {
        return this.player;
    }

    @Override
    public String getId() {
        return this.temporalConfig.getName();
    }

    @Override
    public File toFile(File file) {
        return null;
    }

    @Override
    public Configuration fromFile(File file) {
        return null;
    }
}
