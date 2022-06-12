package com.me.other;

public class ProgressBarTest {
  // 进度条
  public static void progressPar(int done, int total) {
    int size = 100;
    String iconLeftBoundary = "[";
    String iconDone = "=";
    String iconRemain = ".";
    String iconRightBoundary = "]";

    if (done > total) {
      throw new IllegalArgumentException("done must be less than total");
    }
    int donePercents = (100 * done) / total;
    int doneLength = size * donePercents / 100;

    StringBuilder bar = new StringBuilder(iconLeftBoundary);
    for (int i = 0; i < size; i++) {
      if (i < doneLength) {
        bar.append(iconDone);
      } else {
        bar.append(iconRemain);
      }
    }
    bar.append(iconRightBoundary);

    System.out.print("\r" + bar + " " + donePercents + "%");

    if (done == total) {
      System.out.print("\n");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      progressPar(i, 99);
      Thread.sleep(10);
    }
  }
}
