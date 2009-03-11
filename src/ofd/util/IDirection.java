package ofd.util;

public interface IDirection<D extends IDirection<D>> {
  D next();
  D previous();
}
