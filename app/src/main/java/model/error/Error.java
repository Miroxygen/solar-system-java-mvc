package model.error;

public abstract class Error {
  Iterable<?> iterate;

  Error(Iterable<?> i) {
    iterate = i;
  }
  
  public void errorCheck() {

  }
}
