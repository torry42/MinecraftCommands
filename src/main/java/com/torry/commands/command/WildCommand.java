package com.torry.commands.command;

import com.torry.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class WildCommand implements CommandExecutor {

    Plugin plugin = Commands.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            return false;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("commands.wildcommand")) {

                World world = player.getWorld();

                switch (args[0]) {
                    case "bounded":
                        return bounded_cmd(player, world);

                    case "radius":
                        return radius_cmd(player, world, args);
                }
            } else {
                player.sendMessage(ChatColor.RED + "you don't have permission to use this command");
            }

        } else {
            System.out.println("This command should run by a player");
        }

        return true;
    }

    public boolean bounded_cmd(Player player, World world) {

        int bounds = plugin.getConfig().getInt("bounds");
        Location location =  player.getLocation();

        Location newLocation = new Location(world, 0, 0, 0);
        do {
            newLocation = randomLocation(location, bounds, world);
        } while (newLocation == null);

        player.sendMessage("You teleported to X: " + newLocation.getX() + ", Y: " + newLocation.getY() + ", Z: " + newLocation.getX() + ".");
        player.teleport(newLocation);

        return true;
    }

    public boolean radius_cmd(Player player, World world, String[] args) {
        if (args.length < 2) {
            return false;
        }

        int radius = Integer.parseInt(args[1]);
        Location location =  player.getLocation();

        Location newLocation = new Location(world, 0, 0, 0);
        do {
            newLocation = randomLocation(location, radius, world);
        } while (newLocation == null);

        player.sendMessage("You teleported to X: " + newLocation.getX() + ", Y: " + newLocation.getY() + ", Z: " + newLocation.getX() + ".");
        player.teleport(newLocation);

        return true;
    }

    public boolean isBlockSafe(Block block) {
        return !block.isLiquid() && !block.isPassable() && !block.isBlockPowered() && !block.isBlockIndirectlyPowered();
    }

    public Location randomLocation(Location location, int bounds, World world) {

        int x = location.getBlockX();
        int z = location.getBlockZ();

        x = x + (int) (Math.random() * bounds);
        z = z + (int) (Math.random() * bounds);

        int y = world.getHighestBlockYAt(x, z);

        Block newBlock = world.getBlockAt(x, y, z);
        Location newLocation = new Location(world, x, y, z);

        if (isBlockSafe(newBlock)) {
            return newLocation;
        } else {
            return null;
        }
    }

    public int getTopBlock(World world,int x, int z) {
        boolean stillEmpty = true;

        for (int i = 255; i > 0 && stillEmpty; i--) {
            Block block = world.getBlockAt(x, i, z);
            if (block.isEmpty()) {
                continue;
            }
            stillEmpty = false;
            return i;
        }

        return 0;
    }
}
