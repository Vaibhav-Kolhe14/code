int LEDpin = 13; 
int delayT = 1000; 

void setup() { 
  // Set pin 13 as an output
  pinMode(LEDpin, OUTPUT); 
} 

void loop() { 
  // Turn the LED on
  digitalWrite(LEDpin, HIGH); 
  delay(delayT);  

  // Turn the LED off
  digitalWrite(LEDpin, LOW); 
  delay(delayT); 
}
