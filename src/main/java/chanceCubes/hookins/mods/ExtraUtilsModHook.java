package chanceCubes.hookins.mods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import chanceCubes.registry.ChanceCubeRegistry;
import chanceCubes.rewards.BasicReward;
import chanceCubes.rewards.type.BlockRewardType;
import chanceCubes.rewards.type.ItemRewardType;
import chanceCubes.util.OffsetBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExtraUtilsModHook extends BaseModHook
{

	public ExtraUtilsModHook()
	{
		super("ExtraUtilities");
		loadRewards();
	}

	@Override
	public void loadRewards()
	{
		ItemStack stack;
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":unstableingot", -100, new ItemRewardType(GameRegistry.findItemStack(super.modId, "unstableingot", 1))
		{
			@Override
			public void trigger(ItemStack s, World world, int x, int y, int z, EntityPlayer player)
			{
				ItemStack stack1 = s.copy();
				NBTTagCompound ts = new NBTTagCompound();
				if(ts.hasKey("crafting"))
				{
					ts.removeTag("crafting");
				}
				if(stack1.getItemDamage() > 0)
				{
					return;
				}
				ts.setInteger("dimension", world.provider.dimensionId);
				ts.setLong("time", world.getTotalWorldTime());
				stack1.setTagCompound(ts);

				world.setBlock(x, y, z, Blocks.crafting_table);
				player.displayGUIWorkbench(x, y, z);
				player.inventory.setInventorySlotContents(player.inventory.currentItem, stack1);
				if((player instanceof EntityPlayerMP))
				{
					((EntityPlayerMP) player).mcServer.getConfigurationManager().syncPlayerInventory((EntityPlayerMP) player);
				}
			}
		}));

		stack = GameRegistry.findItemStack(super.modId, "generator.64", 1);
		stack.setStackDisplayName("Useless Generator");
		stack.setItemDamage(9);
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Pink_Generator", 30, new ItemRewardType(stack)));

		stack = GameRegistry.findItemStack(super.modId, "watering_can", 1);
		stack.setItemDamage(2);
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Broken_Watering_Can", 15, new ItemRewardType(stack)));

		stack = GameRegistry.findItemStack(super.modId, "drum", 1);
		stack.setItemDamage(1);
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Bedrockium_Drum", 65, new ItemRewardType(stack)));

		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Golden_Bag", 90, new ItemRewardType(GameRegistry.findItemStack(super.modId, "golden_bag", 1))));

		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Ender_Lilly", 45, new ItemRewardType(GameRegistry.findItemStack(super.modId, "plant/ender_lilly", 3))));

		stack = GameRegistry.findItemStack(super.modId, "endConstructor", 10);
		stack.setItemDamage(2);
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Ender_Flux_Crystal", 80, new ItemRewardType(stack)));

		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Deep_Dark", 95, new ItemRewardType(GameRegistry.findItemStack(super.modId, "dark_portal", 1))));

		stack = GameRegistry.findItemStack(super.modId, "dark_portal", 1);
		stack.setItemDamage(2);
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Last_Millenium", 95, new ItemRewardType(stack)));

		stack = GameRegistry.findItemStack(super.modId, "cobblestone_compressed", 4);
		stack.setItemDamage(5);
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Compressed_Cobble", 20, new ItemRewardType(stack)));

		OffsetBlock[] spikes = new OffsetBlock[50];
		for(int x = 0; x < 5; x++)
		{
			for(int z = 0; z < 5; z++)
			{
				spikes[x * 5 + z] = new OffsetBlock(x - 2, -1, z - 2, GameRegistry.findBlock(super.modId, "spike_base"), false).setRelativeToPlayer(true);
				spikes[x * 5 + z].setData((byte) 1);
				spikes[25 + (x * 5 + z)] = new OffsetBlock(x - 2, 2, z - 2, GameRegistry.findBlock(super.modId, "spike_base"), false).setRelativeToPlayer(true);

			}
		}
		ChanceCubeRegistry.INSTANCE.registerReward(new BasicReward(this.modId + ":Spikes", -40, new BlockRewardType(spikes)));
	}
}
