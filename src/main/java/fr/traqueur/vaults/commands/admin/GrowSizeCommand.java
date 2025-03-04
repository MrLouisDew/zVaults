package fr.traqueur.vaults.commands.admin;

import fr.traqueur.commands.api.Arguments;
import fr.traqueur.vaults.api.VaultsPlugin;
import fr.traqueur.vaults.api.commands.VaultCommand;
import fr.traqueur.vaults.api.exceptions.IndexOutOfBoundVaultException;
import fr.traqueur.vaults.api.messages.Message;
import fr.traqueur.vaults.api.users.User;
import fr.traqueur.vaults.api.users.UserManager;
import fr.traqueur.vaults.api.vaults.Vault;
import fr.traqueur.vaults.api.vaults.VaultsManager;
import fr.traqueur.vaults.users.ZUserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GrowSizeCommand extends VaultCommand {

    private final UserManager userManager;
    private final VaultsManager vaultsManager;

    // /zvaults growsize <player> <vault_num> <size>
    public GrowSizeCommand(VaultsPlugin plugin) {
        super(plugin, "growsize");

        this.userManager = plugin.getManager(UserManager.class);
        this.vaultsManager = plugin.getManager(VaultsManager.class);

        this.setPermission("zvaults.admin.growsize");
        this.setUsage("/zvaults growsize <player> <vault_num> <size>");
        this.setDescription(plugin.getMessageResolver().convertToLegacySectionFormat(Message.GROW_SIZE_COMMAND_DESCRIPTION.translate()));

        this.addArgs("receiver:user");
        this.addArgs("vault_num:int", (sender, args) -> this.vaultsManager.getNumVaultsTabulation());
        this.addOptionalArgs("size:int", (sender, args) -> List.of("9", "18", "27"));
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
        int vaultNum = arguments.get("vault_num");
        Vault vault;
        try {
            vault = this.vaultsManager.getVault(receiver, vaultNum);
        } catch (IndexOutOfBoundVaultException e) {
            user.sendMessage(Message.VAULT_NOT_FOUND);
            return;
        }
        this.vaultsManager.changeSizeOfVault(user, vault, vault.getSize()+size, Message.VAULT_GROWED_SUCCESS, Message.VAULT_GROWED);
    }
}
