package fr.traqueur.vaults.vaults;

import fr.traqueur.vaults.api.config.NonLoadable;
import fr.traqueur.vaults.api.config.VaultsConfiguration;
import fr.traqueur.vaults.api.vaults.SizeMode;
import fr.traqueur.vaults.api.gui.VaultIcon;

import java.util.List;

public class ZVaultsConfiguration implements VaultsConfiguration {

    @NonLoadable
    private boolean load;

    private int defaultSize;
    private int maxVaultsPerPlayer;
    private boolean infiniteVaults;
    private SizeMode sizeMode;
    private List<VaultIcon> vaultsIcons;

    public ZVaultsConfiguration() {
        this.load = false;
    }

    @Override
    public String getFile() {
        return "vaults.yml";
    }

    @Override
    public void loadConfig() {
        this.load = true;
    }

    @Override
    public boolean isLoad() {
        return load;
    }

    @Override
    public SizeMode getSizeMode() {
        return sizeMode;
    }

    @Override
    public int getDefaultSize() {
        return defaultSize;
    }

    @Override
    public int getMaxVaultsByPlayer() {
        return maxVaultsPerPlayer;
    }

    @Override
    public boolean isVaultsInfinity() {
        return infiniteVaults;
    }

    @Override
    public List<VaultIcon> getIcons() {
        return vaultsIcons;
    }

    @Override
    public VaultIcon getIcon(String id) {
        return this.vaultsIcons.stream().filter(icon -> icon.id().equals(id)).findFirst().orElseThrow();
    }
}
