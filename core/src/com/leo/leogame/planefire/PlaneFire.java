package com.leo.leogame.planefire;

import com.badlogic.gdx.math.Interpolation;
import com.leo.leogame.planefire.assetmanage.Asset;
import com.leo.leogame.planefire.screens.DirectedGame;
import com.leo.leogame.planefire.screens.MenuScreen;
import com.leo.leogame.planefire.screens.TestScreen;
import com.leo.leogame.planefire.screens.transitions.ScreenTransition;
import com.leo.leogame.planefire.screens.transitions.ScreenTransitionSlice;


public class PlaneFire extends DirectedGame {

	@Override
	public void create() {
		Asset.instance.init();
		ScreenTransition transition = ScreenTransitionSlice.init(2,
				ScreenTransitionSlice.UP_DOWN, 10, Interpolation.swingOut);

		//setScreen(new MenuScreen(this),transition);
		setScreen(new TestScreen(this));
	}
}
