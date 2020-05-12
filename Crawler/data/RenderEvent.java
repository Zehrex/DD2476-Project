2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Render/RenderEvent.java
package com.naive.phase.Render;

import com.naive.phase.Auxiliary.Helper.ShaderHelper;
import com.naive.phase.Auxiliary.Helper.VectorHelper;
import com.naive.phase.Item.ItemColliculus.ItemColliculus;
import com.naive.phase.Potion.PotionRegistry;
import com.naive.phase.Render.Mistuned.MistuneRenderManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber
public class RenderEvent {

    private static TempFrameBuffer tempBuffer;

    @SubscribeEvent
    public static void onRenderEntity(RenderLivingEvent.Pre event) {
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        if (!(event.getEntity() instanceof EntityPlayer)) {
            ShaderHelper.useShader(ShaderLib.GAUSS_SHADER);
        }
    }

    @SubscribeEvent
    public static void onPostRenderEntity(RenderLivingEvent.Post event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            ShaderHelper.releaseShader();
        }
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public static void onRenderMistuned(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !MistuneRenderManager.isShouldEffect())
            return;

        if (tempBuffer == null) {
            tempBuffer = new TempFrameBuffer(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, true);
            tempBuffer.setFramebufferColor(0f, 0f, 0f, 0f);
        }

        GL11.glPushMatrix();

        Framebuffer mb = Minecraft.getMinecraft().getFramebuffer();

        tempBuffer.createBindFramebuffer(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, tempBuffer != null ? tempBuffer.framebufferObject : 0);

        ShaderHelper.useShader(ShaderLib.MISTUNE_SHADER, MistuneRenderManager.getMistuneRenderCallback());
        mb.framebufferRender(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        ShaderHelper.releaseShader();

        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, mb.framebufferObject);
        tempBuffer.framebufferRender(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);

