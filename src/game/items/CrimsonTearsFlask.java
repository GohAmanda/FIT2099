package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.ResetManager;
import game.actions.Resettable;
import game.actions.Consumable;
import game.actors.Player;

import java.util.Scanner;

/**
 * CrimsonTearsFlask is a class that represents player's item which is not portable
 * It helps player to heal
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class CrimsonTearsFlask extends Item implements Consumable, Resettable {
    private final int hitPoints = 250;
    private final int maxFlaskOfCrimsonTears = 2;
    private int numOfFlask;

    /***
     * Constructor.
     */
    public CrimsonTearsFlask() {
        // we assume displayChar of flask is 'F'
        super("Flask of Crimson Tears", 'F', false);
        this.numOfFlask = this.maxFlaskOfCrimsonTears;

        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Getter to get the current number of crimson tears flask
     * @return
     */
    public int getNumOfFlask(){
        return this.numOfFlask;
    }

    /**
     * To use the crimson tears flask
     * @return hitpoint if the number of crimson tears flasks is more than 0, otherwise, return 0
     */
    public int useFlask(){
        if (this.numOfFlask > 0) {
            this.numOfFlask -= 1;
            return this.hitPoints;
        }
        else
            return 0;
    }

    /**
     * Inform whether to use the item
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        Player player = (Player) actor;
        Display display = new Display();
        if (!player.isMaxHp() && player.getFlaskOfCrimsonTears().getNumOfFlask() > 0) {

            int selection;
            Scanner input = new Scanner(System.in);
            display.println("Do U Want to Use Flask of Crimson Tears?\n" + player.getFlaskOfCrimsonTears().toString());
            display.println("-------------------------\n");
            display.println("1 - Yes");
            display.println("2 - No");
            selection = input.nextInt();

            if (selection == 1) {
                player.heal(player.getFlaskOfCrimsonTears().useFlask());
                display.println(player.getFlaskOfCrimsonTears().toString());
                if(player.getFlaskOfCrimsonTears().getNumOfFlask() == 0) {
                    new ConsumeAction(player.getFlaskOfCrimsonTears(), player.getFlaskOfCrimsonTears().consumable()).execute(player, currentLocation.map());
                    display.println("After Consumed:" + player.getFlaskOfCrimsonTears().getNumOfFlask() + "+" + player.getItemInventory());
                }
            }
        }
    }

    /**
     * @return String format of crimson tears flask
     */
    public String toString(){
        return this.numOfFlask + "/" + this.maxFlaskOfCrimsonTears;
    }



    /**
     * When reset, the number if crimson tears flask reset to the maximum use (2)
     */
    @Override
    public void reset() {
        this.numOfFlask = this.maxFlaskOfCrimsonTears;
    }

    /**
     * Crimson tears flask is resettable
     * @return true
     */
    @Override
    public boolean resettable()
    {
        return true;
    }

    /**
     * Cimson tears flask is consumable
     * @return true
     */
    @Override
    public boolean consumable()
    {
        return true;
    }
}
