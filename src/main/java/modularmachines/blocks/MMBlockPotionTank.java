/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import modularmachines.api.guide.IGuided;
import modularmachines.api.misc.helpers.DyeHelper;
import modularmachines.api.misc.interfaces.IColorable;
import modularmachines.blocks.tiles.TilePotionTank;
import modularmachines.helpers.PotionHelper;
import modularmachines.items.MMItemOrbEmpty;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMItems;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wasliecore.helpers.MathHelper;
import wasliecore.helpers.Utils;
import wasliecore.interfaces.IWrenchable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMBlockPotionTank extends BlockContainer implements IWrenchable, IGuided{
	public MMBlockPotionTank(String name) {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(MMTabs.tabMain);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + name);

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
		if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() == Items.glass_bottle){
		}else if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() == MMItems.input && te.input == null && te.output != ForgeDirection.getOrientation(side)){
			te.input = ForgeDirection.getOrientation(side);
			if(player.getHeldItem().stackSize > 1)
				player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
			else
				player.setCurrentItemOrArmor(0, null);
			world.markBlockForUpdate(x, y, z);
		}else if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() == MMItems.output && te.output == null && te.input != ForgeDirection.getOrientation(side)){
			te.output = ForgeDirection.getOrientation(side);
			if(player.getHeldItem().stackSize > 1)
				player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
			else
				player.setCurrentItemOrArmor(0, null);
			world.markBlockForUpdate(x, y, z);	
		}else if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemPotion){
			ItemPotion potion = (ItemPotion)player.getHeldItem().getItem();
			if(potion != null && genEffect(player, potion) != null && genEffect(player, potion).size() > 0){
				PotionEffect p = genEffect(player, potion).get(0);
				Potion pe = Potion.potionTypes[p.getPotionID()];
				if(te.tank.getPotion() == null){
					te.tank.setPotion(pe);
					te.tank.setAmount(PotionHelper.potion_volume * getPotionAmplifier(p));
					
					if(!player.capabilities.isCreativeMode){
						if(player.getHeldItem().stackSize > 1)
							player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
						else if(player.getHeldItem().stackSize == 1)
							player.setCurrentItemOrArmor(0, null);
					}
					world.markBlockForUpdate(x, y, z);
				}else if(te.tank.getPotion() != null && te.tank.getPotion() == pe && te.tank.getAmount() + (PotionHelper.potion_volume * getPotionAmplifier(p)) <= te.tank.capacity){
					te.tank.increaseAmount(PotionHelper.potion_volume * getPotionAmplifier(p));
					
					if(!player.capabilities.isCreativeMode){
						if(player.getHeldItem().stackSize > 1)
							player.setCurrentItemOrArmor(0, new ItemStack(player.getHeldItem().getItem(), player.getHeldItem().stackSize--, player.getHeldItem().getItemDamage()));
						else if(player.getHeldItem().stackSize == 1)
							player.setCurrentItemOrArmor(0, null);
					}
					world.markBlockForUpdate(x, y, z);
				}
				return true;
			}
		}else if(te != null && player.getHeldItem() == null){
			if(te.tank.getPotion() != null && te.tank.getAmount() - PotionHelper.potion_volume >= 0){
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
				te.tank.decreaseAmount(PotionHelper.potion_volume);
				
				if(te.tank.getAmount() == 0)
					te.tank.setPotion(null);
				
				world.markBlockForUpdate(x, y, z);
			}
		}else if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemDye){
			int meta = player.getHeldItem().getItemDamage();
			te.setColor(new Color(DyeHelper.getColorCode(meta)));
			world.markBlockForUpdate(x, y, z);
		}else if(te != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof MMItemOrbEmpty){
			if(te.tank.getPotion() != null && te.tank.getAmount() - PotionHelper.potion_volume >= 0){
				player.setCurrentItemOrArmor(0, new ItemStack(MMItems.orb_potion, 1, PotionHelper.getIDFromPotion(te.tank.potion)));
				
				te.tank.decreaseAmount(PotionHelper.potion_volume);
				
				if(te.tank.getAmount() == 0)
					te.tank.setPotion(null);
				
				world.markBlockForUpdate(x, y, z);
			}
		}
		return false;
    }
	
    public List<PotionEffect> genEffect(EntityPlayer player, ItemPotion potion){
    	List<?> s = potion.getEffects(player.getHeldItem());
    	List<PotionEffect> b = new ArrayList<PotionEffect>();
    	
    	if(s != null && !s.isEmpty()){
    		for(Object o : s){
    			PotionEffect e = (PotionEffect)o;
    			b.add(e);
    		}
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
        blockIcon = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "tank");
        icon_fluid = ir.registerIcon(LibMod.modName.toLowerCase() + ":" + "fluid");
	}
	
	@Override
	public String getKey() {
		return "Potion Tank";
	}
	
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z){
    	TileEntity te = world.getTileEntity(x, y, z);
    	if(te != null && te instanceof IColorable && ((IColorable)te).getColor() != null){
    		return ((IColorable)te).getColor().getRGB();
    	}
    	return 0;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int side){
    	TilePotionTank te = (TilePotionTank)world.getTileEntity(x, y, z);
    	
    	ArrayList<ItemStack> equiped = new ArrayList<ItemStack>();
    	
    	if(te != null && te.input != null)
    		equiped.add(new ItemStack(MMItems.input));
    	if(te != null && te.output != null)
    		equiped.add(new ItemStack(MMItems.output));
    	
    	for(ItemStack stack : equiped){
    		Utils.dropBlock(world, x, y, z, stack);
    	}
    	
    	super.breakBlock(world, x, y, z, block, side);
    	
    	world.setBlock(x, y, z, Blocks.air);
    }
    
	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z){
        int side = target.sideHit;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        TileEntity te = world.getTileEntity(target.blockX, target.blockY, target.blockZ);
        
        if(te != null && te instanceof TilePotionTank){
        	TilePotionTank tr = (TilePotionTank)te;
        	if(dir == tr.input)
        		return new ItemStack(MMItems.input);
        	else if(dir == tr.output)
        		return new ItemStack(MMItems.output);
        }
        return super.getPickBlock(target, world, x, y, z);
    }
}