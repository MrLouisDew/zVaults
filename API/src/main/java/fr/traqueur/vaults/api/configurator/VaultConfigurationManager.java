package fr.traqueur.vaults.api.configurator;

import fr.traqueur.vaults.api.managers.Manager;
import fr.traqueur.vaults.api.users.User;
import fr.traqueur.vaults.api.vaults.Vault;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface VaultConfigurationManager extends Manager {

    String SHARED_ACCESS_TABLE = "vaults_shared_access";

    void openVaultConfig(User user, Vault vault);

    void closeVaultConfig(User user);

    boolean hasAccess(Vault vault, User user);

    Vault getOpenedConfig(User user);

    void openInvitationMenu(User user, Vault vault);

    void openAccessManagerMenu(User user, Vault vault);

    void closeAccessManagerMenu(User user);

    Vault getOpenedAccessManager(User user);

    List<User> getWhoCanAccess(Vault vault);

    void removeAccess(User user, Vault vault, User value);

    void deleteSharedAccess(UUID uniqueId);

    void delete(Vault vault);

    void addSharedAccess(UUID uniqueId, User user, Vault vault);

    void openNameModifier(User user, Vault vault);
}
