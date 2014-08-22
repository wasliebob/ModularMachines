/**
* This file is created by wasliebob.
* Created on: 6 aug. 2014, at 13:09:46
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.blocks;

import java.util.List;

import modularmachines.api.misc.helpers.DyeHelper;
import modularmachines.blocks.itemblocks.MMItemBlockGlass;
import modularmachines.libs.LibMod;
import modularmachines.main.MM;
import modularmachines.main.init.MMTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MMBlockGlass extends Block{
	public MMBlockGlass(String type) {
		super(Material.glass);
		setCreativeTab(MMTabs.tabDecoration);
		setBlockName(LibMod.modName.toLowerCase() + "." + "block" + "." + type);

		this.type = type;
		
		GameRegistry.registerBlock(this, MMItemBlockGlass.class, this.getUnlocalizedName());
	}
	public String type;
	public IIcon[] icons = new IIcon[16];
	
    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean renderAsNormalBlock (){
        return false;
    }

    @Override
    public int getRenderBlockPass(){
        return 0;
    }
    
    @Override
    public int damageDropped(int meta){
    	return meta;
    }
    
    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list){
    	for(int i = 0; i < 16; i++)
    		list.add(new ItemStack(item, 1, i));
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess access, int x, int y, int z, int side){
    	Block s = access.getBlock(x, y, z);
    	return s == (Block) this ? false : super.shouldSideBeRendered(access, x, y, z, side);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir){
    	String path = "modularmachines" + ":" + type + "/" + type;
    	
    	icons[0] = ir.registerIcon(path);
    	icons[1] = ir.registerIcon(path + "_1_d");
    	icons[2] = ir.registerIcon(path + "_1_u");
    	icons[3] = ir.registerIcon(path + "_1_l");
    	icons[4] = ir.registerIcon(path + "_1_r");
    	icons[5] = ir.registerIcon(path + "_2_h");
    	icons[6] = ir.registerIcon(path + "_2_v");
    	icons[7] = ir.registerIcon(path + "_2_dl");
    	icons[8] = ir.registerIcon(path + "_2_dr");
    	icons[9] = ir.registerIcon(path + "_2_ul");
    	icons[10] = ir.registerIcon(path + "_2_ur");
    	icons[11] = ir.registerIcon(path + "_3_d");
    	icons[12] = ir.registerIcon(path + "_3_u");
    	icons[13] = ir.registerIcon(path + "_3_l");
    	icons[14] = ir.registerIcon(path + "_3_r");
    	icons[15] = ir.registerIcon(path + "_4");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int meta){
    	return DyeHelper.getColorCode(meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess access, int x, int y, int z){
    	int meta = access.getBlockMetadata(x, y, z);
    	return DyeHelper.getColorCode(meta);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return icons[0];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side){
        return access.getBlockMetadata(x, y, z) == 15 ? icons[0] : getConnectedBlockTexture(access, x, y, z, side, icons);
    }
    
    public boolean shouldConnectToBlock(IBlockAccess access, int x, int y, int z, Block block, int side){
        return block == (Block)this;
    }
    
    /** The messy part */
    public IIcon getConnectedBlockTexture(IBlockAccess access, int x, int y, int z, int side, IIcon[] icons){
        boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

        switch (side){
        case 0:
            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x - 1, y, z), access.getBlockMetadata(x - 1, y, z))){
                isOpenDown = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x + 1, y, z), access.getBlockMetadata(x + 1, y, z))){
                isOpenUp = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z - 1), access.getBlockMetadata(x, y, z - 1))){
                isOpenLeft = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z + 1), access.getBlockMetadata(x, y, z + 1))){
                isOpenRight = true;
            }

            if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight){
                return icons[15];
            }
            else if(isOpenUp && isOpenDown && isOpenLeft){
                return icons[11];
            }
            else if(isOpenUp && isOpenDown && isOpenRight){
                return icons[12];
            }
            else if(isOpenUp && isOpenLeft && isOpenRight){
                return icons[13];
            }
            else if(isOpenDown && isOpenLeft && isOpenRight){
                return icons[14];
            }
            else if(isOpenDown && isOpenUp){
                return icons[5];
            }
            else if(isOpenLeft && isOpenRight){
                return icons[6];
            }
            else if(isOpenDown && isOpenLeft){
                return icons[8];
            }
            else if(isOpenDown && isOpenRight){
                return icons[10];
            }
            else if(isOpenUp && isOpenLeft){
                return icons[7];
            }
            else if(isOpenUp && isOpenRight){
                return icons[9];
            }
            else if(isOpenDown){
                return icons[3];
            }
            else if(isOpenUp){
                return icons[4];
            }
            else if(isOpenLeft){
                return icons[2];
            }
            else if(isOpenRight){
                return icons[1];
            }
            break;
        case 1:
            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x - 1, y, z), access.getBlockMetadata(x - 1, y, z))){
                isOpenDown = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x + 1, y, z), access.getBlockMetadata(x + 1, y, z))){
                isOpenUp = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z - 1), access.getBlockMetadata(x, y, z - 1))){
                isOpenLeft = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z + 1), access.getBlockMetadata(x, y, z + 1))){
                isOpenRight = true;
            }

            if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight){
                return icons[15];
            }
            else if(isOpenUp && isOpenDown && isOpenLeft){
                return icons[11];
            }
            else if(isOpenUp && isOpenDown && isOpenRight){
                return icons[12];
            }
            else if(isOpenUp && isOpenLeft && isOpenRight){
                return icons[13];
            }
            else if(isOpenDown && isOpenLeft && isOpenRight){
                return icons[14];
            }
            else if(isOpenDown && isOpenUp){
                return icons[5];
            }
            else if(isOpenLeft && isOpenRight){
                return icons[6];
            }
            else if(isOpenDown && isOpenLeft){
                return icons[8];
            }
            else if(isOpenDown && isOpenRight){
                return icons[10];
            }
            else if(isOpenUp && isOpenLeft){
                return icons[7];
            }
            else if(isOpenUp && isOpenRight){
                return icons[9];
            }
            else if(isOpenDown){
                return icons[3];
            }
            else if(isOpenUp){
                return icons[4];
            }
            else if(isOpenLeft){
                return icons[2];
            }
            else if(isOpenRight){
                return icons[1];
            }
            break;
        case 2:
            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y - 1, z), access.getBlockMetadata(x, y - 1, z))){
                isOpenDown = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y + 1, z), access.getBlockMetadata(x, y + 1, z))){
                isOpenUp = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x - 1, y, z), access.getBlockMetadata(x - 1, y, z))){
                isOpenLeft = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x + 1, y, z), access.getBlockMetadata(x + 1, y, z))){
                isOpenRight = true;
            }

            if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight){
                return icons[15];
            }
            else if(isOpenUp && isOpenDown && isOpenLeft){
                return icons[13];
            }
            else if(isOpenUp && isOpenDown && isOpenRight){
                return icons[14];
            }
            else if(isOpenUp && isOpenLeft && isOpenRight){
                return icons[11];
            }
            else if(isOpenDown && isOpenLeft && isOpenRight){
                return icons[12];
            }
            else if(isOpenDown && isOpenUp){
                return icons[6];
            }
            else if(isOpenLeft && isOpenRight){
                return icons[5];
            }
            else if(isOpenDown && isOpenLeft){
                return icons[9];
            }
            else if(isOpenDown && isOpenRight){
                return icons[10];
            }
            else if(isOpenUp && isOpenLeft){
                return icons[7];
            }
            else if(isOpenUp && isOpenRight){
                return icons[8];
            }
            else if(isOpenDown){
                return icons[1];
            }
            else if(isOpenUp){
                return icons[2];
            }
            else if(isOpenLeft){
                return icons[4];
            }
            else if(isOpenRight){
                return icons[3];
            }
            break;
        case 3:
            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y - 1, z), access.getBlockMetadata(x, y - 1, z))){
                isOpenDown = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y + 1, z), access.getBlockMetadata(x, y + 1, z))){
                isOpenUp = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x - 1, y, z), access.getBlockMetadata(x - 1, y, z))){
                isOpenLeft = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x + 1, y, z), access.getBlockMetadata(x + 1, y, z))){
                isOpenRight = true;
            }

            if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight){
                return icons[15];
            }
            else if(isOpenUp && isOpenDown && isOpenLeft){
                return icons[14];
            }
            else if(isOpenUp && isOpenDown && isOpenRight){
                return icons[13];
            }
            else if(isOpenUp && isOpenLeft && isOpenRight){
                return icons[11];
            }
            else if(isOpenDown && isOpenLeft && isOpenRight){
                return icons[12];
            }
            else if(isOpenDown && isOpenUp){
                return icons[6];
            }
            else if(isOpenLeft && isOpenRight){
                return icons[5];
            }
            else if(isOpenDown && isOpenLeft){
                return icons[10];
            }
            else if(isOpenDown && isOpenRight){
                return icons[9];
            }
            else if(isOpenUp && isOpenLeft){
                return icons[8];
            }
            else if(isOpenUp && isOpenRight){
                return icons[7];
            }
            else if(isOpenDown){
                return icons[1];
            }
            else if(isOpenUp){
                return icons[2];
            }
            else if(isOpenLeft){
                return icons[3];
            }
            else if(isOpenRight){
                return icons[4];
            }
            break;
        case 4:
            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y - 1, z), access.getBlockMetadata(x, y - 1, z))){
                isOpenDown = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y + 1, z), access.getBlockMetadata(x, y + 1, z))){
                isOpenUp = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z - 1), access.getBlockMetadata(x, y, z - 1))){
                isOpenLeft = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z + 1), access.getBlockMetadata(x, y, z + 1))){
                isOpenRight = true;
            }

            if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight){
                return icons[15];
            }
            else if(isOpenUp && isOpenDown && isOpenLeft){
                return icons[14];
            }
            else if(isOpenUp && isOpenDown && isOpenRight){
                return icons[13];
            }
            else if(isOpenUp && isOpenLeft && isOpenRight){
                return icons[11];
            }
            else if(isOpenDown && isOpenLeft && isOpenRight){
                return icons[12];
            }
            else if(isOpenDown && isOpenUp){
                return icons[6];
            }
            else if(isOpenLeft && isOpenRight){
                return icons[5];
            }
            else if(isOpenDown && isOpenLeft){
                return icons[10];
            }
            else if(isOpenDown && isOpenRight){
                return icons[9];
            }
            else if(isOpenUp && isOpenLeft){
                return icons[8];
            }
            else if(isOpenUp && isOpenRight){
                return icons[7];
            }
            else if(isOpenDown){
                return icons[1];
            }
            else if(isOpenUp){
                return icons[2];
            }
            else if(isOpenLeft){
                return icons[3];
            }
            else if(isOpenRight){
                return icons[4];
            }
            break;
        case 5:
            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y - 1, z), access.getBlockMetadata(x, y - 1, z))){
                isOpenDown = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y + 1, z), access.getBlockMetadata(x, y + 1, z))){
                isOpenUp = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z - 1), access.getBlockMetadata(x, y, z - 1))){
                isOpenLeft = true;
            }

            if(shouldConnectToBlock(access, x, y, z, access.getBlock(x, y, z + 1), access.getBlockMetadata(x, y, z + 1))){
                isOpenRight = true;
            }

            if(isOpenUp && isOpenDown && isOpenLeft && isOpenRight){
                return icons[15];
            }
            else if(isOpenUp && isOpenDown && isOpenLeft){
                return icons[13];
            }
            else if(isOpenUp && isOpenDown && isOpenRight){
                return icons[14];
            }
            else if(isOpenUp && isOpenLeft && isOpenRight){
                return icons[11];
            }
            else if(isOpenDown && isOpenLeft && isOpenRight){
                return icons[12];
            }
            else if(isOpenDown && isOpenUp){
                return icons[6];
            }
            else if(isOpenLeft && isOpenRight){
                return icons[5];
            }
            else if(isOpenDown && isOpenLeft){
                return icons[9];
            }
            else if(isOpenDown && isOpenRight){
                return icons[10];
            }
            else if(isOpenUp && isOpenLeft){
                return icons[7];
            }
            else if(isOpenUp && isOpenRight){
                return icons[8];
            }
            else if(isOpenDown){
                return icons[1];
            }
            else if(isOpenUp){
                return icons[2];
            }
            else if(isOpenLeft){
                return icons[4];
            }
            else if (isOpenRight){
                return icons[3];
            }
            break;
        }

        return icons[0];
    }
}