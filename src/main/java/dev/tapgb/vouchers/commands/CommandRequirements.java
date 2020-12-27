package dev.tapgb.vouchers.commands;

public class CommandRequirements {

    private CommandRequirement[] requirements;

    public CommandRequirement[] getRequirements(){
        return requirements;
    }

    public CommandRequirements(CommandRequirement... requirements){
        this.requirements = requirements;
    }

}
