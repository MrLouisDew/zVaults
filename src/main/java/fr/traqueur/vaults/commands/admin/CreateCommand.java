package fr.traqueur.vaults.commands.admin;

import fr.traqueur.commands.api.Arguments;
import fr.traqueur.vaults.api.VaultsPlugin;
import fr.traqueur.vaults.api.commands.VaultCommand;
import fr.traqueur.vaults.api.config.Configuration;
import fr.traqueur.vaults.api.config.VaultsConfiguration;
import fr.traqueur.vaults.api.messages.Formatter;
import fr.traqueur.vaults.api.messages.Message;
import fr.traqueur.vaults.api.users.User;
import fr.traqueur.vaults.api.users.UserManager;
import fr.traqueur.vaults.api.vaults.VaultOwner;
import fr.traqueur.vaults.api.vaults.VaultsManager;
import fr.traqueur.vaults.users.ZUserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class CreateCommand extends VaultCommand {

    private final UserManager userManager;
    private final VaultsManager vaultsManager;

    public CreateCommand(VaultsPlugin plugin) {
        super(plugin, "create");

        this.userManager = plugin.getManager(UserManager.class);
        this.vaultsManager = plugin.getManager(VaultsManager.class);

        this.setPermission("zvaults.admin.create");
        this.setUsage("/vaults create <receiver> <size> (infinite) <id> [type]");
        this.setDescription(plugin.getMessageResolver().convertToLegacySectionFormat(Message.CREATE_COMMAND_DESCRIPTION.translate()));

        this.addArgs("receiver:user");
        this.addArgs("size:int", (sender, args) -> this.vaultsManager.getSizeTabulation());
        if (Configuration.get(VaultsConfiguration.class).isVaultsInfinity()) {
            this.addArgs("infinite:boolean");
        }
        this.addArgs("id", Long.class);
        this.addOptionalArgs("type:ownerType");
    }

    @Override
    public void execute(CommandSender commandSender, Arguments arguments) {
        User user;
        if(!(commandSender instanceof Player)) {
            user = ZUserManager.CONSOLE_USER;
        } else {
            user = this.userManager.getUser(((Player) commandSender).getUniqueId()).orElseThrow();
        }
        User receiver = arguments.get("receiver");
        int size = arguments.get("size");
        Optional<String> opt = arguments.getOptional("type");
        String type = opt.orElse("player");
        long vaultId = arguments.get("id");
        boolean infinite = false;

        if (Configuration.get(VaultsConfiguration.class).isVaultsInfinity()) {
            infinite = arguments.get("infinite");
        }

        if (!this.vaultsManager.sizeIsAvailable(size)) {
            user.sendMessage(Message.SIZE_NOT_AVAILABLE, Formatter.format("%size%", size));
            return;
        }
        VaultOwner owner = this.vaultsManager.generateOwner(type, receiver);

        if(this.vaultsManager.idExists(owner, vaultId)) {
            user.sendMessage(Message.VAULT_ID_ALREADY_EXISTS, Formatter.format("%id%", vaultId));
            return;
        }
        int maxVaults = Configuration.get(VaultsConfiguration.class).getMaxVaultsByOwnerType(type.toLowerCase());
        this.vaultsManager.createVault(user, owner, size, maxVaults, infinite, false, vaultId);
    }
}
