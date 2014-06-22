/*
 * Copyright 2014 Filip.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package expandedexplorer;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Filip
 */
public class SoundManager {

    /**
     *
     */
    public final String forward = "forward.wav";

    /**
     *
     */
    public final String backward = "backward.wav";

    /**
     *
     */
    public final String wrong = "wrong.wav";

    /**
     *
     */
    public final String confirmation = "bell.wav";

    /**
     * plays sound with given soundname
     *
     * @param soundname
     */
    public synchronized void playSound(String soundname) {
	try {
	    Clip clip = AudioSystem.getClip();
	    AudioInputStream inputStream = Resources.getAudioStream(soundname);
	    clip.open(inputStream);
	    clip.start();
	} catch (LineUnavailableException ex) {
	    ex.printStackTrace();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

}
