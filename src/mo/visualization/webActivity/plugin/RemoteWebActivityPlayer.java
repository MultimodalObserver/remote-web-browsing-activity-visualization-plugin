package mo.visualization.webActivity.plugin;

import mo.communication.streaming.visualization.PlayableStreaming;
import mo.core.ui.dockables.DockableElement;
import mo.core.ui.dockables.DockablesRegistry;
import mo.visualization.webActivity.plugin.view.PlayerPanel;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoteWebActivityPlayer implements PlayableStreaming {

    private PlayerPanel panel;
    private static final Logger LOGGER = Logger.getLogger(RemoteWebActivityPlayer.class.getName());

    RemoteWebActivityPlayer() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                DockableElement e = new DockableElement();
                this.panel = new PlayerPanel();
                e.add(this.panel);
                DockablesRegistry.getInstance().addAppWideDockable(e);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void play() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    PlayerPanel getPanel() {
        return panel;
    }
}
