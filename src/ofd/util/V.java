package ofd.util;

/** A translation vector. */
public class V {
  private final int dx;
  private final int dy;

  public V(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public int dx() {
    return dx;
  }

  public int dy() {
    return dy;
  }

  @Override
  public int hashCode() {
    final int prime = 17;
    int result = 1;
    result = prime * result + dx;
    result = prime * result + dy;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    V other = (V) obj;
    if (dx != other.dx) {
      return false;
    }
    if (dy != other.dy) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "V(" + dx + ", " + dy + ")";
  }

}