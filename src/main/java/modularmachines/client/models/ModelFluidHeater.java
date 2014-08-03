/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFluidHeater extends ModelBase
{
    ModelRenderer Bottom;
    ModelRenderer Pipe;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
  
  public ModelFluidHeater()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    Bottom = new ModelRenderer(this, 0, 0);
    Bottom.addBox(0F, 0F, 0F, 16, 3, 16);
    Bottom.setRotationPoint(-8F, 21F, -8F);
    Bottom.setTextureSize(64, 32);
    Bottom.mirror = true;
    setRotation(Bottom, 0F, 0F, 0F);
    Pipe = new ModelRenderer(this, 0, 0);
    Pipe.addBox(0F, 0F, 0F, 2, 4, 2);
    Pipe.setRotationPoint(-1F, 17F, -1F);
    Pipe.setTextureSize(64, 32);
    Pipe.mirror = true;
    setRotation(Pipe, 0F, 0F, 0F);
    Shape1 = new ModelRenderer(this, 0, 0);
    Shape1.addBox(0F, 0F, 0F, 1, 4, 1);
    Shape1.setRotationPoint(7F, 17F, -8F);
    Shape1.setTextureSize(64, 32);
    Shape1.mirror = true;
    setRotation(Shape1, 0F, 0F, 0F);
    Shape2 = new ModelRenderer(this, 0, 0);
    Shape2.addBox(0F, 0F, 0F, 1, 4, 1);
    Shape2.setRotationPoint(7F, 17F, 7F);
    Shape2.setTextureSize(64, 32);
    Shape2.mirror = true;
    setRotation(Shape2, 0F, 0F, 0F);
    Shape3 = new ModelRenderer(this, 0, 0);
    Shape3.addBox(0F, 0F, 0F, 1, 4, 1);
    Shape3.setRotationPoint(-8F, 17F, 7F);
    Shape3.setTextureSize(64, 32);
    Shape3.mirror = true;
    setRotation(Shape3, 0F, 0F, 0F);
    Shape4 = new ModelRenderer(this, 0, 0);
    Shape4.addBox(0F, 0F, 0F, 1, 4, 1);
    Shape4.setRotationPoint(-8F, 17F, -8F);
    Shape4.setTextureSize(64, 32);
    Shape4.mirror = true;
    setRotation(Shape4, 0F, 0F, 0F);
    Shape5 = new ModelRenderer(this, 0, 0);
    Shape5.addBox(0F, 0F, 0F, 16, 3, 16);
    Shape5.setRotationPoint(-8F, 14F, -8F);
    Shape5.setTextureSize(64, 32);
    Shape5.mirror = true;
    setRotation(Shape5, 0F, 0F, 0F);
  }
  
  public void render()
  {
	  final float scale = 1F / 16F;
	  Bottom.render(scale);
	  Pipe.render(scale);
	  Shape1.render(scale);
	  Shape2.render(scale);
	  Shape3.render(scale);
	  Shape4.render(scale);
	  Shape5.render(scale);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  Bottom.render(f5);
	  Pipe.render(f5);
	  Shape1.render(f5);
	  Shape2.render(f5);
	  Shape3.render(f5);
	  Shape4.render(f5);
	  Shape5.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
	  model.rotateAngleX = x;
	  model.rotateAngleY = y;
	  model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
	  super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}