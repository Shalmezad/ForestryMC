package forestry.worktable.compat;

import com.google.common.base.Preconditions;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import forestry.api.core.ForestryAPI;
import forestry.core.config.Constants;
import forestry.core.utils.JeiUtil;
import forestry.modules.ForestryModuleUids;
import forestry.worktable.ModuleWorktable;
import forestry.worktable.blocks.BlockRegistryWorktable;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;

@JEIPlugin
@SideOnly(Side.CLIENT)
public class WorktableJeiPlugin implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		if(!ForestryAPI.enabledModules.contains(new ResourceLocation(Constants.MOD_ID, ForestryModuleUids.WORKTABLE))){
			return;
		}
		BlockRegistryWorktable blocks = ModuleWorktable.getBlocks();
		Preconditions.checkNotNull(blocks);

		registry.addRecipeCatalyst(new ItemStack(blocks.worktable), VanillaRecipeCategoryUid.CRAFTING);

		IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
		transferRegistry.addRecipeTransferHandler(new WorktableRecipeTransferHandler(), VanillaRecipeCategoryUid.CRAFTING);

		JeiUtil.addDescription(registry, blocks.worktable);
	}
}
