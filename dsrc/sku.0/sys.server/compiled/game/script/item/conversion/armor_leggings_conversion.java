package script.item.conversion;

public class armor_leggings_conversion extends script.item.conversion.armor_base_conversion
{
    public armor_leggings_conversion()
    {
    }
    public static final String[] ARMOR_SET_ASSAULT = 
    {
        "composite/armor_composite_leggings.iff",
        "chitin/armor_chitin_s01_leggings.iff"
    };
    public static final String[] ARMOR_SET_BATTLE = 
    {
        "bone/armor_bone_s01_leggings.iff",
        "padded/armor_padded_s01_leggings.iff"
    };
    public static final String[] ARMOR_SET_RECON = 
    {
        "tantel/armor_tantel_skreej_leggings.iff",
        "ubese/armor_ubese_pants.iff"
    };
    public static final String[] ASSAULT_TYPE = 
    {
        "Composite",
        "Chitin"
    };
    public static final String[] BATTLE_TYPE = 
    {
        "Bone",
        "Padded"
    };
    public static final String[] RECON_TYPE = 
    {
        "Tantel",
        "Ubese"
    };
    public String[] getAssaultTemplates() throws InterruptedException
    {
        return ARMOR_SET_ASSAULT;
    }
    public String[] getBattleTemplates() throws InterruptedException
    {
        return ARMOR_SET_BATTLE;
    }
    public String[] getReconTemplates() throws InterruptedException
    {
        return ARMOR_SET_RECON;
    }
    public String[] getAssaultTypes() throws InterruptedException
    {
        return ASSAULT_TYPE;
    }
    public String[] getBattleTypes() throws InterruptedException
    {
        return BATTLE_TYPE;
    }
    public String[] getReconTypes() throws InterruptedException
    {
        return RECON_TYPE;
    }
}
