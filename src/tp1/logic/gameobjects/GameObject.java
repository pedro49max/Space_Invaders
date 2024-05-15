package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected GameWorld game;
	protected boolean goodSide;
	
	
	public GameObject(GameWorld game2, Position pos, int life, boolean goodSide) {	
		this.pos = pos;
		this.game = game2;
		this.life = life;
		this.goodSide = goodSide;
	}
	
	@Override
	public boolean isAlive() {
		return this.life > 0;
	}
	
	public String toString() {
		return this.getSymbol();
	}
	
	protected int getLife() {
		return this.life;
	}

	//TODO fill with your code

	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();
	protected abstract void setDir();
			
	public abstract void onDelete();
	public abstract void automaticMove();
	public void computerAction() {
		this.setDir();
	};
	
	//TODO fill with your code
	
	@Override
	public boolean performAttack(GameItem other) {
		boolean attack = false;
		if(this.getSymbol() == Messages.BOMB_SYMBOL) {
			EnemyWeapon w = (EnemyWeapon) this;
			attack = other.receiveAttack(w);
		}
		else if(this.getSymbol() == Messages.LASER_SYMBOL) {
			UCMWeapon w = (UCMWeapon) this;
			attack = other.receiveAttack(w);
		}
		return attack;
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		if(this.isOnPosition(weapon.pos)) {
			if(this.goodSide) {
				this.life -= weapon.getDamage();
				this.game.deleteObject(weapon);
				if(this.life <= 0) {
					this.onDelete();
				}
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		if(this.isOnPosition(weapon.pos)) {
			if(!this.goodSide) {
				this.life -= weapon.getDamage();
				this.game.deleteObject(weapon);
				if(this.life <= 0) {
					this.game.receivePoints(this.points());
					this.onDelete();
					this.game.deleteObject(this);
				}
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

}
