package erebus.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityRhinoBeetle;

@SideOnly(Side.CLIENT)
public class RenderRhinoBeetleChargeBar extends Gui {

	private final Minecraft mc = Minecraft.getMinecraft();

	@ForgeSubscribe
	public void onRenderHUD(RenderGameOverlayEvent.Post event) {
		if (event.type.equals(RenderGameOverlayEvent.ElementType.HOTBAR)) {
			EntityClientPlayerMP player = mc.thePlayer;
			if (player != null && player.ridingEntity != null && player.ridingEntity instanceof EntityRhinoBeetle) {
				GL11.glColor4f(1F, 1F, 1F, 1F);
				mc.renderEngine.bindTexture(new ResourceLocation("erebus:textures/gui/overlay/rhinoChargeBar.png"));
				ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
				renderChargeBar(((EntityRhinoBeetle) player.ridingEntity).rammingCharge, res.getScaledWidth() / 2 + 84, res.getScaledHeight() - 30);
			}
		}
	}

	private void renderChargeBar(int currCond, int posX, int posY) {
		for (int i =0; i < currCond / 5; i++)
			drawTexturedModalRect(posX - i * 8, posY, 0, 0, 7, 1);
	}
}