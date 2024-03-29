package mo.visualization.webActivity.plugin;

import mo.communication.streaming.capture.CaptureConfig;
import mo.communication.streaming.visualization.VisualizationStreamingProvider;
import mo.core.I18n;
import mo.core.plugin.Extends;
import mo.core.plugin.Extension;
import mo.organization.Configuration;
import mo.visualization.webActivity.plugin.model.VisualizationConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Extension(
        xtends = {
                @Extends(
                        extensionPointId = "mo.communication.streaming.visualization.VisualizationStreamingProvider"
                )
        }
)
public class RemoteWebActivityVisualizationPlugin implements VisualizationStreamingProvider{

    private static final String[] CREATORS = new String[] {"mo.capture.webActivity.plugin.WebBrowsingActivityRecorder"};
    private List<Configuration> configurations;
    private I18n i18n;

    public RemoteWebActivityVisualizationPlugin() {
        this.configurations = new ArrayList<>();
        this.i18n = new I18n(RemoteWebActivityVisualizationPlugin.class);
    }

    @Override
    public String getName() {
        return this.i18n.s("pluginDisplayedName");
    }

    @Override
    public List<String> getCompatibleCreators() {
        return Arrays.asList(CREATORS);
    }

    @Override
    public Configuration initNewStreamingConfiguration(CaptureConfig captureConfig) {
        VisualizationConfiguration temporalConfig = new VisualizationConfiguration(captureConfig.getConfigID());
        RemoteWebActivityVisualizationConfiguration configuration = new RemoteWebActivityVisualizationConfiguration(temporalConfig);
        this.configurations.add(configuration);
        return configuration;
    }

    @Override
    public List<Configuration> getConfigurations() {
        return this.configurations;
    }
}