        GL11.glPopMatrix();

    }


    //@SubscribeEvent(priority = EventPriority.HIGHEST)
    //Not enabled since the system is not yet operational
    public static void onRenderCOLLICULUS(RenderWorldLastEvent event) {
        GlStateManager.pushMatrix();
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();

        EntityPlayer player = Minecraft.getMinecraft().player;
        World world = Minecraft.getMinecraft().world;

        Set<BlockPos> seen = getSeenBlocks(player, event.getPartialTicks(), 1. / 2., 0.01, 8);

        for (BlockPos pos : seen)
            renderBlockOutlineAt(pos, Color.HSBtoRGB(world.getWorldTime() % 200 / 200f, .6f, 1f), 5f);

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPopAttrib();
        GlStateManager.popMatrix();
    }

    @SideOnly(Side.CLIENT)
    private static Set<BlockPos> getSeenBlocks(EntityPlayer player, float partialTicks, double offset, double step, double range) {
        Set<BlockPos> poses = new HashSet<>();
        Vec3d vecPlayer = player.getLook(partialTicks);
        for (double i = -offset; i <= offset; i += step) {
            for (double j = -offset; j <= offset; j += step) {
                Vec3d vecSph = VectorHelper.cartesianToSpherical(vecPlayer).addVector(0., i, j);

                Vec3d vec3d = player.getPositionEyes(partialTicks);
                Vec3d vec3d1 = VectorHelper.sphericalToCartesian(vecSph);

                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * range, vec3d1.y * range, vec3d1.z * range);
                RayTraceResult rayTraceResult = player.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
                if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK)
                    poses.add(rayTraceResult.getBlockPos());
            }
        }

        return poses;
    }

    private static void renderBlockOutlineAt(BlockPos pos, int color, float thickness) {
        double renderPosX = Minecraft.getMinecraft().getRenderManager().renderPosX;
        double renderPosY = Minecraft.getMinecraft().getRenderManager().renderPosY;
        double renderPosZ = Minecraft.getMinecraft().getRenderManager().renderPosZ;

        GlStateManager.pushMatrix();
        GlStateManager.translate(pos.getX() - renderPosX, pos.getY() - renderPosY, pos.getZ() - renderPosZ + 1);
        Color colorRGB = new Color(color);
        GL11.glColor4ub((byte) colorRGB.getRed(), (byte) colorRGB.getGreen(), (byte) colorRGB.getBlue(), (byte) 255);

        World world = Minecraft.getMinecraft().world;
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        drawWireframe:
        {
            if (block != null) {
                AxisAlignedBB axis;

                axis = state.getSelectedBoundingBox(world, pos);

                if (axis == null)
                    break drawWireframe;

                axis = axis.offset(-pos.getX(), -pos.getY(), -(pos.getZ() + 1));

                GlStateManager.scale(1F, 1F, 1F);

                GL11.glLineWidth(thickness);
                renderBlockOutline(axis);

                GL11.glLineWidth(thickness + 3F);
                GL11.glColor4ub((byte) colorRGB.getRed(), (byte) colorRGB.getGreen(), (byte) colorRGB.getBlue(), (byte) 64);
                renderBlockOutline(axis);
            }
        }

        GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) 255);
        GlStateManager.popMatrix();
    }

    private static void renderBlockOutline(AxisAlignedBB aabb) {
        Tessellator tessellator = Tessellator.getInstance();

        double ix = aabb.minX;
        double iy = aabb.minY;
        double iz = aabb.minZ;
        double ax = aabb.maxX;
        double ay = aabb.maxY;
        double az = aabb.maxZ;

        tessellator.getBuffer().begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        tessellator.getBuffer().pos(ix, iy, iz).endVertex();
        tessellator.getBuffer().pos(ix, ay, iz).endVertex();

        tessellator.getBuffer().pos(ix, ay, iz).endVertex();
        tessellator.getBuffer().pos(ax, ay, iz).endVertex();

        tessellator.getBuffer().pos(ax, ay, iz).endVertex();
        tessellator.getBuffer().pos(ax, iy, iz).endVertex();

        tessellator.getBuffer().pos(ax, iy, iz).endVertex();
        tessellator.getBuffer().pos(ix, iy, iz).endVertex();

        tessellator.getBuffer().pos(ix, iy, az).endVertex();
        tessellator.getBuffer().pos(ix, ay, az).endVertex();

        tessellator.getBuffer().pos(ix, iy, az).endVertex();
        tessellator.getBuffer().pos(ax, iy, az).endVertex();

        tessellator.getBuffer().pos(ax, iy, az).endVertex();
        tessellator.getBuffer().pos(ax, ay, az).endVertex();

        tessellator.getBuffer().pos(ix, ay, az).endVertex();
        tessellator.getBuffer().pos(ax, ay, az).endVertex();

        tessellator.getBuffer().pos(ix, iy, iz).endVertex();
        tessellator.getBuffer().pos(ix, iy, az).endVertex();

        tessellator.getBuffer().pos(ix, ay, iz).endVertex();
        tessellator.getBuffer().pos(ix, ay, az).endVertex();

        tessellator.getBuffer().pos(ax, iy, iz).endVertex();
        tessellator.getBuffer().pos(ax, iy, az).endVertex();

        tessellator.getBuffer().pos(ax, ay, iz).endVertex();
        tessellator.getBuffer().pos(ax, ay, az).endVertex();

        tessellator.draw();
    }

    @SubscribeEvent
    public static void setPhaseVisualEffect(RenderPlayerEvent.Pre event) {
        if (event.getEntityPlayer().isPotionActive(PotionRegistry.POTION_PHASE)) {

            GlStateManager.pushMatrix();

            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);

            GlStateManager.color(1f, 1f, 1f, 0.7f);
        }
    }

    @SubscribeEvent
    public static void unsetPhaseVisualEffect(RenderPlayerEvent.Post event) {

        if (event.getEntityPlayer().isPotionActive(PotionRegistry.POTION_PHASE)) {
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();

            GlStateManager.popMatrix();
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void setToNormal(TickEvent.PlayerTickEvent event) {
        ItemStack head = event.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        if (head == ItemStack.EMPTY || !(head.getItem() instanceof ItemColliculus))
            MistuneRenderManager.setShouldEffect(false);
    }
}
