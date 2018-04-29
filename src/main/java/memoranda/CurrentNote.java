package main.java.memoranda;

import java.util.Collection;
import java.util.Vector;

public class CurrentNote {

    // TASK 2-2 SMELL BETWEEN CLASSES feature envy - this class is doing very little more than Note.java, 
    // Note.java could contain all the same methods to bypass having this class exist
	private static Note currentNote = null;
    private static Vector noteListeners = new Vector();

    public static Note get() {
        return currentNote;
    }

    public static void set(Note note, boolean toSaveCurrentNote) {
        noteChanged(note, toSaveCurrentNote);
        currentNote = note;
    }

    public static void reset() {
//    	 set toSave to true to mimic status quo behaviour only. the appropriate setting could be false
        set(null, true);
    }

    public static void addNoteListener(NoteListener nl) {
        noteListeners.add(nl);
    }

    public static Collection getChangeListeners() {
        return noteListeners;
    }

    private static void noteChanged(Note note, boolean toSaveCurrentNote) {
        for (int i = 0; i < noteListeners.size(); i++) {
            ((NoteListener)noteListeners.get(i)).noteChange(note,toSaveCurrentNote);
		}
    }
}
