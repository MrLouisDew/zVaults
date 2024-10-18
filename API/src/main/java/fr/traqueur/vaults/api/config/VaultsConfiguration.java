package fr.traqueur.vaults.api.config;

import fr.traqueur.vaults.api.gui.VaultIcon;
import fr.traqueur.vaults.api.vaults.SizeMode;

import java.util.List;

public interface VaultsConfiguration extends Configuration {

    SizeMode getSizeMode();

    int getDefaultSize();

    int getMaxVaultsByPlayer();

    boolean isVaultsInfinity();

    List<VaultIcon> getIcons();

    VaultIcon getIcon(String id);
}
