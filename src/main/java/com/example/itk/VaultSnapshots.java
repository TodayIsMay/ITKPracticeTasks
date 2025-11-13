package com.example.itk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaultSnapshots {
    private int version = 0;
    private final Map<Integer, PersonalStringBuilderSnapshot> snapshots = new HashMap<>();
    private final List<PersonalStringBuilderSnapshot> snapshotList = new ArrayList<>();

    public void saveState(PersonalStringBuilderSnapshot snapshot) {
        version++;
        snapshots.put(version, snapshot);
        snapshotList.add(snapshot);
    }

    public PersonalStringBuilderSnapshot getPreviousState() {
        var snapshot = snapshotList.get(snapshotList.size() - 2);
        snapshots.remove(snapshotList.size() - 1);
        return snapshot;
    }
}
