package script.systems.crafting.space.shields;

import script.dictionary;
import script.draft_schematic;
import script.library.craftinglib;
import script.library.space_crafting;
import script.obj_id;

public class crafting_base_shields extends script.systems.crafting.crafting_base
{
    public crafting_base_shields()
    {
    }
    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes, dictionary craftingValuesDictionary) throws InterruptedException
    {
        for (draft_schematic.attribute itemAttribute : itemAttributes) {
            if (itemAttribute == null) {
                continue;
            }
            if (((itemAttribute.name).getAsciiId()).equals("mass") || ((itemAttribute.name).getAsciiId()).equals("energy_maintenance")) {
                itemAttribute.currentValue = (itemAttribute.minValue + itemAttribute.maxValue) - itemAttribute.currentValue;
            }
            if (((itemAttribute.name).getAsciiId()).equals("shield_recharge")) {
                itemAttribute.currentValue = (itemAttribute.currentValue * 0.001f);
            }
        }
        super.calcAndSetPrototypeProperties(prototype, itemAttributes, craftingValuesDictionary);
    }
    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes) throws InterruptedException
    {
        debugServerConsoleMsg(null, "Beginning assembly-phase prototype property setting");
        for (draft_schematic.attribute itemAttribute : itemAttributes) {
            if (itemAttribute == null) {
                continue;
            }
            if (!calcAndSetPrototypeProperty(prototype, itemAttribute)) {
                if (((itemAttribute.name).getAsciiId()).equals("hitPointsMax")) {
                    space_crafting.setComponentMaximumHitpoints(prototype, (float) itemAttribute.currentValue);
                    space_crafting.setComponentCurrentHitpoints(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("efficiency")) {
                    space_crafting.setComponentGeneralEfficiency(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("mass")) {
                    space_crafting.setComponentMass(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("armorHpMax")) {
                    space_crafting.setComponentMaximumArmorHitpoints(prototype, (float) itemAttribute.currentValue);
                    space_crafting.setComponentCurrentArmorHitpoints(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("energy_efficiency")) {
                    space_crafting.setComponentEnergyEfficiency(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("energy_maintenance")) {
                    space_crafting.setComponentEnergyMaintenance(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("shield_max_front_hp")) {
                    space_crafting.setShieldGeneratorMaximumFrontHitpoints(prototype, (float) itemAttribute.currentValue);
                    space_crafting.setShieldGeneratorCurrentFrontHitpoints(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("shield_max_back_hp")) {
                    space_crafting.setShieldGeneratorMaximumBackHitpoints(prototype, (float) itemAttribute.currentValue);
                    space_crafting.setShieldGeneratorCurrentBackHitpoints(prototype, (float) itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("shield_recharge")) {
                    space_crafting.setShieldGeneratorRechargeRate(prototype, (float) itemAttribute.currentValue);
                } else {
                    setObjVar(prototype, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + (itemAttribute.name).getAsciiId(), itemAttribute.currentValue);
                }
            }
        }
    }
}
