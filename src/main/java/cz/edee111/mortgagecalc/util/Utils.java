package cz.edee111.mortgagecalc.util;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Eduard Tomek
 */
public class Utils {

  public static final int MONTHS_IN_YEAR = 12;

  public static final MathContext MC = new MathContext(16, RoundingMode.HALF_UP);
}
