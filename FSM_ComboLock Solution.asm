TITLE Combination Lock 
; Program prompts the user to enter numbers 1 through 4
; to unlock a combination lock with 4 states (0 to 3)
; 1 decrements the 2s count
; 2 increments the 2s count
; 3 attempts an unlock
; 4 increases the 2s count by 2
; The 2s count is not stored in a variable unless a message is
; to be displayed reflected the current state. All logic is dependent
; on one of the program states.
; Author: Adam Androulidakis

INCLUDE Irvine32.inc

.data

; Strings
str1 BYTE "Please press a button (1, 2, 3, or 4): ", 0
str2 BYTE "The lock 2s count = ", 0
str3 BYTE "The lock is open!", 0
button BYTE ?

.code
main PROC

State0:				; Twos state is zero
	mov al, '0'		; Combination lock starts in twos state of zero
	call DisplayMessage ; Display message indicating current twos state
	call GetButtonPress ; Get button

	cmp al, '2'		; Button 2 pressed?
	je State1			; Transition to state 1 (increment)
	cmp al, '4'		; Button 4 pressed?
	je State2			; Transition to state 2 (increment twice)
	jmp State0		; Value was 1 or 3
					; Value of 1 assumed to decrement state below 0 and reverts to state 0
					; Value of 3 assumes failed unlock and reverts to state 0

State1:				; The twos state is one
	mov al, '1'		; Current twos state char in AL used in DisplayMessage
	call DisplayMessage	; Displays message indicating current twos state
	call GetButtonPress ; Get next button

	cmp al, '1'		; Button1 pressed? 
	je State0			; Decrement to state0
	cmp al, '3'		; Button3 pressed?
	je State0			; Failed unlock - back to state0
	cmp al, '4'		; Button4 pressed? 
	je State3			; Transition to state 3 (double increment)
					; Assuming button 2 pressed, transition to state2 (increment)

State2:				; The twos state is two
	mov al, '2'		; Current twos state char in AL used in DisplayMessage
	call DisplayMessage ; Display message indicating current twos state
	call GetButtonPress ; Get next button

	cmp al, '1'		; Button1 pressed? 
	je State1			; Transition to state1 (decrement)
	cmp al, '3'		; Button3 pressed?
	je Terminate		; Unlocked!
	cmp al, '4'		; Button4 pressed?
	je State3			; Transition to state3 (double increment, 3 is limit)

					; Assume 2 is pressed and transition to state3 (increment)

State3:				; The twos state is three
	mov al, '3'		; Current twos state char in AL used in DisplayMessage
	call DisplayMessage ; Display message indicating current twos state
	call GetButtonPress ; Get next button

	cmp al, '1'		; Button1 pressed?
	je State2			; Transition to state2 (decrement)
	cmp al, '3'		; Button3 pressed?
	je State0			; Transition to state0 (unlock failed)
	jmp State3		; Assumed button 2 or 4 is pressed, transition to state3 (increment limit)

Terminate:			; Terminating state
	mov edx, OFFSET str3; "The lock is open!"
	call WriteString	; Write lock message
	call crlf
	call ReadChar		; Pause console window

exit					; Program end
main ENDP

;---------------------------------------------------------
GetButtonPress PROC
; Get the pressed button from user
; Only values 1 through 4 will be returned
; Receives:None
; Returns: al = returned validated button
;---------------------------------------------------------
	mov edx, OFFSET str1		; "Please press a button (1, 2, 3, or 4): "
	call WriteString

	L1: call ReadChar
	cmp al, '1'				; Read character again if input below 1
	jb L1
	cmp al, '4'				; Read character again if input above 4
	ja L1

	call WriteChar				; Display the character
	call crlf
		ret
	GetButtonPress ENDP

;---------------------------------------------------------
DisplayMessage PROC
; Display a message to the user indicating the current
; state the combination lock is on
; Receives: al = value of twos state
; Returns: n/a
;---------------------------------------------------------

	mov edx, OFFSET str2 ; "The lock two's count = ", 0 
	call WriteString     ; Display the two's count
	call WriteChar		 ; Display char currently in AL register
	call crlf
		ret
	DisplayMessage ENDP

END main
