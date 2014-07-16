package modularmachines.blocks;

import java.util.ArrayList;
import java.util.List;

import modularmachines.api.guide.IGuided;
import modularmachines.blocks.tiles.TilePotionTank;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import wasliecore.helpers.MathHelper;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMBlockPotionTank extends BlockContainer implements IWrenchable, IGuided{
	public MMBlockPotionTank(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(MM.modName.toLowerCase() + "." + "block" + "." + name);

		this.name = name;
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	String name;
	public IIcon icon_fluid;
	
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TilePotionTank();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cX, float cY, float cZ) {
		TilePotionTank te = (TilePotionTank)world.getTileEntity(x, y, z);		
		if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() == MMItems.programmer){
			
		}else if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemPotion){
			ItemPotion potion = (ItemPotion)player.getHeldItem().getItem();
			PotionEffect p = genEffect(player, potion).get(0);
			Potion pe = Potion.potionTypes[p.getPotionID()];
			if(te.tank.getPotion() == null){
				te.tank.setPotion(pe);
				te.tank.setAmount(10 * getPotionAmplifier(p));
				
				if(!player.capabilities.isCreativeMode){
					if(player.getHeldItem().stackSize > 1)
						player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
					else if(player.getHeldItem().stackSize == 1)
						player.setCurrentItemOrArmor(0, null);
				}
				world.markBlockForUpdate(x, y, z);
			}else if(te.tank.getPotion() != null && te.tank.getPotion() == pe && te.tank.getAmount() + (10 * getPotionAmplifier(p)) <= te.tank.capacity){
				te.tank.increaseAmount(10 * getPotionAmplifier(p));
				
				if(!player.capabilities.isCreativeMode){
					if(player.getHeldItem().stackSize > 1)
						player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
					else if(player.getHeldItem().stackSize == 1)
						player.setCurrentItemOrArmor(0, null);
				}
				world.markBlockForUpdate(x, y, z);
			}
		}else if(te != null && player.getHeldItem() == null){
			if(te.tank.getPotion() != null && te.tank.getAmount() - 10 >= 0){
				Potion p = Potion.potionTypes[te.tank.potion.id];
				if(!te.tank.potion.isInstant()){	
					if(player.getActivePotionEffect(p) == null){
						player.addPotionEffect(new PotionEffect(te.tank.potion.id, MathHelper.secondToTick(10)));
					}else if(player.getActivePotionEffect(p) != null){
						player.addPotionEffect(new PotionEffect(te.tank.potion.id, player.getActivePotionEffect(p).getDuration() + MathHelper.secondToTick(10)));
					}
				}else{
					player.addPotionEffect(new PotionEffect(te.tank.potion.id, 1));
				}
				te.tank.decreaseAmount(10);
				
				if(te.tank.getAmount() == 0)
					te.tank.setPotion(null);
				
				world.markBlockForUpdate(x, y, z);
			}
		}
		return true;
    }
	
    public List<PotionEffect> genEffect(EntityPlayer player, ItemPotion potion){
    	List<?> s = potion.getEffects(player.getHeldItem());
    	List<PotionEffect> b = new ArrayList<PotionEffect>();
    	
    	for(Object o : s){
    		PotionEffect e = (PotionEffect)o;
    		b.add(e);
    	}
    	return b;
    }
    
    public int getPotionAmplifier(PotionEffect p){
    	if(p.getAmplifier() == 0)
    		return 1;
    	else
    		return p.getAmplifier();
    }
    
    @Override
    public boolean isOpaqueCube(){
        return false;
    }
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
    
	@Override
    public void registerBlockIcons(IIconRegister ir) {
        blockIcon = ir.registerIcon(MM.modName.toLowerCase() + ":" + "tank");
        icon_fluid = ir.registerIcon(MM.modName.toLowerCase() + ":" + "fluid");
	}
	
	@Override
	public String getKey() {
		return "Potion Tank";
	}
}