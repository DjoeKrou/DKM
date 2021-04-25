package ru.ijo42.dkm.medicaments.stimulators;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.ijo42.dkm.base.MedicamentBaseItem;
import ru.ijo42.dkm.base.PotionApplier;
import ru.ijo42.dkm.interfaces.IMedicamentSpecs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class AchitilcelinItem extends MedicamentBaseItem {

    public AchitilcelinItem() {
        super(new AchitilcelinSpecs());
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onFoodEaten(final ItemStack stack, final World worldIn, final EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if (!worldIn.isRemote) {
            PotionApplier.applyPotion(player, MobEffects.RESISTANCE, 50);
            PotionApplier.applyPotion(player, MobEffects.REGENERATION, 50);
            PotionApplier.applyPotion(player, MobEffects.HUNGER, 200);
            PotionApplier.applyPotion(player, MobEffects.SLOWNESS, 10);
            new Timer(this + " Thread").schedule(new TimerTask() {
                @Override
                public void run() {
                    PotionApplier.applyPotion(player, MobEffects.NAUSEA, 20);
                }
            }, TimeUnit.SECONDS.toMillis(120));
        }
    }

    static class AchitilcelinSpecs implements IMedicamentSpecs {

        @Override
        public int getUsageTime() {
            return 1;
        }

        @Override
        public int getMaxDamage() {
            return 1;
        }

        @Nonnull
        @Override
        public String getName() {
            return "achitilcelin";
        }

    }

}
