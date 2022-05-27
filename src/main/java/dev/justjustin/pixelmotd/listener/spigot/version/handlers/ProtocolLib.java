package dev.justjustin.pixelmotd.listener.spigot.version.handlers;

import com.comphenix.protocol.ProtocolLibrary;
import dev.justjustin.pixelmotd.listener.spigot.version.PlayerVersionHandler;
import org.bukkit.entity.Player;

public class ProtocolLib implements PlayerVersionHandler {
    @Override
    public int getProtocol(Player player) {
        return ProtocolLibrary.getProtocolManager().getProtocolVersion(player);
    }
}
