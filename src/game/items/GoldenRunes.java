package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.RecoverRunesAction;
import game.actors.Player;
import game.tools.RandomNumberGenerator;
import game.actions.Consumable;

import java.util.Scanner;

/**
 * GoldenRunes is a class that represents the money held by the player
 * and can be used to purchase weapons from trader.
 *
 *  @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GoldenRunes extends Item implements Consumable{

    /**
     * Attributes used in this class
     */
    private int runes;
    private int minRune = 200;
    private int maxRune = 10000;

    /**
     * Constructor
     */
    public GoldenRunes(){
        super("Golden Runes", '*', true);
        this.runes = RandomNumberGenerator.getRandomInt(minRune, maxRune);
    }

    /**
     * The GoldenRunes consumable is set as true
     * @return true
     */
    @Override
    public boolean consumable()
    {
        return true;
    }

    /**
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        Player player = (Player) actor;
        Display display = new Display();

        for(int j = 0; j < player.getItemInventory().size(); j ++)
        {
            if(player.getItemInventory().get(j).getClass() == GoldenRunes.class)
            {
                int selection;
                Scanner input = new Scanner(System.in);
                display.println("Do U Want to Use Golden Runes?");
                display.println("-------------------------\n");
                display.println("1 - Yes");
                display.println("2 - No");
                selection = input.nextInt();

                if (selection == 1) {
                    Runes runesAdded = new Runes(player, RandomNumberGenerator.getRandomInt(200, 10000));
                    player.getRunesInt().addRunes(runesAdded.getRunes());
                    display.println(new ConsumeAction(player.getItemInventory().get(j), this.consumable()).execute(player, currentLocation.map()));
                }
            }
        }
    }
}
