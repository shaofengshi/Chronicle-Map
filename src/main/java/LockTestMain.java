/*
 * Copyright 2016-2020 Chronicle Software
 *
 * https://chronicle.software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import net.openhft.chronicle.core.Jvm;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.io.File;
import java.io.IOException;

public class LockTestMain {
    public static void main(String[] args) throws IOException {
        File f = new File("mapfile");

        ChronicleMap<Integer, Integer> map = ChronicleMapBuilder
                .of(Integer.class, Integer.class)
                .entries(100)
                .createPersistedTo(f);

        for (int i = 0; i < 10; i++)
            map.put(i, i);

        for (int i = 0; i < 10; i++) {
            if (!Integer.valueOf(i).equals(map.get(i)))
                throw new IllegalStateException("FAIL");
        }

        System.out.println("Success");

        System.out.println("Sleeping for 300 seconds");

        Jvm.pause(300_000);

    }
}
