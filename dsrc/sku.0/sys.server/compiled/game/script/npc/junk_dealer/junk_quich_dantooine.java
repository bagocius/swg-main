package script.npc.junk_dealer;

import script.library.ai_lib;
import script.obj_id;

public class junk_quich_dantooine extends script.base_script
{
    public junk_quich_dantooine()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        detachScript(self, "conversation.junk_quich_dantooine");
        attachScript(self, "npc.converse.junk_dealer");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        detachScript(self, "conversation.junk_quich_dantooine");
        attachScript(self, "npc.converse.junk_dealer");
        return SCRIPT_CONTINUE;
    }
}
