package com.example.itk;

public class PersonalStringBuilder {
    private final VaultSnapshots vaultSnapshots = new VaultSnapshots();
    private StringBuilder builder;

    public PersonalStringBuilder(String str) {
        builder = new StringBuilder(str);
        vaultSnapshots.saveState(createSnapshot());
    }

    public PersonalStringBuilder append(String str) {
        builder.append(str);
        vaultSnapshots.saveState(createSnapshot());
        return this;
    }

    public void undo() {
        var snapshot = vaultSnapshots.getPreviousState();
        this.builder = new StringBuilder(snapshot.string());
        vaultSnapshots.saveState(createSnapshot());
    }

    private PersonalStringBuilderSnapshot createSnapshot() {
        return new PersonalStringBuilderSnapshot(builder.toString());
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}