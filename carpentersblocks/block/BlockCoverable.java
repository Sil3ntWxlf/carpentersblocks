package carpentersblocks.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.api.ICarpentersChisel;
import carpentersblocks.api.ICarpentersHammer;
import carpentersblocks.data.Barrier;
import carpentersblocks.data.Gate;
import carpentersblocks.renderer.helper.ParticleHelper;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.tileentity.TECarpentersFlowerPot;
import carpentersblocks.util.BlockProperties;
import carpentersblocks.util.flowerpot.FlowerPotProperties;
import carpentersblocks.util.handler.EventHandler;
import carpentersblocks.util.handler.OverlayHandler;
import carpentersblocks.util.handler.PatternHandler;
import carpentersblocks.util.registry.BlockRegistry;
import carpentersblocks.util.registry.FeatureRegistry;
import carpentersblocks.util.registry.IconRegistry;
import carpentersblocks.util.registry.ItemRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCoverable extends BlockContainer {
    
    public BlockCoverable(Material material)
    {
        super(material);
        //setBurnProperties(blockID, 5, 20);
        setHardness(0.2F);
        setStepSound(BlockProperties.stepSound);
        setCreativeTab(CarpentersBlocks.creativeTab);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        if (FeatureRegistry.enableMCPatcherCompatibility) {
            IconRegistry.registerIcons(iconRegister);
        }
        
        super.registerBlockIcons(iconRegister);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    /**
     * Returns the icon on the side given the block metadata.
     * Due to the amount of control needed over this, vanilla calls will always return icon_blank.
     */
    public IIcon getIcon(int side, int metadata)
    {
        switch (metadata) {
            case EventHandler.BLOCKICON_BASE_ID:
                return blockIcon;
            default:
                return IconRegistry.icon_blank;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        TEBase TE = (TEBase) world.getTileEntity(x, y, z);
        
        int metadata = BlockProperties.hasCover(TE, 6) ? BlockProperties.getCoverMetadata(TE, 6) : EventHandler.BLOCKICON_BASE_ID;
        
        return BlockProperties.getCover(TE, 6).getIcon(side, metadata);
    }
    
    /**
     * Returns an array of adjacent TEBase tile entities.
     */
    protected TEBase[] getAdjacentTileEntities(World world, int x, int y, int z)
    {        
        Block block_YN = world.getBlock(x, y - 1, z);
        Block block_YP = world.getBlock(x, y + 1, z);
        Block block_ZN = world.getBlock(x, y, z - 1);
        Block block_ZP = world.getBlock(x, y, z + 1);
        Block block_XN = world.getBlock(x - 1, y, z);
        Block block_XP = world.getBlock(x + 1, y, z);

        TEBase[] list = {
                block_YN != null && block_YN instanceof BlockCoverable ? (TEBase) world.getTileEntity(x, y - 1, z) : null,
                block_YP != null && block_YP instanceof BlockCoverable ? (TEBase) world.getTileEntity(x, y + 1, z) : null,
                block_ZN != null && block_ZN instanceof BlockCoverable ? (TEBase) world.getTileEntity(x, y, z - 1) : null,
                block_ZP != null && block_ZP instanceof BlockCoverable ? (TEBase) world.getTileEntity(x, y, z + 1) : null,
                block_XN != null && block_XN instanceof BlockCoverable ? (TEBase) world.getTileEntity(x - 1, y, z) : null,
                block_XP != null && block_XP instanceof BlockCoverable ? (TEBase) world.getTileEntity(x + 1, y, z) : null
        };
        
        return list;        
    }
    
    /**
     * Returns tile entity if this block is valid, otherwise null.
     * Always grab it from here then cast it to the type you need
     * after verifying the block is what you're expecting.
     */
    protected TEBase getTileEntity(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).equals(this)) {
            return (TEBase) world.getTileEntity(x, y, z);
        } else {
            return null;
        }
    }
    
    /**
     * Returns true if player is operator.
     * Can only return true if called server-side.
     */
    protected boolean isOp(EntityLivingBase entityLiving)
    {
        if (!entityLiving.worldObj.isRemote) {
            return ((EntityPlayerMP)entityLiving).mcServer.getConfigurationManager().isPlayerOpped(((EntityPlayer)entityLiving).getDisplayName());
        } else {
            return false;
        }
    }
    
    /**
     * Returns whether player is allowed to make alterations to this block.
     * This does not include block activation.  For that, use canPlayerActivate().
     */
    protected boolean canPlayerEdit(TEBase TE, EntityPlayer entityPlayer)
    {
        if (isOp(entityPlayer)) {
            return true;
        } else if (FeatureRegistry.enableBlockOwnership) {
            return TE.isOwner((EntityPlayer) entityPlayer);
        } else {
            return ((EntityPlayer) entityPlayer).canPlayerEdit(TE.xCoord, TE.yCoord, TE.zCoord, EventHandler.eventFace, entityPlayer.getHeldItem());
        }
    }
    
    /**
     * Returns whether player is allowed to activate this block.
     */
    protected boolean canPlayerActivate(TEBase TE, EntityPlayer entityPlayer)
    {
        return true;
    }
    
    @Override
    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public final void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityPlayer)
    {
        TEBase TE = (TEBase) world.getTileEntity(x, y, z);
        
        if (canPlayerEdit(TE, entityPlayer)) {
            
            ItemStack itemStack = entityPlayer.getCurrentEquippedItem();
            
            if (itemStack != null) {
                
                int effectiveSide = BlockProperties.hasCover(TE, EventHandler.eventFace) ? EventHandler.eventFace : 6;
                Item item = itemStack.getItem();
                
                if (item instanceof ICarpentersHammer && ((ICarpentersHammer)item).canUseHammer(world, entityPlayer)) {
                    
                    List<Boolean> altered = new ArrayList<Boolean>();
                    
                    altered.add(preOnBlockClicked(TE, world, x, y, z, entityPlayer));
                    
                    if (!altered.contains(true)) {
                        
                        if (entityPlayer.isSneaking()) {
                            
                            if (BlockProperties.hasOverlay(TE, effectiveSide)) {
                                altered.add(BlockProperties.setOverlay(TE, effectiveSide, (ItemStack)null));
                            } else if (BlockProperties.hasDyeColor(TE, effectiveSide)) {
                                altered.add(BlockProperties.setDyeColor(TE, effectiveSide, 0));
                            } else if (BlockProperties.hasCover(TE, effectiveSide)) {
                                altered.add(BlockProperties.setCover(TE, effectiveSide, 0, (ItemStack)null));
                                altered.add(BlockProperties.setPattern(TE, effectiveSide, 0));
                            }
                            
                        } else {
                            
                            altered.add(onHammerLeftClick(TE, entityPlayer));
                            
                        }
                    }
                    
                    if (altered.contains(true)) {
                        onNeighborBlockChange(world, x, y, z, this);
                        world.notifyBlocksOfNeighborChange(x, y, z, this);
                    }
                    
                } else if (item instanceof ICarpentersChisel && ((ICarpentersChisel)item).canUseChisel(world, entityPlayer)) {
                    
                    if (entityPlayer.isSneaking()) {
                        
                        if (BlockProperties.hasPattern(TE, effectiveSide)) {
                            BlockProperties.setPattern(TE, effectiveSide, 0);
                        }
                        
                    } else if (BlockProperties.hasCover(TE, effectiveSide) && BlockProperties.getCover(TE, effectiveSide).isOpaqueCube()) {
                        
                        onChiselClick(TE, effectiveSide, true);
                        
                    }
                }
            }
        }
    }
    
    @Override
    /**
     * Called upon block activation (right click on the block.)
     */
    public final boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = entityPlayer.getCurrentEquippedItem();
        
        if (world.isRemote)
        {
            /* If item with right-click action is being held (food, etc), do not continue. */
            
            if (itemStack != null) {
                if (itemStack.getItem() instanceof ItemBlock) {
                    if (Block.getBlockFromItem(itemStack.getItem()) instanceof BlockCoverable) {
                        return false;
                    }
                } else {
                    if (!itemStack.getItemUseAction().equals(EnumAction.none)) {
                        return false;
                    }
                }
            }
            
            return true;
        }
        
        TEBase TE = (TEBase) world.getTileEntity(x, y, z);
        List<Boolean> altered = new ArrayList<Boolean>();
        
        if (canPlayerActivate(TE, entityPlayer)) {
            
            List<Boolean> decInv = new ArrayList<Boolean>();
            
            /* Sides 0-5 are side covers, and 6 is the base block. */
            int effectiveSide = BlockProperties.hasCover(TE, side) ? side : 6;
            
            boolean[] result = preOnBlockActivated(TE, world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
            
            altered.add(result[0]);
            decInv.add(result[1]);
            
            if (canPlayerEdit(TE, entityPlayer)) {
                
                if (!altered.contains(true)) {
                    
                    if (itemStack != null) {
                        
                        if (itemStack.getItem() instanceof ICarpentersHammer && ((ICarpentersHammer)itemStack.getItem()).canUseHammer(world, entityPlayer)) {
                            
                            altered.add(onHammerRightClick(TE, entityPlayer));
                            
                        } else if (ItemRegistry.enableChisel && itemStack.getItem() instanceof ICarpentersChisel && ((ICarpentersChisel)itemStack.getItem()).canUseChisel(world, entityPlayer)) {
                            
                            if (BlockProperties.hasCover(TE, effectiveSide)) {
                                altered.add(onChiselClick(TE, effectiveSide, false));
                            }
                            
                        } else if (FeatureRegistry.enableCovers && canCoverBase(TE, world, x, y, z) && BlockProperties.isCover(itemStack)) {
                            
                            Block block = Block.getBlockFromItem(itemStack.getItem());
                            
                            /* Will handle blocks that save directions using only x and y axes (pumpkin) */
                            int metadata = block instanceof BlockDirectional ? MathHelper.floor_double(entityPlayer.rotationYaw * 4.0F / 360.0F + 2.5D) & 3 : itemStack.getItemDamage();
                            
                            /* Will handle blocks that save directions using all axes (logs, quartz) */
                            if (BlockProperties.blockRotates(block)) {
                                int facing = BlockProperties.getOppositeFacing(EventHandler.eventEntityPlayer);
                                int side_interpolated =    entityPlayer.rotationPitch < -45.0F ? 0 : entityPlayer.rotationPitch > 45 ? 1 : facing == 0 ? 3 : facing == 1 ? 4 : facing == 2 ? 2 : 5;
                                metadata = block.onBlockPlaced(world, x, y, z, side_interpolated, hitX, hitY, hitZ, metadata);
                            }
                            
                            if (!BlockProperties.hasCover(TE, 6)) {
                                
                                altered.add(decInv.add(BlockProperties.setCover(TE, 6, metadata, itemStack)));
                                
                            } else if (FeatureRegistry.enableSideCovers) {
                                
                                if (!BlockProperties.hasCover(TE, side) && canCoverSide(TE, world, x, y, z, side)) {
                                    altered.add(decInv.add(BlockProperties.setCover(TE, side, metadata, itemStack)));
                                }
                                
                            }
                            
                        } else if (FeatureRegistry.enableOverlays && BlockProperties.isOverlay(itemStack)) {
                            
                            if (!BlockProperties.hasOverlay(TE, effectiveSide) && (effectiveSide < 6 && BlockProperties.hasCover(TE, effectiveSide) || effectiveSide == 6)) {
                                altered.add(decInv.add(BlockProperties.setOverlay(TE, effectiveSide, itemStack)));
                            }
                            
                        } else if (FeatureRegistry.enableDyeColors && itemStack.getItem().equals(Items.dye) && itemStack.getItemDamage() != 15) {
                            
                            if (!BlockProperties.hasDyeColor(TE, effectiveSide)) {
                                altered.add(decInv.add(BlockProperties.setDyeColor(TE, effectiveSide, 15 - itemStack.getItemDamage())));
                            }
                            
                        }
                    }
                }
            }
            
            if (!altered.contains(true)) {
                
                if (canPlayerActivate(TE, entityPlayer)) {
                    result = postOnBlockActivated(TE, world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
                    altered.add(result[0]);
                    decInv.add(result[1]);
                }
                
            } else {
                
                BlockProperties.playBlockSound(TE, BlockProperties.getCover(TE, 6));
                damageItemWithChance(world, entityPlayer);
                onNeighborBlockChange(world, x, y, z, this);
                world.notifyBlocksOfNeighborChange(x, y, z, this);
                
            }
            
            if (decInv.contains(true)) {
                if (!entityPlayer.capabilities.isCreativeMode && --itemStack.stackSize <= 0) {
                    entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, (ItemStack)null);
                }
            }
            
        }
        
        return altered.contains(true);
    }
    
    /**
     * Cycles through chisel patterns.
     */
    public boolean onChiselClick(TEBase TE, int side, boolean leftClick)
    {
        int pattern = BlockProperties.getPattern(TE, side);
        int neighbor_pattern = 0;
        
        if (pattern == 0) {
            
            World world = TE.getWorldObj();
            
            /* Match pattern with adjacent pattern if possible. */
            
            TEBase[] TE_list = getAdjacentTileEntities(world, TE.xCoord, TE.yCoord, TE.zCoord);
            
            for (TEBase TE_current : TE_list) {
                
                if (TE_current != null) {

                    Block block = TE_current.getBlockType();
                    
                    if (BlockProperties.hasPattern(TE_current, side)) {
                        pattern = BlockProperties.getPattern(TE_current, side);
                        neighbor_pattern = pattern;
                    }
                
                }
                
            }
            
        }
        
        if (neighbor_pattern == 0) {
            pattern = leftClick ? PatternHandler.getPrev(pattern) : PatternHandler.getNext(pattern);
        }
        
        BlockProperties.setPattern(TE, side, pattern);
        
        return true;
    }
    
    @Override
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (!world.isRemote && TE != null) {
            
            /*
             * Check for and eject side covers that are blocked by a
             * solid adjacent block.
             */
            if (BlockProperties.hasSideCovers(TE))
            {
                for (int side = 0; side < 6; ++side)
                {
                    if (BlockProperties.hasCover(TE, side))
                    {
                        /*
                         * If block state no longer allows a side cover on
                         * side, eject side cover.
                         */
                        if (!canCoverSide(TE, world, x, y, z, side))
                        {
                            BlockProperties.clearAttributes(TE, side);
                            return;
                        }
                        
                        float sideCoverDepth = BlockProperties.getSideCoverDepth(TE, side);
                        
                        /*
                         * If side is obstructed, eject side cover.
                         * Currently does not check for side depth.
                         */
                        ForgeDirection dir = ForgeDirection.getOrientation(side);
                        int x_offset = x + dir.offsetX;
                        int y_offset = y + dir.offsetY;
                        int z_offset = z + dir.offsetZ;
                        
                        if (world.getBlock(x_offset, y_offset, z_offset) != null)
                        {
                            Block adjBlock = world.getBlock(x_offset, y_offset, z_offset);
                            ForgeDirection adj_side = ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[side]);
                            
                            if (adjBlock.isSideSolid(world, x_offset, y_offset, z_offset, adj_side)) {
                                
                                switch (adj_side) {
                                    case DOWN:
                                        if (adjBlock.getBlockBoundsMinY() < sideCoverDepth) {
                                            BlockProperties.clearAttributes(TE, side);
                                        }
                                        break;
                                    case UP:
                                        if (adjBlock.getBlockBoundsMaxY() > 1.0F - sideCoverDepth) {
                                            BlockProperties.clearAttributes(TE, side);
                                        }
                                        break;
                                    case NORTH:
                                        if (adjBlock.getBlockBoundsMinZ() < sideCoverDepth) {
                                            BlockProperties.clearAttributes(TE, side);
                                        }
                                        break;
                                    case SOUTH:
                                        if (adjBlock.getBlockBoundsMaxZ() > 1.0F - sideCoverDepth) {
                                            BlockProperties.clearAttributes(TE, side);
                                        }
                                        break;
                                    case WEST:
                                        if (adjBlock.getBlockBoundsMinX() < sideCoverDepth) {
                                            BlockProperties.clearAttributes(TE, side);
                                        }
                                        break;
                                    case EAST:
                                        if (adjBlock.getBlockBoundsMaxX() > 1.0F - sideCoverDepth) {
                                            BlockProperties.clearAttributes(TE, side);
                                        }
                                        break;
                                    default: {}
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            
            if (BlockProperties.hasCover(TE, 6)) {
                
                int effectiveSide = ForgeDirection.OPPOSITES[side];
                int power = BlockProperties.getCover(world, 6, x, y, z).isProvidingWeakPower(world, x, y, z, side);
                int power_side = BlockProperties.hasCover(TE, effectiveSide) ? BlockProperties.getCover(TE, effectiveSide).isProvidingWeakPower(world, x, y, z, side) : 0;
                
                return power_side > power ? power_side : power;
                
            }
            
        }
        
        return 0;
    }
    
    @Override
    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            
            if (BlockProperties.hasCover(TE, 6)) {
                
                int effectiveSide = ForgeDirection.OPPOSITES[side];
                int power = BlockProperties.getCover(world, 6, x, y, z).isProvidingStrongPower(world, x, y, z, side);
                int power_side = BlockProperties.hasCover(TE, effectiveSide) ? BlockProperties.getCover(TE, effectiveSide).isProvidingStrongPower(world, x, y, z, side) : 0;
                
                return power_side > power ? power_side : power;
                
            }
            
        }
        
        return 0;
    }
    
    /**
     * Indicates whether block destruction should be suppressed when block is clicked.
     * Will return true if player is holding a Carpenter's tool in creative mode.
     */
    private boolean suppressDestroyBlock(EntityPlayer entityPlayer)
    {
        ItemStack itemStack = entityPlayer.getHeldItem();
        
        if (itemStack != null) {
            Item item = entityPlayer.getHeldItem().getItem();
            return entityPlayer.capabilities.isCreativeMode && item != null && (item instanceof ICarpentersHammer || item instanceof ICarpentersChisel);
        }
        
        return false;
    }
    
    @Override
    /**
     * Called when a player removes a block.
     * This controls block break behavior when a player in creative mode left-clicks on block while holding a Carpenter's Hammer
     */
    public boolean removedByPlayer(World world, EntityPlayer entityPlayer, int x, int y, int z)
    {
        if (!suppressDestroyBlock(entityPlayer)) {
            return world.setBlockToAir(x, y, z);
        }
        
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Spawn a digging particle effect in the world, this is a wrapper
     * around EffectRenderer.addBlockHitEffects to allow the block more
     * control over the particles. Useful when you have entirely different
     * texture sheets for different sides/locations in the world.
     *
     * @param world The current world
     * @param target The target the player is looking at {x/y/z/side/sub}
     * @param effectRenderer A reference to the current effect renderer.
     * @return True to prevent vanilla digging particles form spawning.
     */
    public boolean addHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        TEBase TE = (TEBase) world.getTileEntity(target.blockX, target.blockY, target.blockZ);
        
        int effectiveSide = BlockProperties.hasCover(TE, EventHandler.eventFace) ? EventHandler.eventFace : 6;
        
        Block block = BlockProperties.getCover(TE, effectiveSide);
        
        /* Check for overlays that influence particles */
        if (EventHandler.eventFace == 1) {
            block = OverlayHandler.getBlockFromOverlay(TE, effectiveSide, block);
        }
        
        int metadata = block instanceof BlockCoverable ? EventHandler.BLOCKICON_BASE_ID : BlockProperties.getCoverMetadata(TE, effectiveSide);
        
        double xOffset = target.blockX + world.rand.nextDouble() * (block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() - 0.1F * 2.0F) + 0.1F + block.getBlockBoundsMinX();
        double yOffset = target.blockY + world.rand.nextDouble() * (block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() - 0.1F * 2.0F) + 0.1F + block.getBlockBoundsMinY();
        double zOffset = target.blockZ + world.rand.nextDouble() * (block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() - 0.1F * 2.0F) + 0.1F + block.getBlockBoundsMinZ();
        
        switch (target.sideHit) {
            case 0:
                yOffset = target.blockY + block.getBlockBoundsMinY() - 0.1D;
                break;
            case 1:
                yOffset = target.blockY + block.getBlockBoundsMaxY() + 0.1D;
                break;
            case 2:
                zOffset = target.blockZ + block.getBlockBoundsMinZ() - 0.1D;
                break;
            case 3:
                zOffset = target.blockZ + block.getBlockBoundsMaxZ() + 0.1D;
                break;
            case 4:
                xOffset = target.blockX + block.getBlockBoundsMinX() - 0.1D;
                break;
            case 5:
                xOffset = target.blockX + block.getBlockBoundsMaxX() + 0.1D;
                break;
        }
        
        ParticleHelper.addBlockHitEffect(TE, target, xOffset, yOffset, zOffset, block, metadata, effectRenderer);
        
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Renders block destruction effects.
     * This is controlled to prevent block destroy effects if left-clicked with a Carpenter's Hammer while player is in creative mode.
     *
     * Returns false to display effects.  True suppresses them (backwards).
     */
    public boolean addDestroyEffects(World world, int x, int y, int z, int metadata, EffectRenderer effectRenderer)
    {
        /*
         * We don't have the ability to accurately determine the entity that is
         * hitting the block. So, instead we're guessing based on who is
         * closest. This should be adequate most of the time.
         */
        
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            
            EntityPlayer entityPlayer = world.getClosestPlayer(x, y, z, 6.5F);
            
            if (entityPlayer != null) {
                
                if (!suppressDestroyBlock(entityPlayer)) {
                    
                    metadata = BlockProperties.hasCover(TE, 6) ? BlockProperties.getCoverMetadata(TE, 6) : EventHandler.BLOCKICON_BASE_ID;
                    ParticleHelper.addDestroyEffect(world, x, y, z, BlockProperties.getCover(TE, 6), metadata, effectRenderer);
                    
                } else {
                    
                    return true;
                    
                }
                
            }
        }
        
        return false;
    }
    
    @Override
    /**
     * Returns light value based on cover or side covers.
     */
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
    	TEBase TE = getTileEntity(world, x, y, z);
    	
        if (TE != null) {

            if (BlockProperties.hasCover(TE, 6)) {

                int lightOutput = getLightValue();

                for (int side = 0; side < 7; ++side)
                {
                    if (BlockProperties.hasCover(TE, side))
                    {
                        int tempLightOutput = BlockProperties.getCover(TE, side).getLightValue();

                        if (tempLightOutput > lightOutput) {
                            lightOutput = tempLightOutput;
                        }
                    }
                }

                int auxLightValue = auxiliaryGetLightValue(TE, world, x, y, z);

                if (auxLightValue > lightOutput) {
                    lightOutput = auxLightValue;
                }

                return lightOutput;
                                
            }
            
        }
        
        return lightValue;
    }
    
    @Override
    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World world, int x, int y, int z)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            if (BlockProperties.hasCover(TE, 6)) {
                return BlockProperties.getCover(world, 6, x, y, z).getBlockHardness(world,  x,  y,  z);
            }
        }
        
        return blockHardness;
    }
    
    @Override
    /**
     * Chance that fire will spread and consume this block.
     */
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return Blocks.fire.getFlammability(BlockProperties.getCover(world, 6, x, y, z));
    }
    
    @Override
    /**
     * Called when fire is updating on a neighbor block.
     */
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return Blocks.fire.getEncouragement(BlockProperties.getCover(world, 6, x, y, z));
    }
    
    @Override
    /**
     * Currently only called by fire when it is on top of this block.
     * Returning true will prevent the fire from naturally dying during updating.
     * Also prevents fire from dying from rain.
     */
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            if (BlockProperties.hasCover(TE, 6)) {
                return BlockProperties.getCover(TE, 6).isFireSource(world, x, y, z, side);
            }
        }
        
        return false;
    }
    
    @Override
    /**
     * Location sensitive version of getExplosionRestance
     */
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            if (BlockProperties.hasCover(TE, 6)) {
                return BlockProperties.getCover(world, 6, x, y, z).getExplosionResistance(entity);
            }
        }
        
        return this.getExplosionResistance(entity);
    }
    
    @Override
    /**
     * Returns whether block is wood
     */
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            if (BlockProperties.hasCover(TE, 6)) {
                return BlockProperties.getCover(world, 6, x, y, z).isWood(world,  x,  y,  z);
            }
        }
        
        return false;
    }
    
    /**
     * Determines if this block is can be destroyed by the specified entities normal behavior.
     */
    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            
            if (BlockProperties.hasCover(TE, 6)) {
                
                Block block = BlockProperties.getCover(TE, 6);
                
                if (entity instanceof EntityWither) {
                    return !block.equals(Blocks.bedrock) && !block.equals(Blocks.end_portal) && !block.equals(Blocks.end_portal_frame) && !block.equals(Blocks.command_block);
                } else if (entity instanceof EntityDragon) {
                    return !block.equals(Blocks.obsidian) && !block.equals(Blocks.end_stone) && !block.equals(Blocks.bedrock);
                }
                
            }
            
        }
        
        return true;
    }
    
    @Override
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            if (BlockProperties.hasCover(TE, 6)) {
                BlockProperties.getCover(TE, 6).onEntityCollidedWithBlock(world, x, y, z, entity);
            }
        }
    }
    
    @Override
    /**
     * Ejects contained items into the world, and notifies neighbors of an update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            for (int side = 0; side < 7; ++side) {
                BlockProperties.clearAttributes(TE, side);
            }
        }
        
        super.breakBlock(world, x, y, z, block, metadata);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            
            if (BlockProperties.hasCover(TE, 6)) {
                BlockProperties.getCover(world, 6, x, y, z).randomDisplayTick(world, x, y, z, random);
            }
            
            if (BlockProperties.getOverlay(TE, 6) == OverlayHandler.OVERLAY_MYCELIUM) {
                Blocks.mycelium.randomDisplayTick(world, x, y, z, random);
            }
            
        }
    }
    
    /**
     * Determines if this block can support the passed in plant, allowing it to be planted and grow.
     * Some examples:
     *   Reeds check if its a reed, or if its sand/dirt/grass and adjacent to water
     *   Cacti checks if its a cacti, or if its sand
     *   Nether types check for soul sand
     *   Crops check for tilled soil
     *   Caves check if it's a solid surface
     *   Plains check if its grass or dirt
     *   Water check if its still water
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z position
     * @param direction The direction relative to the given position the plant wants to be, typically its UP
     * @param plantable The plant that wants to check
     * @return True to allow the plant to be planted/stay.
     */
    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            
            List<Block> blocks = new ArrayList<Block>();
            
            /*
             * Add base block, top block, and both of their associated
             * overlays to judge whether plants can be supported on block.
             */
            
            for (int side = 1; side < 6; side += 5) {
                
                if (BlockProperties.hasCover(TE, side)) {
                    blocks.add(BlockProperties.getCover(TE, side));
                }
                
                if (BlockProperties.hasOverlay(TE, side)) {
                    
                    switch (BlockProperties.getOverlay(TE, side)) {
                        case OverlayHandler.OVERLAY_GRASS:
                            blocks.add(Blocks.grass);
                            break;
                        case OverlayHandler.OVERLAY_HAY:
                            blocks.add(Blocks.hay_block);
                            break;
                        case OverlayHandler.OVERLAY_MYCELIUM:
                            blocks.add(Blocks.mycelium);
                            break;
                        case OverlayHandler.OVERLAY_SNOW:
                            blocks.add(Blocks.snow);
                            break;
                    }
                    
                }
                
            }
            
            EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
            
            switch (plantType)
            {
                case Desert: return blocks.contains(Blocks.sand);
                case Nether: return blocks.contains(Blocks.soul_sand);
                case Crop:   return blocks.contains(Blocks.farmland);
                case Cave:   return isSideSolid(world, x, y, z, UP);
                case Plains: return blocks.contains(Blocks.grass) || blocks.contains(Blocks.dirt);
                case Beach:
                    boolean isBeach = blocks.contains(Blocks.grass) || blocks.contains(Blocks.dirt) || blocks.contains(Blocks.sand);
                    boolean hasWater = world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                            world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                            world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                            world.getBlock(x,     y, z + 1).getMaterial() == Material.water;
                    return isBeach && hasWater;
                default:
                    break;
            }
        }
        
        return super.canSustainPlant(world, x, y, z, direction, plantable);
    }
    
    /**
     * Returns whether this block is considered solid.
     */
    protected boolean isBlockSolid(IBlockAccess world, int x, int y, int z)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            return !BlockProperties.hasCover(TE, 6) || BlockProperties.getCover(TE, 6).isOpaqueCube();
        } else {
            return false;
        }
    }
    
    @Override
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if (!world.isRemote) {
            
            TEBase TE = getTileEntity(world, x, y, z);
            
            if (TE != null) {
                BlockProperties.setOwner(TE, (EntityPlayer) entityLiving);
            }
            
        }
    }
    
    @Override
    /**
     * Gets the hardness of block at the given coordinates in the given world, relative to the ability of the given
     * EntityPlayer.
     */
    public float getPlayerRelativeBlockHardness(EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            return ForgeHooks.blockStrength(BlockProperties.getCover(TE, 6), entityPlayer, world, x, y, z);
        } else {
            return super.getPlayerRelativeBlockHardness(entityPlayer, world, x, y, z);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: world, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        TEBase TE = getTileEntity(world, x, y, z);
        
        if (TE != null) {
            
            ForgeDirection side_src = ForgeDirection.getOrientation(side);
            ForgeDirection side_adj = ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[side]);
            
            TEBase TE_adj = (TEBase) world.getTileEntity(x, y, z);
            TEBase TE_src = (TEBase) world.getTileEntity(x + side_adj.offsetX, y + side_adj.offsetY, z + side_adj.offsetZ);
            
            if (TE_adj.getBlockType().isSideSolid(world, x, y, z, side_adj) == TE_src.getBlockType().isSideSolid(world, x + side_adj.offsetX, y + side_adj.offsetY, z + side_adj.offsetZ, ForgeDirection.getOrientation(side))) {
                if (shareFaces(TE_adj, TE_src, side_adj, side_src)) {
                    return BlockProperties.shouldRenderSharedFaceBasedOnCovers(TE_adj, TE_src);
                }
            }
            
        }
        
        return super.shouldSideBeRendered(world, x, y, z, side);
    }
    
    /**
     * Returns whether two blocks share faces.
     * Primarily for slopes, stairs and slabs.
     */
    protected boolean shareFaces(TEBase TE_adj, TEBase TE_src, ForgeDirection side_adj, ForgeDirection side_src)
    {
        return TE_adj.getBlockType().isSideSolid(TE_adj.getWorldObj(), TE_adj.xCoord, TE_adj.yCoord, TE_adj.zCoord, side_adj) &&
                TE_src.getBlockType().isSideSolid(TE_src.getWorldObj(), TE_src.xCoord, TE_src.yCoord, TE_src.zCoord, side_src);
    }
    
    @Override
    /**
     * Determines if this block should render in this pass.
     */
    public boolean canRenderInPass(int pass)
    {
        ForgeHooksClient.setRenderPass(pass);
        
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns which pass this block be rendered on. 0 for solids and 1 for alpha.
     */
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z)
    {
        world.setTileEntity(x, y, z, createNewTileEntity(world, 0));
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TEBase();
    }
    
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
    /**
     * This method is configured on as as-needed basis.
     * It's calling order is not guaranteed.
     */
    protected boolean preOnBlockClicked(TEBase TE, World world, int x, int y, int z, EntityPlayer entityPlayer)
    {
        return false;
    }
    
    /**
     * This method is configured on as as-needed basis.
     * It's calling order is not guaranteed.
     */
    protected boolean[] preOnBlockActivated(TEBase TE, World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        boolean[] result = { false, false };
        
        return result;
    }
    
    /**
     * This method is configured on as as-needed basis.
     * It's calling order is not guaranteed.
     */
    protected boolean[] postOnBlockActivated(TEBase TE, World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        return new boolean[] { false, false };
    }
    
    protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
    {
        return false;
    }
    
    protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
    {
        return false;
    }
    
    protected void damageItemWithChance(World world, EntityPlayer entityPlayer)
    {
        Item item = entityPlayer.getCurrentEquippedItem().getItem();
        
        if (item instanceof ICarpentersHammer) {
            ((ICarpentersHammer) item).onHammerUse(world, entityPlayer);
        } else if (item instanceof ICarpentersChisel) {
            ((ICarpentersChisel) item).onChiselUse(world, entityPlayer);
        }
    }
    
    /**
     * Returns whether base block can be covered.
     */
    protected boolean canCoverBase(TEBase TE, World world, int x, int y, int z)
    {
        return true;
    }
    
    /**
     * Returns whether side of block supports a cover.
     */
    protected boolean canCoverSide(TEBase TE, World world, int x, int y, int z, int side)
    {
        return false;
    }
    
    protected int auxiliaryGetLightValue(TEBase TE, IBlockAccess blockAccess, int x, int y, int z)
    {
        return 0;
    }
    
}