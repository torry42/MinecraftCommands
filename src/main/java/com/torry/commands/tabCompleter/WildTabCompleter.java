package com.torry.commands.tabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WildTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            List<String> recommandedArgs = new ArrayList<>();
            recommandedArgs.add("bounded");
            recommandedArgs.add("radius");

            List<String> resultArgs = new ArrayList<>();

            if (args.length == 0) {
                return recommandedArgs;
            } else if (args.length == 1) {
                for (String s: recommandedArgs) {
                    if (s.startsWith(args[0])) {
                        resultArgs.add(s);
                    }
                }
            }

            return resultArgs;
        }

        return null;
    }
}
