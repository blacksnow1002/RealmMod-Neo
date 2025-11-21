package com.blacksnow1002.realmmod.system.player.attachment;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public class PlayerStateData implements ValueIOSerializable {

    private Boolean meditationState = false;

    public Boolean getMeditationState() {
        return meditationState;
    }

    public void setMeditationState(Boolean state) {
        this.meditationState = state;
    }

    @Override
    public void serialize(ValueOutput output) {

    }

    @Override
    public void deserialize(ValueInput input) {

    }
}
