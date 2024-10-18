package fr.traqueur.vaults.vaults;

import fr.traqueur.vaults.api.vaults.Vault;
import fr.traqueur.vaults.api.vaults.VaultItem;
import fr.traqueur.vaults.api.vaults.VaultOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ZVault implements Vault {

    private final UUID uniqueId;
    private final VaultOwner owner;
    private final List<VaultItem> content;
    private final boolean infinite;
    private int size;

    public ZVault(VaultOwner owner, int size, boolean infinite) {
        this(UUID.randomUUID(), owner, new ArrayList<>(), size, infinite);
    }

    public ZVault(UUID uniqueId, VaultOwner owner, List<VaultItem> content, int size, boolean infinite) {
        this.uniqueId = uniqueId;
        this.owner = owner;
        this.content = content;
        this.size = size;
        this.infinite = infinite;
    }

    @Override
    public UUID getUniqueId() {
        return this.uniqueId;
    }

    @Override
    public VaultOwner getOwner() {
        return this.owner;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public List<VaultItem> getContent() {
        return this.content;
    }

    @Override
    public void setContent(List<VaultItem> content) {
        this.content.clear();
        this.content.addAll(content);
    }

    @Override
    public boolean isInfinite() {
        return this.infinite;
    }
}
