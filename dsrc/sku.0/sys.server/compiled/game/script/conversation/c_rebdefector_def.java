package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class c_rebdefector_def extends script.base_script
{
    public c_rebdefector_def()
    {
    }
    public static String c_stringFile = "conversation/c_rebdefector_def";
    public boolean c_rebdefector_def_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean c_rebdefector_def_condition_isOnQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        int questId1 = questGetQuestId("quest/c_rebdefector");
        int takephrase = groundquests.getTaskId(questId1, "takephrase");
        boolean onQuest = questIsTaskComplete(questId1, takephrase, player);
        return onQuest;
    }
    public boolean c_rebdefector_def_condition_isonStepofQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        int questId1 = questGetQuestId("quest/c_rebdefector");
        int takephrase = groundquests.getTaskId(questId1, "takephrase");
        boolean onQuest = questIsTaskActive(questId1, takephrase, player);
        return onQuest;
    }
    public void c_rebdefector_def_action_forwardQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "c_rebdefector_code");
        obj_id waypoint2 = getObjIdObjVar(player, "quest.general.quest/c_rebdefector.hiding");
        if (waypoint2 != null)
        {
            destroyWaypointInDatapad(waypoint2, player);
        }
        removeObjVar(player, "quest.general.quest/c_rebdefector.hiding");
    }
    public void c_rebdefector_def_action_faceplayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
    }
    public int c_rebdefector_def_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_802"))
        {
            if (c_rebdefector_def_condition__defaultCondition(player, npc))
            {
                c_rebdefector_def_action_forwardQuest(player, npc);
                string_id message = new string_id(c_stringFile, "s_804");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (c_rebdefector_def_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (c_rebdefector_def_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_806");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_810");
                    }
                    utils.setScriptVar(player, "conversation.c_rebdefector_def.branchId", 2);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.c_rebdefector_def.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int c_rebdefector_def_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_806"))
        {
            if (c_rebdefector_def_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_808");
                utils.removeScriptVar(player, "conversation.c_rebdefector_def.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_810"))
        {
            if (c_rebdefector_def_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_812");
                utils.removeScriptVar(player, "conversation.c_rebdefector_def.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.c_rebdefector_def");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setName(self, "Rebel Informant");
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setName(self, "Rebel Informant");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.c_rebdefector_def");
        return SCRIPT_CONTINUE;
    }
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id npc = self;
        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (c_rebdefector_def_condition_isonStepofQuest(player, npc))
        {
            c_rebdefector_def_action_faceplayer(player, npc);
            string_id message = new string_id(c_stringFile, "s_800");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (c_rebdefector_def_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_802");
                }
                utils.setScriptVar(player, "conversation.c_rebdefector_def.branchId", 1);
                npcStartConversation(player, npc, "c_rebdefector_def", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (c_rebdefector_def_condition_isOnQuest(player, npc))
        {
            doAnimationAction(npc, "shoo");
            c_rebdefector_def_action_faceplayer(player, npc);
            string_id message = new string_id(c_stringFile, "s_814");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (c_rebdefector_def_condition__defaultCondition(player, npc))
        {
            c_rebdefector_def_action_faceplayer(player, npc);
            string_id message = new string_id(c_stringFile, "s_816");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("c_rebdefector_def"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.c_rebdefector_def.branchId");
        if (branchId == 1 && c_rebdefector_def_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && c_rebdefector_def_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.c_rebdefector_def.branchId");
        return SCRIPT_CONTINUE;
    }
}
