package modularmachines.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCable extends ModelBase
{
    ModelRenderer Bottom;
    ModelRenderer Base;
    ModelRenderer Top;
    ModelRenderer Base2;
    ModelRenderer Middle;
    ModelRenderer Connector;

  public ModelCable()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    /** Default */
    Bottom = new ModelRenderer(this, 0, 0);
    Bottom.addBox(0F, 0F, 0F, 4, 2, 4);
    Bottom.setRotationPoint(-2F, 22F, -2F);
    Bottom.setTextureSize(64, 32);
    Bottom.mirror = true;
    setRotation(Bottom, 0F, 0F, 0F);
    Base = new ModelRenderer(this, 0, 0);
    Base.addBox(0F, 0F, 0F, 2, 5, 2);
    Base.setRotationPoint(-1F, 10F, -1F);
    Base.setTextureSize(64, 32);
    Base.mirror = true;
    setRotation(Base, 0F, 0F, 0F);
    Top = new ModelRenderer(this, 0, 0);
    Top.addBox(0F, 0F, 0F, 4, 2, 4);
    Top.setRotationPoint(-2F, 8F, -2F);
    Top.setTextureSize(64, 32);
    Top.mirror = true;
    setRotation(Top, 0F, 0F, 0F);
    Base2 = new ModelRenderer(this, 0, 0);
    Base2.addBox(0F, 0F, 0F, 2, 5, 2);
    Base2.setRotationPoint(-1F, 17F, -1F);
    Base2.setTextureSize(64, 32);
    Base2.mirror = true;
    setRotation(Base2, 0F, 0F, 0F);
    Middle = new ModelRenderer(this, 0, 0);
    Middle.addBox(0F, 0F, 0F, 4, 2, 4);
    Middle.setRotationPoint(-2F, 15F, -2F);
    Middle.setTextureSize(64, 32);
    Middle.mirror = true;
    setRotation(Middle, 0F, 0F, 0F);
  }
  
  public void render()
  {
	  final float scale = 1F / 16F;
	  Bottom.render(scale);
	  Base.render(scale);
	  Base2.render(scale);
	  Top.render(scale);
	  Middle.render(scale);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  Bottom.render(f5);
	  Base.render(f5);
	  Base2.render(f5);
	  Top.render(f5);
	  Middle.render(f5);
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