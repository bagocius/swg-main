package script.systems.fs_quest.fs_quests_sad;

import script.dictionary;
import script.library.quests;
import script.location;
import script.obj_id;

public class theater_spawn5 extends script.base_script
{
    public theater_spawn5()
    {
    }
    public int OnTheaterCreated(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("newquests", "theater_spawn1");
        obj_id player = params.getObjId("player");
        LOG("newquests", "theater_spawn1 player = " + player);
        location mylocation = getLocation(self);
        quests.createSpawner("fs_quest_sad5_1", mylocation, "datatables/quest/force_sensitive/base_spawn.iff", player);
        return SCRIPT_CONTINUE;
    }
}
