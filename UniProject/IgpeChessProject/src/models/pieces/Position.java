package models.pieces;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position original) {
		this.x = original.x;
		this.y = original.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String translate() {
		return (char) (this.getY() + 97) + "" + ((Integer) (8 - this.getX())).toString();
	}
	
	public static Position reverseTranslate(String translate) {
		int x = 8 - Integer.parseInt(Character.toString(translate.charAt(1)));
		int y = translate.charAt(0) - 97;
		System.out.println(x + " " + y);
		return new Position(x,y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Position position = (Position) o;
		return this.x == position.getX() && this.y == position.getY();
	}

	@Override
	public String toString() {
		char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
		return "(" + this.x + " - " + Integer.toString(this.y) + ")"; // ricordiamo di mettere il +1 a y
	}
	
}