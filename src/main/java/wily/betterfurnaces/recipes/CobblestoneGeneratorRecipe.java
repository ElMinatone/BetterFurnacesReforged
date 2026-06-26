package wily.betterfurnaces.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
//? if >=1.20.5 {
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
//?}
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import wily.betterfurnaces.init.ModObjects;
import wily.factoryapi.base.network.CommonNetwork;
import wily.factoryapi.util.DynamicUtil;

public record CobblestoneGeneratorRecipe(/*? if <1.20.2 {*//*Identifier id, *//*?}*/ItemStack result, int duration) implements Recipe</*? if <1.20.5 {*//*Container*//*?} else {*/SingleRecipeInput/*?}*/> {
    private static final MapCodec<CobblestoneGeneratorRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(DynamicUtil.ITEM_CODEC.fieldOf("result").forGetter((rcp) -> rcp.result), Codec.INT.fieldOf("duration").orElse(80).forGetter((rcp) -> rcp.duration)).apply(i, CobblestoneGeneratorRecipe::new));
    private static final StreamCodec<RegistryFriendlyByteBuf, CobblestoneGeneratorRecipe> STREAM_CODEC = StreamCodec.of((b,r)-> {
        CommonNetwork.encodeItemStack(()-> b, r.result);
        b.writeVarInt(r.duration);
    }, b-> new CobblestoneGeneratorRecipe(CommonNetwork.decodeItemStack(()->b), b.readVarInt()));
    public static final RecipeSerializer<CobblestoneGeneratorRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

    @Override
    public boolean isSpecial() {
        return true;
    }

    //? if <1.20.2 {
    /*@Override
    public Identifier getId() {
        return id();
    }
    *///?}

    @Override
    public boolean matches(/*? if <1.20.5 {*//*Container*//*?} else {*/SingleRecipeInput/*?}*/ inv, Level worldIn) {
        return true;
    }

    @Override
    public ItemStack assemble(/*? if <1.20.5 {*//*Container*//*?} else {*/SingleRecipeInput/*?}*/ p_44001_) {
        return result.copy();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<CobblestoneGeneratorRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<CobblestoneGeneratorRecipe> getType() {
        return ModObjects.ROCK_GENERATING_RECIPE.get();
    }

    //? if >=1.21.2 {
    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
    //?} else {
    /*@Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(/^? if <1.20.5 {^//^RegistryAccess access^//^?} else {^/HolderLookup.Provider access/^?}^/) {
        return result;
    }
    *///?}

}
