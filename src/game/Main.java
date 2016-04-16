package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.command.MouseButtonControl;

public class Main extends BasicGame implements InputProviderListener {
	private Animation sprite, up, down, left, right, stopRight;
	private Command attack = new BasicCommand("attack");
	private boolean stop = true;
	
	public Main() {
		super("MyGame");
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try
        {
            AppGameContainer app = new AppGameContainer(new Main());
            app.setDisplayMode(500, 400, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		arg1.drawString("Howdy!", 250, 200);
		if (stop) {
			arg1.drawAnimation(stopRight, 40, 40);
		} else {
			arg1.drawAnimation(right, 40, 40);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		int time = 150;
		int [] duration = {time, time, time, time};
		
		Image [] movementStopRight = {new Image("sprites/walk_right_1.png")};
		Image [] movementRight = {new Image("sprites/walk_right_2.png"), new Image("sprites/walk_right_3.png"), new Image("sprites/walk_right_4.png"), new Image("sprites/walk_right_5.png")};
		
		stopRight = new Animation(movementStopRight, 500, false);
		right = new Animation(movementRight, duration, true);
		right.setLooping(true);
		right.start();
		stopRight.start();
		
		InputProvider provider = new InputProvider(container.getInput());
        provider.addListener(this);
        provider.bindCommand(new KeyControl(Input.KEY_SPACE), attack);
        provider.bindCommand(new MouseButtonControl(0), attack);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		//right.start();
	}

	@Override
	public void controlPressed(Command arg0) {
		System.out.println(arg0.toString());
		right.restart();
		stop = false;
	}

	@Override
	public void controlReleased(Command arg0) {
		System.out.println(arg0.toString());
		stop = true;
	}

}
