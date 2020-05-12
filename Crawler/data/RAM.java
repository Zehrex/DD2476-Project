4
https://raw.githubusercontent.com/kemusiro/jr100-emulator-v2/master/src/jp/asamomiji/emulator/RAM.java
/**
 * JR-100 Emulator Version 2
 *
 * Copyright (c) 2006-2020 Kenichi Miyata
 *
 * This software is released under the the MIT license
 * http://opensource.org/licenses/mit-license.php
 */
package jp.asamomiji.emulator;

/**
 * この抽象クラスは読み書き可能なメモリを表す。
 */
public abstract class RAM extends Memory {
    public RAM(int start, int length) {
        super(start, length);
    }
}
