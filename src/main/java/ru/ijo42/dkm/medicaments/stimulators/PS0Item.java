package ru.ijo42.dkm.medicaments.stimulators;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ru.ijo42.dkm.Constants;
import ru.ijo42.dkm.base.MedicamentBaseItem;
import ru.ijo42.dkm.interfaces.IMedicamentSpecs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PS0Item extends MedicamentBaseItem {

    public PS0Item() {
        super(new PS0Specs());
    }

    @Override
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    protected void onFoodEaten(final ItemStack stack, final World worldIn, final EntityPlayer player) {
        if (!worldIn.isRemote) {
            //TODO: накладывать эффект `под обезболивающим` (200с)
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 50 * Constants.TICK_IN_SECONDS));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 50 * Constants.TICK_IN_SECONDS));
            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100 * Constants.TICK_IN_SECONDS));
            new Timer(this + " Thread").schedule(new TimerTask() {
                @Override
                public void run() {
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20 * Constants.TICK_IN_SECONDS));
                }
            }, TimeUnit.SECONDS.toMillis(300));
        }
    }

    static class PS0Specs implements IMedicamentSpecs {

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
            return "ps0";
        }

    }

}
