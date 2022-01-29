#!/bin/bash
rm -rf *out*
javac OSFileIO.java
strace -ff -o out java OSFileIO $1