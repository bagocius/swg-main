package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class ep3_etyyy_iluna_mystuk extends script.base_script
{
    public ep3_etyyy_iluna_mystuk()
    {
    }
    public static String c_stringFile = "conversation/ep3_etyyy_iluna_mystuk";
    public boolean ep3_etyyy_iluna_mystuk_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean ep3_etyyy_iluna_mystuk_condition_killedGreyclimberPlusAll(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedQuest(player, "ep3_hunt_loot_paleclaw_killed") && groundquests.hasCompletedQuest(player, "ep3_hunt_loot_silkthrower_killed") && groundquests.hasCompletedQuest(player, "ep3_hunt_loot_stoneleg_killed") && groundquests.hasCompletedQuest(player, "ep3_hunt_loot_spiketop_killed") && groundquests.hasCompletedQuest(player, "ep3_hunt_loot_brightclaw_killed");
    }
    public boolean ep3_etyyy_iluna_mystuk_condition_killedGreyclimber(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "ep3_hunt_loot_greyclimber_killed");
    }
    public boolean ep3_etyyy_iluna_mystuk_condition_doesNotHaveBrodyQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, "ep3_hunt_chrilooc_seek_johnson") || groundquests.hasCompletedQuest(player, "ep3_hunt_chrilooc_seek_johnson"))
        {
            if (!groundquests.isQuestActive(player, "ep3_hunt_johnson_brody_johnson") && !groundquests.hasCompletedQuest(player, "ep3_hunt_johnson_brody_johnson"))
            {
                return true;
            }
        }
        return false;
    }
    public void ep3_etyyy_iluna_mystuk_action_greyclimberReward(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "lootQuest_defeatedGreyclimber");
    }
    public void ep3_etyyy_iluna_mystuk_action_greyclimberRewardPlusAll(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "lootQuest_defeatedGreyclimber");
        groundquests.grantQuest(player, "ep3_hunt_loot_completed_all");
    }
    public void ep3_etyyy_iluna_mystuk_action_sendToArconaCamp(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "ep3_hunt_iluna_goto_arcona_camp");
    }
    public int ep3_etyyy_iluna_mystuk_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_1775"))
        {
            if (ep3_etyyy_iluna_mystuk_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_1777");
                utils.removeScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_1779"))
        {
            if (ep3_etyyy_iluna_mystuk_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_1781");
                utils.removeScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_1783"))
        {
            if (ep3_etyyy_iluna_mystuk_condition_killedGreyclimberPlusAll(player, npc))
            {
                ep3_etyyy_iluna_mystuk_action_greyclimberRewardPlusAll(player, npc);
                string_id message = new string_id(c_stringFile, "s_1785");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (ep3_etyyy_iluna_mystuk_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_1787");
                    }
                    utils.setScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId", 4);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
            if (ep3_etyyy_iluna_mystuk_condition__defaultCondition(player, npc))
            {
                ep3_etyyy_iluna_mystuk_action_greyclimberReward(player, npc);
                string_id message = new string_id(c_stringFile, "s_1791");
                utils.removeScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int ep3_etyyy_iluna_mystuk_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_1787"))
        {
            if (ep3_etyyy_iluna_mystuk_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_1789");
                utils.removeScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId");
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
            detachScript(self, "conversation.ep3_etyyy_iluna_mystuk");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.ep3_etyyy_iluna_mystuk");
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
        if (ep3_etyyy_iluna_mystuk_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_1773");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (ep3_etyyy_iluna_mystuk_condition_doesNotHaveBrodyQuest(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (ep3_etyyy_iluna_mystuk_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse1 = true;
            }
            boolean hasResponse2 = false;
            if (ep3_etyyy_iluna_mystuk_condition_killedGreyclimber(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse2 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_1775");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_1779");
                }
                if (hasResponse2)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_1783");
                }
                utils.setScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId", 1);
                npcStartConversation(player, npc, "ep3_etyyy_iluna_mystuk", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("ep3_etyyy_iluna_mystuk"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId");
        if (branchId == 1 && ep3_etyyy_iluna_mystuk_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 4 && ep3_etyyy_iluna_mystuk_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.ep3_etyyy_iluna_mystuk.branchId");
        return SCRIPT_CONTINUE;
    }
}
