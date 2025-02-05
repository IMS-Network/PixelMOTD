package me.blueslime.pixelmotd.extras.listeners.velocity.abstracts;

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.pixelmotd.PixelMOTD;
import me.blueslime.pixelmotd.extras.listeners.ConnectionListener;
import me.blueslime.pixelmotd.utils.ListUtil;
import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.UUID;

public class AbstractLoginListener  extends ConnectionListener<ProxyServer, LoginEvent, Component> {

    public AbstractLoginListener(PixelMOTD<ProxyServer> plugin) {
        super(plugin);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    @Subscribe
    public void execute(LoginEvent event) {
        if (event == null) {
            return;
        }

        final Player connection = event.getPlayer();

        if (connection == null) {
            return;
        }

        if (connection.getUniqueId() == null) {

            getLogs().info("The plugin will not apply the whitelist or blacklist check for a null-ip and will block player from joining to the server");



            event.setResult(
                    result("&cBlocking you from joining to the server, reason: &6Null IP&c, if this is a error, contact to the server staff")
            );
            return;

        }

        final InetSocketAddress socketAddress = connection.getRemoteAddress();

        if (socketAddress != null) {
            InetAddress address = socketAddress.getAddress();

            getPlayerDatabase().add(
                    address.getHostAddress(),
                    connection.getUsername()
            );
        }
    }

    private ResultedEvent.ComponentResult result(String message) {
        return ResultedEvent.ComponentResult.denied(
                colorize(message)
        );
    }

    @Override
    public Component colorize(String message) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }
}