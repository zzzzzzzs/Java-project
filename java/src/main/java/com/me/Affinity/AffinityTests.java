package com.me.Affinity;

import net.openhft.affinity.AffinityLock;

public class AffinityTests {
//  https://blog.csdn.net/u011943534/article/details/118557488
  public static void main(String[] args) {
    try (AffinityLock affinityLock = AffinityLock.acquireLock(2)) {
      while (true) {}
    }
  }
}
