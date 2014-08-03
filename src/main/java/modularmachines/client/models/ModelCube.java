/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCube extends ModelBase
{
    ModelRenderer cube;

  public ModelCube()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    cube = new ModelRenderer(this, 0, 0);
    cube.addBox(0F, 0F, 0F, 16, 16, 16);
//    cube.setRotationPoint(-2F, 22F, -2F);
    cube.setTextureSize(64, 32);
    cube.mirror = true;
  }
  
  public void render()
  {
	  final float scale = 1F / 16F;
	  cube.render(scale);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  cube.render(f5);
  }
  
//  private void setRotation(ModelRenderer model, float x, float y, float z)
//  {
//	  model.rotateAngleX = x;
//	  model.rotateAngleY = y;
//	  model.rotateAngleZ = z;
//  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
	  super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}