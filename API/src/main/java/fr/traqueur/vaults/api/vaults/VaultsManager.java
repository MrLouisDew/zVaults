package fr.traqueur.vaults.api.vaults;

import fr.traqueur.vaults.api.exceptions.IndexOutOfBoundVaultException;
import fr.traqueur.vaults.api.managers.Manager;
import fr.traqueur.vaults.api.users.User;

import java.util.Collection;
import java.util.List;

public interface VaultsManager extends Manager {

    String VAULT_TABLE_NAME = "vaults";

    void createVault(User creator, VaultOwner owner, int size);

    boolean sizeIsAvailable(int size);

    OwnerResolver getOwnerResolver();

    List<String> getSizeTabulation();

    VaultOwner generateOwner(String type, User receiver);

    List<String> getNumVaultsTabulation();

    Vault getVault(User receiver, int vaultNum) throws IndexOutOfBoundVaultException;

    List<Vault> getVaults(User user);
}
