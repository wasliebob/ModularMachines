/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 15:24:57
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.entities.projectiles;

import wasliecore.helpers.MathHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/** TODO Make it get color of the orb, not sure if it's even possible but might try. */
public class ProjectilePotionOrb extends EntityThrowable{
    public ProjectilePotionOrb(World world){
        super(world);
    }

    public ProjectilePotionOrb(World world, EntityLivingBase base){
        super(world, base);
    }

    public ProjectilePotionOrb(World world, double x, double y, double z){
        super(world, x, y, z);
    }
    public Potion potion;
    
    public void setPotion(Potion potion){
    	this.potion = potion;
    }
    
    protected void onImpact(MovingObjectPosition mop){
    	if(mop.entityHit != null && mop.entityHit instanceof EntityLivingBase){
    		EntityLivingBase base = (EntityLivingBase)mop.entityHit;
			if(!potion.isInstant()){	
				if(base.getActivePotionEffect(potion) == null){
					base.addPotionEffect(new PotionEffect(potion.id, MathHelper.secondToTick(10)));
				}else if(base.getActivePotionEffect(potion) != null){
					base.addPotionEffect(new PotionEffect(potion.id, base.getActivePotionEffect(potion).getDuration() + MathHelper.secondToTick(10)));
				}
			}else{
				base.addPotionEffect(new PotionEffect(potion.id, 1));
			}
			
            for (int i = 0; i < 8; ++i){
                this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
            
            if(!worldObj.isRemote){
                this.setDead();
            }
    	}
    }
}