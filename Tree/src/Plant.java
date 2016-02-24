import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Plant implements Drawable
{
	double x, y, x1, y1;

	public Plant()
	{
		x = 0;
		y = 0;
		x1 = 0;
		y1 = 0;
	}

	public Plant(double xx1, double yy1, double xx2, double yy2)
	{
		x = xx1;
		y = yy1;
		x1 = xx2;
		y1 = yy2;
	}

	private double[] branch(double xx1, double yy1, double xx2, double yy2)
	{
		double baseX, baseY;

		double leftX, leftY;

		double rightX, rightY;
		String sloped = getSlope(xx1, yy1, xx2, yy2);
		double leng = getLength(xx1, yy1, xx2, yy2);
		double newLength = (leng/10)*6;
		baseX = xx2;
		baseY = yy2;
		double c;
		double slopeRight, slopeLeft;
		double rightDirection, leftDirection;

		if (sloped.equals("vertical"))
		{
			if (yy1 > yy2)
			{
				rightDirection = 1;
				leftDirection = -1;
			} else
			{
				rightDirection = -1;
				leftDirection = 1;
			}

			slopeRight = -1;
			slopeLeft = 1;

			c = baseY - (slopeLeft) * baseX;

			leftX = leftDirection * Math.sqrt(Math.pow(newLength, 2) / 2)
					+ baseX;
			leftY = slopeLeft * (leftX) + c;

			c = baseY - (slopeRight) * baseX;
			rightX = rightDirection * Math.sqrt(Math.pow(newLength, 2) / 2)
					+ baseX;
			rightY = slopeRight * (rightX) + c;
			
		} else if (sloped.equals("horizontal"))
		{
			if (xx1 > xx2)
			{
				rightDirection = -1;
				leftDirection = -1;
			} else
			{
				rightDirection = 1;
				leftDirection = 1;
			}

			slopeRight = 1;
			slopeLeft = -1;
			
			c = baseY - (slopeLeft) * baseX;
			leftX = leftDirection * Math.sqrt(Math.pow(newLength, 2) / 2)
					+ baseX;
			leftY = slopeLeft * leftX + c;

			c = baseY - (slopeRight) * baseX;
			rightX = rightDirection * Math.sqrt(Math.pow(newLength, 2) / 2)
					+ baseX;
			rightY = slopeRight * rightX + c;
		} else
		{
			double baseSlope = (yy2 - yy1) / (xx2 - xx1);
			if (xx1 > xx2 && yy1 > yy2)
			{
				leftDirection = -1;
				rightDirection = -1;
			} else if (xx1 > xx2 && yy1 < yy2)
			{
				leftDirection = -1;
				rightDirection = 1;
			}

			else if (xx1 < xx2 && yy1 < yy2)

			{
				leftDirection = 1;
				rightDirection = 1;
			}

			else
			// (x < x1 && y > y1)
			{
				leftDirection = 1;
				rightDirection = -1;
			}
			if (baseSlope == 1)

			{
				leftX = xx2;
				leftY = yy2 + leftDirection * newLength;
				rightY = yy2;
				rightX = xx2 + rightDirection * newLength;
			}

			else
			// baseSlope == -1
			{

				leftX = xx2 + leftDirection * newLength;
				leftY = yy2;
				rightY = yy2 + rightDirection * newLength;
				rightX = xx2;
			}
		}
		double[] tee = { baseX, baseY, rightX, rightY, leftX, leftY };
		return tee;
	}

	public void Draw(Graphics2D g2In)
	{
		drawTree(g2In, x, y, x1, y1);
	}

	private String getSlope(double xx1, double yy1, double xx2, double yy2)
	{
		String slope = "sloped";
		if (xx1 == xx2)
		{
			slope = "vertical";
		} else if (yy1 == yy2)
		{
			slope = "horizontal";
		}
		return slope;
	}

	private double getLength(double xx1, double yy1, double xx2, double yy2)
	{
		double leng;
		String sloppy = getSlope(xx1, yy1, xx2, yy2);
		if (sloppy.equals("vertical"))
		{
			leng = Math.abs(yy1 - yy2);
		} else if (sloppy.equals("horizontal"))
		{
			leng = Math.abs(xx1 - xx2);
		} else
		{
			leng = Math.sqrt(Math.pow((xx1 - xx2), 2) + Math.pow((yy1 - yy2), 2));
		}
		return leng;

	}

	private void drawTree(Graphics2D g2In, double x, double y, double x2,
			double y2)
	{
		double length = getLength(x, y, x2, y2);

		if (length <=2)
		{

			// draw line
			g2In.setColor(Color.black);
			g2In.draw(new Line2D.Double(x, y, x2, y2));

		} else
		{
			// draw line
			g2In.setColor(Color.magenta);
			g2In.draw(new Line2D.Double(x, y, x2, y2));
			
			
			// calculate 2 new lines
			double[] branches = branch(x, y, x2, y2);
			drawTree(g2In, branches[0], branches[1], branches[2], branches[3]);
			drawTree(g2In, branches[0], branches[1], branches[4], branches[5]);
			
			// drawTree(g2In,)
		}
	}
}
